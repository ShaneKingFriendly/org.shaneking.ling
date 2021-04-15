package org.shaneking.ling.persistence.entity;

import lombok.NonNull;
import org.shaneking.ling.persistence.Entities;
import org.shaneking.ling.persistence.Keyword;
import org.shaneking.ling.persistence.Pagination;
import org.shaneking.ling.zero.lang.Integer0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.persistence.Tuple;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import javax.persistence.Transient;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface SqlEntities extends Entities {
  @Transient
  String EMPTY_COMMENT_WITH_BLACK__PREFIX = " ''";
  @Transient
  String INDEX_NAME__PREFIX = "idx_";
  @Transient
  String UNIQUE_INDEX_NAME__PREFIX = "u_" + INDEX_NAME__PREFIX;

  String createColumnStatement(String columnName, boolean idOrVersion);

  String createTableIfNotExistSql();

  String createTableIndexSql();

  default Map<String, String> genTableIdxMap() {
    Map<String, String> rtn = Map0.newHashMap();
    List0.newArrayList(this.getJavaTable().indexes()).stream().forEach(idx -> {
      rtn.put(idx.name(), idx.columnList());
    });
    return rtn;
  }

  default Map<String, List<String>> genTableUniIdxMap() {
    Map<String, List<String>> rtn = Map0.newHashMap();
    List0.newArrayList(this.getJavaTable().uniqueConstraints()).stream().filter(uniqueConstraint -> uniqueConstraint.columnNames().length > 0).forEach(uniqueConstraint -> {
      rtn.put(String.join(String0.UNDERLINE, uniqueConstraint.columnNames()), List0.newArrayList(uniqueConstraint.columnNames()));
    });
    this.getFieldNameList().stream().filter(fieldName -> this.getColumnMap().get(fieldName).unique()).forEach(fieldName -> {
      rtn.put(this.getDbColumnMap().get(fieldName), List0.newArrayList(this.getDbColumnMap().get(fieldName)));
    });
    return rtn;
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

  ///
  Tuple.Pair<String, List<Object>> deleteSql();

  Tuple.Pair<String, List<Object>> insertSql();

  Tuple.Pair<String, List<Object>> selectCountSql();

  Tuple.Pair<String, List<Object>> selectIdsSql();

  Tuple.Pair<String, List<Object>> selectSql();

  Tuple.Pair<List<String>, List<Object>> selectSql(@NonNull List<String> selectList, @NonNull List<Object> selectObjectList);

  Tuple.Pair<String, List<Object>> updateSql();
}
