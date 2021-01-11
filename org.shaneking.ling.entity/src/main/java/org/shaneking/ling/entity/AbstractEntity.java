package org.shaneking.ling.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.entity.sql.Condition;
import org.shaneking.ling.entity.sql.Keyword;
import org.shaneking.ling.entity.sql.Pagination;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.persistence.Tuple;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;
import org.shaneking.ling.zero.util.Regex0;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Design for has cache want single table operation
 */
@Accessors(chain = true)
@Slf4j
@ToString
public abstract class AbstractEntity<J> {
  @Transient
  private static final String EMPTY_COMMENT_WITH_BLACK_PREFIX = " ''";
  @Transient
  private static final String UNIQUE_INDEX_NAME_PREFIX = "u_idx_";

  @Getter
  @JsonIgnore
  @Transient
  private final Map<String, Column> columnMap = Map0.newHashMap();
  @Getter
  @JsonIgnore
  @Transient
  private final Map<String, String> dbColumnMap = Map0.newHashMap();
  @Getter
  @JsonIgnore
  @Transient
  private final Map<String, Field> fieldMap = Map0.newHashMap();
  @Getter
  @JsonIgnore
  @Transient
  private final List<String> fieldNameList = List0.newArrayList();
  @Getter
  @JsonIgnore
  @Transient
  private final List<String> idFieldNameList = List0.newArrayList();
  @Getter
  @JsonIgnore
  @Transient
  private final List<String> verFieldNameList = List0.newArrayList();
  @Getter
  @JsonIgnore
  @Setter
  @Transient
  private String dbTableName;
  @Getter
  @JsonIgnore
  @Setter
  @Transient
  private Table javaTable;

  @Getter
  @Setter
  @Transient
  private List<String> groupByList;
  @Getter
  @Setter
  @Transient
  private J havingConditions;
  @Getter
  @Setter
  @Transient
  private List<String> orderByList;
  @Getter
  @Setter
  @Transient
  private Pagination pagination;
  @Getter
  @Setter
  @Transient
  private List<String> selectList;
  /**
   * I don't want Map<String, Condition> to limit everyone, J maybe Map/fastjson/gson/jackson...
   * <blockquote><pre>
   *     {
   *         crtDateTime:{
   *             op:'between',
   *             cl:['2017-09-10','2019-04-27']
   *         },
   *         delDateTime:{
   *             op:'>',
   *             cs:'2017-09-10'
   *         },
   *         modDateTime:{
   *             op:'like',
   *             cs:'2019-04-27',
   *             bw:'%',
   *             ew:'%'
   *         }
   *     }
   * </pre></blockquote>
   */
  @Getter
  @Setter
  @Transient
  private J whereConditions;

  //init
  public AbstractEntity() {
    initTableInfo();
    initColumnInfo(this.getClass());
  }

  public void initTableInfo() {
    if (this.getJavaTable() == null) {
      this.setJavaTable(this.getClass().getAnnotation(Table.class));
    }
    if (String0.isNull2Empty(this.getJavaTable().name())) {
      String classTableName = String0.field2DbColumn(List0.reverse(List0.newArrayList(this.getClass().getName().split(Regex0.DOT))).get(0));
      this.setDbTableName("t" + (classTableName.startsWith(String0.UNDERLINE) ? classTableName : String0.UNDERLINE + classTableName));
    } else {
      this.setDbTableName(this.getJavaTable().name());
    }
  }

  public void initColumnInfo(Class<? extends Object> skEntityClass) {
    if (AbstractEntity.class.isAssignableFrom(skEntityClass.getSuperclass())) {
      initColumnInfo(skEntityClass.getSuperclass());
    }
    for (Field field : skEntityClass.getDeclaredFields()) {
      if (field.getAnnotation(Transient.class) == null) {
        Column column = field.getAnnotation(Column.class);
        if (column != null) {
          this.getColumnMap().put(field.getName(), column);
          this.getFieldMap().put(field.getName(), field);
          this.getDbColumnMap().put(field.getName(), String0.isNull2Empty(column.name()) ? String0.field2DbColumn(field.getName()) : column.name());
          if (!this.getFieldNameList().contains(field.getName())) {
            this.getFieldNameList().add(field.getName());
          }
        }
        if (field.getAnnotation(Id.class) != null && !this.getIdFieldNameList().contains(field.getName())) {
          this.getIdFieldNameList().add(field.getName());
        }
        if (field.getAnnotation(Version.class) != null && !this.getVerFieldNameList().contains(field.getName())) {
          this.getVerFieldNameList().add(field.getName());
        }
      }
    }
  }

  //create table
  public String createTableIfNotExistSql() {
    String idxSql = createTableIndexSql();
    idxSql = String0.isNull2Empty(idxSql) ? String0.EMPTY : (idxSql + String0.BR_LINUX + String0.BR_LINUX);
    return "drop procedure if exists p_" + this.getDbTableName() + "_create;" + String0.BR_LINUX +
      "delimiter $$" + String0.BR_LINUX +
      "create procedure p_" + this.getDbTableName() + "_create() begin" + String0.BR_LINUX +
      "if not exists (select * from information_schema.tables where table_schema = '" + this.getJavaTable().schema() + "' and table_name = '" + this.getDbTableName() + "')" + String0.BR_LINUX +
      "then" + String0.BR_LINUX + String0.BR_LINUX +
      createTableSql() + String0.BR_LINUX + String0.BR_LINUX +
      idxSql +
      "end if;" + String0.BR_LINUX +
      "end;" + String0.BR_LINUX +
      "$$" + String0.BR_LINUX +
      "delimiter ;" + String0.BR_LINUX +
      "call p_" + this.getDbTableName() + "_create();" + String0.BR_LINUX +
      "drop procedure if exists p_" + this.getDbTableName() + "_create;" + String0.BR_LINUX;
  }

  public String createTableSql() {
    List<String> sqlList = List0.newArrayList();
    String idxSchemaPart = String0.notNull2EmptyTo(String0.nullToEmpty(this.getJavaTable().schema()), MessageFormat.format("`{0}`.", this.getJavaTable().schema()));
    sqlList.add(MessageFormat.format("{0} {1}`{2}` (", Keyword.CREATE_TABLE, idxSchemaPart, this.getDbTableName()));
    for (String versionColumn : this.getVerFieldNameList()) {
      sqlList.add(this.createColumnStatement(versionColumn, true));
    }
    for (String idColumn : this.getIdFieldNameList()) {
      sqlList.add(this.createColumnStatement(idColumn, true));
    }
    for (String columnName : this.getFieldNameList()) {
      if (!this.getIdFieldNameList().contains(columnName) && !this.getVerFieldNameList().contains(columnName)) {
        sqlList.add(this.createColumnStatement(columnName, false));
      }
    }
    sqlList.add(MessageFormat.format("  {0} (`{1}`)", Keyword.PRIMARY_KEY, String.join("`,`", this.getIdFieldNameList().stream().map(idFieldName -> this.getDbColumnMap().get(idFieldName)).collect(Collectors.toList()))));
    sqlList.add(String0.CLOSE_PARENTHESIS + String0.SEMICOLON);
    return String.join("\n", sqlList);
  }

  public String createTableIndexSql() {
    Map<String, List<String>> idxPartNameColumnsMap = Map0.newHashMap();
    List0.newArrayList(this.getJavaTable().uniqueConstraints()).stream().filter(uniqueConstraint -> uniqueConstraint.columnNames().length > 0).forEach(uniqueConstraint -> {
      idxPartNameColumnsMap.put(String.join(String0.UNDERLINE, uniqueConstraint.columnNames()), List0.newArrayList(uniqueConstraint.columnNames()));
    });
    this.getFieldNameList().stream().filter(fieldName -> this.getColumnMap().get(fieldName).unique()).forEach(fieldName -> {
      idxPartNameColumnsMap.put(this.getDbColumnMap().get(fieldName), List0.newArrayList(this.getDbColumnMap().get(fieldName)));
    });
    List<String> createIndexStatementList = List0.newArrayList();
    String idxSchemaPart = String0.notNull2EmptyTo(String0.nullToEmpty(this.getJavaTable().schema()), MessageFormat.format("`{0}`.", this.getJavaTable().schema()));
    idxPartNameColumnsMap.forEach((idxPartName, columnList) -> {
      String indexColumns = "`" + (columnList.size() > 1 ? String.join("` asc, `", columnList) : columnList.get(0)) + "` asc";
      createIndexStatementList.add(MessageFormat.format("{0} {1}`{2}` {3} `{4}` ({5});", Keyword.ALTER_TABLE, idxSchemaPart, this.getDbTableName(), Keyword.ADD_UNIQUE_INDEX, UNIQUE_INDEX_NAME_PREFIX + idxPartName, indexColumns));
    });
    return String.join(String0.BR, createIndexStatementList);
  }

  private String createColumnStatement(String columnName, boolean idOrVersion) {
    String rtn, columnDbTypeString;
    Field columnField = this.getFieldMap().get(columnName);
    String partNotNull = (idOrVersion || !this.getColumnMap().get(columnName).nullable()) ? Keyword.NOT_NULL_WITH_BLACK_PREFIX : String0.EMPTY;
    if (columnField.getAnnotation(Lob.class) != null) {
      columnDbTypeString = Keyword.TYPE_LONGTEXT;
    } else if (Integer.class.getCanonicalName().equals(columnField.getType().getCanonicalName())) {
      columnDbTypeString = Keyword.TYPE_INT;
    } else {
      columnDbTypeString = Keyword.TYPE_VARCHAR;
    }
    String[] comments = String0.nullToEmpty(this.getColumnMap().get(columnName).columnDefinition()).split(Keyword.COMMENT4ANNOTATION);
    String commentWithBlackPrefix = comments.length > 1 ? comments[1] : EMPTY_COMMENT_WITH_BLACK_PREFIX;
    if (Keyword.TYPE_LONGTEXT.equals(columnDbTypeString) || Keyword.TYPE_INT.equals(columnDbTypeString)) {
      rtn = MessageFormat.format("  `{0}` {1}{2} {3}{4},", this.getDbColumnMap().get(columnName), columnDbTypeString, partNotNull, Keyword.COMMENT, commentWithBlackPrefix);
    } else {
      rtn = MessageFormat.format("  `{0}` {1}({2}){3} {4}{5},", this.getDbColumnMap().get(columnName), columnDbTypeString, String.valueOf(this.getColumnMap().get(columnName).length()), partNotNull, Keyword.COMMENT, commentWithBlackPrefix);
    }
    return rtn;
  }

  //abstracts
  @NonNull
  public abstract List<Condition> findHavingConditions(@NonNull String fieldName);

  @NonNull
  public abstract List<Condition> findWhereConditions(@NonNull String fieldName);

  public abstract void limitStatement(@NonNull List<String> limitList, @NonNull List<Object> objectList);

  //prepares
  public void fillOc(@NonNull List<String> list, @NonNull List<Object> objectList, Condition cond, String leftExpr) {
    if (Keyword.BETWEEN.equalsIgnoreCase(cond.getOp())) {
      if (cond.getCl() != null && cond.getCl().size() == 2) {
        list.add(leftExpr + String0.BLANK + cond.getOp() + String0.BLANK + String0.QUESTION + String0.BLANK + Keyword.AND + String0.BLANK + String0.QUESTION);
        objectList.addAll(cond.getCl());
      }
    } else if (Keyword.IN.equalsIgnoreCase(cond.getOp())) {
      if (cond.getCl() != null && cond.getCl().size() > 0) {
        list.add(leftExpr + String0.BLANK + cond.getOp() + String0.BLANK + String0.OPEN_PARENTHESIS + String.join(String0.COMMA, Collections.nCopies(cond.getCl().size(), String0.QUESTION)) + String0.CLOSE_PARENTHESIS);
        objectList.addAll(cond.getCl());
      }
    } else {
      list.add(leftExpr + String0.BLANK + cond.getOp() + String0.BLANK + String0.QUESTION);
      objectList.add(String0.nullToEmpty(cond.getBw()) + cond.getCs() + String0.nullToEmpty(cond.getEw()));
    }
  }

  public String fullTableName() {
    return String0.notNull2EmptyTo(String0.nullToEmpty(this.getJavaTable().schema()), this.getJavaTable().schema() + String0.DOT) + this.getDbTableName();
  }

  public List<String> lstSelectFiled() {
    return this.getSelectList() != null && this.getSelectList().size() > 0 ? this.getSelectList() : this.getFieldNameList();
  }

  public void mapRow(ResultSet rs) {
    String columnFieldTypeString;
    Object o;
    for (String fieldName : this.lstSelectFiled()) {
      try {
        columnFieldTypeString = this.getFieldMap().get(fieldName).getType().getCanonicalName();
        if (Integer.class.getCanonicalName().equals(columnFieldTypeString)) {
          o = rs.getInt(this.getDbColumnMap().get(fieldName));
        } else {
          o = rs.getString(this.getDbColumnMap().get(fieldName));
        }
        if (o != null) {
          this.getClass().getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), o.getClass()).invoke(this, o);
        }
      } catch (Exception e) {
        log.error(e.getMessage(), e);
      }
    }
  }

  //crud
  public Tuple.Pair<String, List<Object>> deleteSql() {
    List<Object> rtnObjectList = List0.newArrayList();

    List<String> sqlList = List0.newArrayList();
    sqlList.add(Keyword.DELETE_FROM);
    sqlList.add(this.fullTableName());

    List<String> whereList = List0.newArrayList();
    whereStatement(whereList, rtnObjectList);
    if (whereList.size() > 0) {
      sqlList.add(Keyword.WHERE);
      sqlList.add(String.join(Keyword.AND_WITH_BLACK_PREFIX_WITH_BLACK_SUFFIX, whereList));
    }
    return Tuple.of(String.join(String0.BLANK, sqlList), rtnObjectList);
  }

  public Tuple.Pair<String, List<Object>> insertSql() {
    List<Object> rtnObjectList = List0.newArrayList();

    List<String> insertList = List0.newArrayList();
    insertStatement(insertList, rtnObjectList);

    List<String> sqlList = List0.newArrayList();
    sqlList.add(Keyword.INSERT_INFO);
    sqlList.add(this.fullTableName());
    sqlList.add(String0.OPEN_PARENTHESIS + String.join(String0.COMMA, insertList) + String0.CLOSE_PARENTHESIS);
    sqlList.add(Keyword.VALUES);
    sqlList.add(String0.OPEN_PARENTHESIS + String0.repeat(String0.COMMA + String0.QUESTION, insertList.size()).substring(1) + String0.CLOSE_PARENTHESIS);

    return Tuple.of(String.join(String0.BLANK, sqlList), rtnObjectList);
  }

  public void insertStatement(@NonNull List<String> insertList, @NonNull List<Object> objectList) {
    Object o;
    for (String fieldName : this.getFieldNameList()) {
      try {
        o = this.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).invoke(this);
      } catch (Exception e) {
        o = null;
        log.error(e.toString());
      }
      if (!String0.isNullOrEmpty(String.valueOf(o))) {
        insertList.add(this.getDbColumnMap().get(fieldName));
        objectList.add(o);
      }
    }
  }

  public Tuple.Pair<String, List<Object>> selectCountSql() {
    Tuple.Pair<List<String>, List<Object>> pair = this.selectSql(List0.newArrayList(Keyword.COUNT_1_), List0.newArrayList());
    return Tuple.of(String.join(String0.BLANK, Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<String, List<Object>> selectIdsSql() {
    Tuple.Pair<List<String>, List<Object>> pair = this.selectSql(List0.newArrayList(Keyword.GROUP__CONCAT_ID_), List0.newArrayList());
    return Tuple.of(String.join(String0.BLANK, Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<String, List<Object>> selectSql() {
    List<Object> rtnObjectList = List0.newArrayList();

    List<String> selectList = List0.newArrayList();
    selectStatement(selectList, rtnObjectList);

    Tuple.Pair<List<String>, List<Object>> pair = this.selectSql(selectList, rtnObjectList);
    return Tuple.of(String.join(String0.BLANK, Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<List<String>, List<Object>> selectSql(@NonNull List<String> selectList, @NonNull List<Object> selectObjectList) {
    List<Object> rtnObjectList = List0.newArrayList();
    rtnObjectList.addAll(selectObjectList);

    List<String> fromList = List0.newArrayList();
    fromStatement(fromList, rtnObjectList);

    List<String> whereList = List0.newArrayList();
    whereStatement(whereList, rtnObjectList);

    List<String> groupByList = List0.newArrayList();
    groupByStatement(groupByList, rtnObjectList);

    List<String> havingList = List0.newArrayList();
    havingStatement(havingList, rtnObjectList);

    List<String> orderByList = List0.newArrayList();
    orderByStatement(orderByList, rtnObjectList);

    return selectSql(rtnObjectList, selectList, fromList, whereList, groupByList, havingList, orderByList);
  }

  public Tuple.Pair<List<String>, List<Object>> selectSql(@NonNull List<Object> objectList, @NonNull List<String> selectList, @NonNull List<String> fromList, List<String> whereList, List<String> groupByList, List<String> havingList, List<String> orderByList) {
    List<Object> rtnObjectList = List0.newArrayList();

    List<String> sqlList = List0.newArrayList();
    sqlList.add(Keyword.SELECT);
    sqlList.add(String.join(String0.COMMA, selectList));
    sqlList.add(Keyword.FROM);
    sqlList.add(String.join(String0.COMMA, fromList));
    if (whereList != null && whereList.size() > 0) {
      sqlList.add(Keyword.WHERE);
      sqlList.add(String.join(Keyword.AND_WITH_BLACK_PREFIX_WITH_BLACK_SUFFIX, whereList));
    }
    if (groupByList != null && groupByList.size() > 0) {
      sqlList.add(Keyword.GROUP_BY);
      sqlList.add(String.join(String0.COMMA, groupByList));
    }
    if (havingList != null && havingList.size() > 0) {
      sqlList.add(Keyword.HAVING);
      sqlList.add(String.join(Keyword.AND_WITH_BLACK_PREFIX_WITH_BLACK_SUFFIX, havingList));
    }
    if (orderByList != null && orderByList.size() > 0) {
      sqlList.add(Keyword.ORDER_BY);
      sqlList.add(String.join(String0.COMMA, orderByList));
    }
    rtnObjectList.addAll(objectList);

    List<String> limitList = List0.newArrayList();
    limitStatement(limitList, rtnObjectList);
    if (limitList.size() > 0) {
      sqlList.add(String.join(String0.BLANK, limitList));
    }
    return Tuple.of(sqlList, rtnObjectList);
  }

  public void selectStatement(@NonNull List<String> selectList, @NonNull List<Object> objectList) {
    //count(1)
    selectList.addAll(this.lstSelectFiled().stream().map((String fieldName) -> String0.null2EmptyTo(this.getDbColumnMap().get(fieldName), fieldName)).collect(Collectors.toList()));
  }

  public Tuple.Pair<String, List<Object>> updateSql() {
    List<Object> rtnObjectList = List0.newArrayList();

    List<String> updateList = List0.newArrayList();
    updateStatement(updateList, rtnObjectList);

    List<String> whereList = List0.newArrayList();
    whereEntityStatement(whereList, rtnObjectList, this.getIdFieldNameList());
    whereEntityStatement(whereList, rtnObjectList, this.getVerFieldNameList());
    whereConditionsStatement(whereList, rtnObjectList, this.getFieldNameList());

    List<String> sqlList = List0.newArrayList();
    sqlList.add(Keyword.UPDATE);
    sqlList.add(this.fullTableName());
    sqlList.add(Keyword.SET);
    sqlList.add(String.join(String0.COMMA, updateList));
    if (whereList.size() > 0) {
      sqlList.add(Keyword.WHERE);
      sqlList.add(String.join(Keyword.AND_WITH_BLACK_PREFIX_WITH_BLACK_SUFFIX, whereList));
    }
    return Tuple.of(String.join(String0.BLANK, sqlList), rtnObjectList);
  }

  public void updateStatement(@NonNull List<String> updateList, @NonNull List<Object> objectList) {
    Object o = null;
    for (String fieldName : this.getFieldNameList().stream().filter(fieldName -> !this.getIdFieldNameList().contains(fieldName) && this.getColumnMap().get(fieldName).updatable()).collect(Collectors.toList())) {
      try {
        o = this.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).invoke(this);
      } catch (Exception e) {
        o = null;
        log.error(e.toString());
      }
      if (o != null) {//can update to null
        updateList.add(this.getDbColumnMap().get(fieldName) + String0.EQUAL + String0.QUESTION);
        if (!this.getVerFieldNameList().contains(fieldName)) {
          objectList.add(o);
        } else {
          objectList.add((Integer) o + 1);
        }
      }
    }
  }

  //other statements
  public void fromStatement(@NonNull List<String> fromList, @NonNull List<Object> objectList) {
    fromList.add(this.fullTableName());
  }

  public void groupByStatement(@NonNull List<String> groupByList, @NonNull List<Object> objectList) {
    if (this.getGroupByList() != null) {
      groupByList.addAll(this.getGroupByList());
    }
  }

  public void havingStatement(@NonNull List<String> havingList, @NonNull List<Object> objectList) {
    if (this.getHavingConditions() != null) {
      this.havingStatement(havingList, objectList, this.getFieldNameList());
    }
  }

  public void havingStatement(@NonNull List<String> havingList, @NonNull List<Object> objectList, @NonNull List<String> fieldNameList) {
    for (String fieldName : fieldNameList) {
      if (this.getColumnMap().get(fieldName) != null) {
        for (Condition cond : this.findHavingConditions(fieldName)) {
          this.fillOc(havingList, objectList, cond, String0.null2EmptyTo(cond.getLe(), this.getDbColumnMap().get(fieldName)));
        }
      }
    }
  }

  public void orderByStatement(@NonNull List<String> orderByList, @NonNull List<Object> objectList) {
    if (this.getOrderByList() != null) {
      orderByList.addAll(this.getOrderByList());
    }
  }

  public void whereEntityStatement(@NonNull List<String> whereList, @NonNull List<Object> objectList, @NonNull List<String> fieldNameList) {
    Object o = null;
    for (String fieldName : fieldNameList) {
      try {
        o = this.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).invoke(this);
      } catch (Exception e) {
        o = null;
        log.error(e.toString());
      }
      if (this.getColumnMap().get(fieldName) != null) {
        if (o != null) {//whereConditions support empty
          whereList.add(this.getDbColumnMap().get(fieldName) + String0.EQUAL + String0.QUESTION);
          objectList.add(o);
        }
      }
    }
  }

  public void whereConditionsStatement(@NonNull List<String> whereList, @NonNull List<Object> objectList, @NonNull List<String> fieldNameList) {
    for (String fieldName : fieldNameList) {
      for (Condition cond : this.findWhereConditions(fieldName)) {
        this.fillOc(whereList, objectList, cond, String0.null2EmptyTo(cond.getLe(), this.getDbColumnMap().get(fieldName)));
      }
    }
  }

  public void whereStatement(@NonNull List<String> whereList, @NonNull List<Object> objectList) {
    this.whereEntityStatement(whereList, objectList, this.getFieldNameList());
    this.whereConditionsStatement(whereList, objectList, this.getFieldNameList());
  }
}
