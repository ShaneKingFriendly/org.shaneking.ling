package org.shaneking.ling.persistence.entity.sql.sqllite;

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

//some junit test without remote db server
public interface SqlliteSqlEntities extends SqlEntities {
  default String createColumnStatement(String columnName, boolean idOrVersion) {
    String rtn, columnDbTypeString;
    Field columnField = this.getFieldMap().get(columnName);
    String partNotNull = (idOrVersion || !this.getColumnMap().get(columnName).nullable()) ? Keyword.NOT_NULL_WITH_BLACK_PREFIX : String0.EMPTY;
    if (columnField.getAnnotation(Lob.class) != null) {
      columnDbTypeString = Keyword.TYPE_TEXT;
    } else if (Integer.class.getCanonicalName().equals(columnField.getType().getCanonicalName())) {
      columnDbTypeString = Keyword.TYPE_INT;
    } else if (columnField.getAnnotation(Id.class) != null && String.class.getCanonicalName().equals(columnField.getType().getCanonicalName())) {
      columnDbTypeString = Keyword.TYPE_CHAR;//fixed length is good for primary key id.
    } else {
      columnDbTypeString = Keyword.TYPE_VARCHAR;
    }
    String[] comments = String0.nullToEmpty(this.getColumnMap().get(columnName).columnDefinition()).split(Keyword.COMMENT.toUpperCase());
    String commentBefore = String0.nullToEmpty(comments[0]).trim();
    commentBefore = String0.isNullOrEmpty(commentBefore) ? commentBefore : (String0.BLANK + commentBefore);
    if (Keyword.TYPE_TEXT.equals(columnDbTypeString) || Keyword.TYPE_INT.equals(columnDbTypeString)) {
      rtn = MF0.fmt("  `{0}` {1}{2}{3},", this.getDbColumnMap().get(columnName), columnDbTypeString, partNotNull, commentBefore);
    } else {
      rtn = MF0.fmt("  `{0}` {1}({2}){3}{4},", this.getDbColumnMap().get(columnName), columnDbTypeString, String.valueOf(this.getColumnMap().get(columnName).length()), partNotNull, commentBefore);
    }
    return rtn;
  }

  default String createIndexIfNotExistSql() {
    return createIndexSql();
  }

  default String createIndexSql() {
    List<String> indexStatementList = List0.newArrayList();

    Map<String, List<String>> uniIdxMap = genTableUniIdxMap();
    uniIdxMap.forEach((idxPartName, columnList) -> {
      String indexColumns = "`" + (columnList.size() > 1 ? String.join("`,`", columnList) : columnList.get(0)) + "`";
      indexStatementList.add(MF0.fmt("{0} {1} {2} on {3}({4});", Keyword.CREATE_UNIQUE_INDEX, Keyword.IF_NOT_EXISTS, UNIQUE_INDEX_NAME__PREFIX + idxPartName, this.getDbTableName(), indexColumns));
    });

    Map<String, String> idxMap = genTableIdxMap();
    idxMap.forEach((idxName, columnString) -> {
      indexStatementList.add(MF0.fmt("{0} {1} {2} on {3}({4});", Keyword.CREATE_INDEX, Keyword.IF_NOT_EXISTS, idxName, this.getDbTableName(), columnString));
    });

    return String.join(String0.BR_LINUX, indexStatementList);
  }

  default String createTableSql() {
    List<String> sqlList = List0.newArrayList();
    sqlList.add(MF0.fmt("{0} {1} `{2}` (", Keyword.CREATE_TABLE, Keyword.IF_NOT_EXISTS, this.getDbTableName()));
    return createTableSql(sqlList);
  }

  default String createTableAndIndexIfNotExistSql() {
    String idxSqls = createIndexIfNotExistSql();
    idxSqls = String0.isNull2Empty(idxSqls) ? String0.EMPTY : (idxSqls + String0.BR_LINUX);
    return createTableIfNotExistSql() + String0.BR_LINUX + String0.BR_LINUX + idxSqls;
  }

  default String createTableIfNotExistSql() {
    return createTableSql();
  }
}
