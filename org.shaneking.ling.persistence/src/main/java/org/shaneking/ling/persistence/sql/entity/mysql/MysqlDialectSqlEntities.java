package org.shaneking.ling.persistence.sql.entity.mysql;

import org.shaneking.ling.persistence.sql.Keyword;
import org.shaneking.ling.persistence.sql.entity.DialectSqlEntities;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import javax.persistence.Lob;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MysqlDialectSqlEntities extends DialectSqlEntities {
  default String createColumnStatement(String columnName, boolean idOrVersion) {
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

  default String createTableIfNotExistSql() {
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

  default String createTableIndexSql() {
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

  default String createTableSql() {
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
}
