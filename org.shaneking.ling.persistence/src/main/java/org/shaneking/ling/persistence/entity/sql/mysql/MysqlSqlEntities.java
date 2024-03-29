package org.shaneking.ling.persistence.entity.sql.mysql;

import org.shaneking.ling.persistence.Keyword;
import org.shaneking.ling.persistence.entity.SqlEntities;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.text.MF0;
import org.shaneking.ling.zero.util.List0;

import javax.persistence.Id;
import javax.persistence.Lob;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface MysqlSqlEntities extends SqlEntities {
  default String createColumnStatement(String columnName, boolean idOrVersion) {
    String rtn, columnDbTypeString;
    Field columnField = this.getFieldMap().get(columnName);
    String partNotNull = (idOrVersion || !this.getColumnMap().get(columnName).nullable()) ? Keyword.NOT_NULL_WITH_BLACK_PREFIX : String0.EMPTY;
    if (columnField.getAnnotation(Lob.class) != null) {
      columnDbTypeString = Keyword.TYPE_LONGTEXT;
    } else if (Integer.class.getCanonicalName().equals(columnField.getType().getCanonicalName())) {
      columnDbTypeString = Keyword.TYPE_INT;
    } else if (columnField.getAnnotation(Id.class) != null && String.class.getCanonicalName().equals(columnField.getType().getCanonicalName())) {
      columnDbTypeString = Keyword.TYPE_CHAR;//fixed length is good for primary key id.
    } else {
      columnDbTypeString = Keyword.TYPE_VARCHAR;
    }
    String[] comments = String0.nullToEmpty(this.getColumnMap().get(columnName).columnDefinition()).split(Keyword.COMMENT.toUpperCase());
    String commentWithBlackPrefix = comments.length > 1 ? comments[1] : EMPTY_COMMENT_WITH_BLACK__PREFIX;
    String commentBefore = String0.nullToEmpty(comments[0]);
    if (Keyword.TYPE_LONGTEXT.equals(columnDbTypeString) || Keyword.TYPE_INT.equals(columnDbTypeString)) {
      rtn = MF0.fmt("  `{0}` {1}{2} {3}{4}{5},", this.getDbColumnMap().get(columnName), columnDbTypeString, partNotNull, commentBefore, Keyword.COMMENT, commentWithBlackPrefix);
    } else {
      rtn = MF0.fmt("  `{0}` {1}({2}){3} {4}{5}{6},", this.getDbColumnMap().get(columnName), columnDbTypeString, String.valueOf(this.getColumnMap().get(columnName).length()), partNotNull, commentBefore, Keyword.COMMENT, commentWithBlackPrefix);
    }
    return rtn;
  }

  default String createIndexIfNotExistSql() {
    String idxSqls = createIndexSql(true);
    if (!String0.isNullOrEmpty(idxSqls)) {
      idxSqls = "drop procedure if exists p_" + this.getDbTableName() + "_idx_create;" + String0.BR_LINUX +
        "delimiter $$" + String0.BR_LINUX +
        "create procedure p_" + this.getDbTableName() + "_idx_create() begin" + String0.BR_LINUX +
        idxSqls + String0.BR_LINUX +
        "end;" + String0.BR_LINUX +
        "$$" + String0.BR_LINUX +
        "delimiter ;" + String0.BR_LINUX +
        "call p_" + this.getDbTableName() + "_idx_create();" + String0.BR_LINUX +
        "drop procedure if exists p_" + this.getDbTableName() + "_idx_create;" + String0.BR_LINUX;
    }
    return idxSqls;
  }

  default String createIndexSql() {
    return createIndexSql(false);
  }

  default String createIndexSql(boolean ifNotExists) {
    List<String> indexStatementList = List0.newArrayList();

    Map<String, List<String>> uniIdxMap = genTableUniIdxMap();
    String idxSchemaPart = String0.notNull2EmptyTo(String0.nullToEmpty(this.getJavaTable().schema()), MF0.fmt("`{0}`.", this.getJavaTable().schema()));
    uniIdxMap.forEach((idxPartName, columnList) -> {
      String indexColumns = "`" + (columnList.size() > 1 ? String.join("` asc, `", columnList) : columnList.get(0)) + "` asc";
      indexStatementList.add(createTableIndexSql(ifNotExists, MF0.fmt("{0} {1}`{2}` {3} `{4}` ({5});", Keyword.ALTER_TABLE, idxSchemaPart, this.getDbTableName(), Keyword.ADD_UNIQUE_INDEX, UNIQUE_INDEX_NAME__PREFIX + idxPartName, indexColumns), UNIQUE_INDEX_NAME__PREFIX + idxPartName));
    });

    Map<String, String> idxMap = genTableIdxMap();
    idxMap.forEach((idxName, columnString) -> {
      indexStatementList.add(createTableIndexSql(ifNotExists, MF0.fmt("{0} {1}`{2}` {3} `{4}` ({5});", Keyword.ALTER_TABLE, idxSchemaPart, this.getDbTableName(), Keyword.ADD_INDEX, idxName, columnString), idxName));
    });

    return String.join(String0.BR_LINUX, indexStatementList);
  }

  default String createTableAndIndexIfNotExistSql() {
    String rtn = createTableIfNotExistSql() + String0.BR_LINUX;
    String idxSqls = createIndexIfNotExistSql();
    if (!String0.isNull2Empty(idxSqls)) {
      rtn = rtn + String0.BR_LINUX + idxSqls;
    }
    return rtn;
  }

  default String createTableIfNotExistSql() {
    return createTableSql();
  }

  default String createTableIndexSql(boolean ifNotExists, String idxSql, String idxName) {
    if (ifNotExists) {
      return "if not exists (select * from information_schema.statistics where table_schema = '" + this.getJavaTable().schema() + "' and table_name = '" + this.getDbTableName() + "' and index_name = '" + idxName + "')" + String0.BR_LINUX +
        "then" + String0.BR_LINUX +
        idxSql + String0.BR_LINUX +
        "end if;";
    } else {
      return idxSql;
    }
  }

  default String createTableSql() {
    List<String> sqlList = List0.newArrayList();
    String idxSchemaPart = String0.notNull2EmptyTo(String0.nullToEmpty(this.getJavaTable().schema()), MF0.fmt("`{0}`.", this.getJavaTable().schema()));
    sqlList.add(MF0.fmt("{0} {1} {2}`{3}` (", Keyword.CREATE_TABLE, Keyword.IF_NOT_EXISTS, idxSchemaPart, this.getDbTableName()));
    return createTableSql(sqlList);
  }
}
