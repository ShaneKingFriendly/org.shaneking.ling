package org.shaneking.ling.persistence.sql.entity;

import lombok.NonNull;
import org.shaneking.ling.persistence.sql.Keyword;
import org.shaneking.ling.persistence.sql.Pagination;
import org.shaneking.ling.persistence.sql.SqlEntities;
import org.shaneking.ling.zero.lang.Integer0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;

import javax.persistence.Transient;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface DialectSqlEntities extends SqlEntities {
  @Transient
  String EMPTY_COMMENT_WITH_BLACK_PREFIX = " ''";
  @Transient
  String UNIQUE_INDEX_NAME_PREFIX = "u_idx_";

  String createColumnStatement(String columnName, boolean idOrVersion);

  String createTableIfNotExistSql();

  String createTableIndexSql();

  default List<String> createTableIndexSql(Map<String, List<String>> idxPartNameColumnsMap) {
    List0.newArrayList(this.getJavaTable().uniqueConstraints()).stream().filter(uniqueConstraint -> uniqueConstraint.columnNames().length > 0).forEach(uniqueConstraint -> {
      idxPartNameColumnsMap.put(String.join(String0.UNDERLINE, uniqueConstraint.columnNames()), List0.newArrayList(uniqueConstraint.columnNames()));
    });
    this.getFieldNameList().stream().filter(fieldName -> this.getColumnMap().get(fieldName).unique()).forEach(fieldName -> {
      idxPartNameColumnsMap.put(this.getDbColumnMap().get(fieldName), List0.newArrayList(this.getDbColumnMap().get(fieldName)));
    });
    return List0.newArrayList();
  }

  String createTableSql();

  default String createTableSql(List<String> sqlList) {
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
    return String.join(String0.BR_LINUX, sqlList);
  }

  default void limitStatement(@NonNull List<String> limitList, @NonNull List<Object> objectList) {
    Pagination pagination = this.getPagination() == null ? new Pagination() : this.getPagination();
    Integer limit = Integer0.gt2d(Integer0.null2Default(pagination.getSize(), Pagination.DEFAULT_SIZE), Pagination.MAX_SIZE);
    limitList.add(MessageFormat.format("{0} {1}", Keyword.LIMIT, String.valueOf(limit)));//add String.valueOf to fix 1000+ to 1,000+
    limitList.add(MessageFormat.format("{0} {1}", Keyword.OFFSET, String.valueOf(Integer0.lt2d((Integer0.null2Zero(pagination.getPage()) - 1) * limit, 0))));
  }
}
