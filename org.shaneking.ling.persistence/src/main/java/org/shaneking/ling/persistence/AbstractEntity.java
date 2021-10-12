package org.shaneking.ling.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.persistence.Pagination;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;
import org.shaneking.ling.zero.util.Regex0;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Design for has cache want single table operation
 */
@Accessors(chain = true)
@Slf4j
public abstract class AbstractEntity<J> implements Entities {
  @Getter
  @JsonIgnore
  @Schema(hidden = true)
  @Transient
  private final List<String> fieldNameList = List0.newArrayList();
  @Getter
  @JsonIgnore
  @Schema(hidden = true)
  @Transient
  private final List<String> idFieldNameList = List0.newArrayList();
  @Getter
  @JsonIgnore
  @Schema(hidden = true)
  @Transient
  private final List<String> verFieldNameList = List0.newArrayList();
  @Getter
  @JsonIgnore
  @Schema(hidden = true)
  @Transient
  private final Map<String, Column> columnMap = Map0.newHashMap();
  @Getter
  @JsonIgnore
  @Schema(hidden = true)
  @Transient
  private final Map<String, String> dbColumnMap = Map0.newHashMap();
  @Getter
  @JsonIgnore
  @Schema(hidden = true)
  @Transient
  private final Map<String, Field> fieldMap = Map0.newHashMap();
  @Getter
  @JsonIgnore
  @Schema(hidden = true)
  @Setter
  @Transient
  private String dbTableName;
  @Getter
  @Schema(hidden = true)
  @Setter
  @Transient
  private List<String> groupByList;
  @Getter
  @Schema(hidden = true)
  @Setter
  @Transient
  private List<String> orderByList;
  @Getter
  @Schema(hidden = true)
  @Setter
  @Transient
  private List<String> selectList;
  @Getter
  @Schema(hidden = true)
  @Setter
  @Transient
  private J havingConditions;
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
  @Getter
  @Setter
  @Transient
  private Pagination pagination;
  @Getter
  @JsonIgnore
  @Schema(hidden = true)
  @Setter
  @Transient
  private Table javaTable;

  public AbstractEntity() {
    initTableInfo();
    initColumnInfo(this.getClass());
  }

  //maybe no primary index, no union index, just have `dd` non-union index
  public String deletedFullTableName() {
    return fullTableName() + "_d";
  }

  public Map<String, Object> fieldNameValues() {
    Map<String, Object> rtn = Map0.newHashMap();
    Object o;
    for (String fieldName : this.getFieldNameList()) {
      try {
        o = this.getClass().getMethod("get" + String0.upperFirst(fieldName)).invoke(this);
      } catch (Exception e) {
        o = null;
        ///ignore exception : config error, can't stop business, developer can be see and fixed by log
        log.warn(OM3.lp(o, fieldName), e);
      }
      if (o != null) {
        rtn.put(fieldName, o);
      }
    }
    return rtn;
  }

  public void fillOc(@NonNull List<String> list, @NonNull List<Object> objectList, Condition cond, String leftExpr) {
    if (String0.isNullOrEmpty(cond.getSq())) {
      if (Keyword.BETWEEN.equalsIgnoreCase(cond.getOp())) {
        if (cond.getCl() != null && cond.getCl().size() == 2) {
          list.add(leftExpr + String0.BLANK + cond.getOp() + String0.BLANK + String0.QUESTION + String0.BLANK + Keyword.AND + String0.BLANK + String0.QUESTION);
          objectList.addAll(cond.getCl());
        }
      } else if (Keyword.IN.equalsIgnoreCase(cond.getOp())) {
        if (cond.getCl() != null && cond.getCl().size() > 0) {
          if (cond.getCl().size() == 1) {
            list.add(leftExpr + String0.BLANK + String0.EQUAL + String0.BLANK + String0.QUESTION);
            objectList.add(cond.getCl().get(0));
          } else {
            list.add(leftExpr + String0.BLANK + cond.getOp() + String0.BLANK + String0.OPEN_PARENTHESIS + String.join(String0.COMMA, Collections.nCopies(cond.getCl().size(), String0.QUESTION)) + String0.CLOSE_PARENTHESIS);
            objectList.addAll(cond.getCl());
          }
        }
      } else {
        list.add(leftExpr + String0.BLANK + cond.getOp() + String0.BLANK + String0.QUESTION);
        objectList.add(String0.nullToEmpty(cond.getBw()) + cond.getCs() + String0.nullToEmpty(cond.getEw()));
      }
    } else {
      list.add(leftExpr + String0.BLANK + cond.getOp() + String0.BLANK + String0.OPEN_PARENTHESIS + cond.getSq() + String0.CLOSE_PARENTHESIS);
    }
  }

  public String fullTableName() {
    return String0.notNull2EmptyTo(String0.nullToEmpty(this.getJavaTable().schema()), this.getJavaTable().schema() + String0.DOT) + this.getDbTableName();
  }

  public void initColumnInfo(Class<? extends Object> entityClass) {
    if (AbstractEntity.class.isAssignableFrom(entityClass.getSuperclass())) {
      initColumnInfo(entityClass.getSuperclass());
    }
    for (Field field : entityClass.getDeclaredFields()) {
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

  public List<String> lstSelectFiled() {
    return this.getSelectList() != null && this.getSelectList().size() > 0 ? this.getSelectList() : this.getFieldNameList();
  }

  public void mapRow(ResultSet rs) {
    String columnFieldTypeString = null;
    Object o = null;
    for (String fieldName : this.lstSelectFiled()) {
      try {
        columnFieldTypeString = this.getFieldMap().get(fieldName).getType().getCanonicalName();
        if (Integer.class.getCanonicalName().equals(columnFieldTypeString)) {
          o = rs.getInt(this.getDbColumnMap().get(fieldName));
        } else {
          o = rs.getString(this.getDbColumnMap().get(fieldName));
        }
        if (o != null) {
          this.getClass().getMethod("set" + String0.upperFirst(fieldName), o.getClass()).invoke(this, o);
        }
      } catch (Exception e) {
        ///ignore exception : config error, can't stop business, developer can be see and fixed by log
        log.warn(OM3.lp(o, columnFieldTypeString), e);
      }
    }
  }

  public void srvSelectList(List<String> selectList) {
    setSelectList(selectList);
  }
}
