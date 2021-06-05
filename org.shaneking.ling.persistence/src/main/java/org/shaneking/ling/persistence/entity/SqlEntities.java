package org.shaneking.ling.persistence.entity;

import lombok.NonNull;
import org.shaneking.ling.persistence.Entities;
import org.shaneking.ling.persistence.Keyword;
import org.shaneking.ling.persistence.Pagination;
import org.shaneking.ling.zero.lang.Integer0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.persistence.Tuple;
import org.shaneking.ling.zero.text.MF0;
import org.shaneking.ling.zero.util.LW;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import javax.persistence.Transient;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
MySQL Named Length Limit(https://help.aliyun.com/document_detail/96709.html)
Identifier Type           Maximum Length (characters)
Database                  64 (NDB storage engine: 63)
Table                     64 (NDB storage engine: 63)
Column                    64
Index                     64
Constraint                64
Stored Program            64
View                      64
Tablespace                64
Server                    64
Log File Group            64
Alias                     256 (see exception following table)
Compound Statement Label  16
 */
public interface SqlEntities extends Entities {
  @Transient
  String EMPTY_COMMENT_WITH_BLACK__PREFIX = " ''";
  @Transient
  String INDEX_NAME__PREFIX = "idx_";
  @Transient
  String UNIQUE_INDEX_NAME__PREFIX = "u_" + INDEX_NAME__PREFIX;

  String createColumnStatement(String columnName, boolean idOrVersion);

  String createIndexSql();

  String createIndexIfNotExistSql();

  String createTableSql();

  String createTableIfNotExistSql();

  String createTableAndIndexIfNotExistSql();

  default Map<String, String> genTableIdxMap() {
    Map<String, String> rtn = Map0.newHashMap();
    if (this instanceof Deleted && ((Deleted) this).ddNeedCreateIdx()) {
      rtn.put(Deleted.COLUMN__DD, Deleted.COLUMN__DD);
    }
    List0.newArrayList(this.getJavaTable().indexes()).forEach(idx -> {
      rtn.put(idx.name(), idx.columnList());
    });
    Map<String, String> ext = genTableIdxMapExt();
    if (ext != null && ext.size() > 0) {
      rtn.putAll(ext);
    }

    return rtn;
  }

  default Map<String, String> genTableIdxMapExt() {
    return Map0.newHashMap();
  }

  default Map<String, List<String>> genTableUniIdxMap() {
    Map<String, List<String>> rtn = Map0.newHashMap();
    if (this instanceof Deleted && ((Deleted) this).ddNeedJoinUniIdx()) {
      List0.newArrayList(this.getJavaTable().uniqueConstraints()).stream().filter(uniqueConstraint -> uniqueConstraint.columnNames().length > 0).forEach(uniqueConstraint -> {
        List<String> list = LW.wrap(Deleted.COLUMN__DD).addAll(List0.newArrayList(uniqueConstraint.columnNames())).list();
        rtn.put(String.join(String0.UNDERLINE, list), list);
      });
      this.getFieldNameList().stream().filter(fieldName -> this.getColumnMap().get(fieldName).unique()).forEach(fieldName -> {
        List<String> list = LW.wrap(Deleted.COLUMN__DD).add(this.getDbColumnMap().get(fieldName)).list();
        rtn.put(String.join(String0.UNDERLINE, list), list);
      });
    } else {
      List0.newArrayList(this.getJavaTable().uniqueConstraints()).stream().filter(uniqueConstraint -> uniqueConstraint.columnNames().length > 0).forEach(uniqueConstraint -> {
        rtn.put(String.join(String0.UNDERLINE, uniqueConstraint.columnNames()), List0.newArrayList(uniqueConstraint.columnNames()));
      });
      this.getFieldNameList().stream().filter(fieldName -> this.getColumnMap().get(fieldName).unique()).forEach(fieldName -> {
        rtn.put(this.getDbColumnMap().get(fieldName), List0.newArrayList(this.getDbColumnMap().get(fieldName)));
      });
    }
    Map<String, List<String>> ext = genTableUniIdxMapExt();
    if (ext != null && ext.size() > 0) {
      if (this instanceof Deleted && ((Deleted) this).ddNeedJoinUniIdx()) {
        ext.forEach((key, value) -> rtn.put(String.join(String0.UNDERLINE, LW.wrap(Deleted.COLUMN__DD).add(key).list()), LW.wrap(Deleted.COLUMN__DD).addAll(value).list()));
      } else {
        rtn.putAll(ext);
      }
    }
    return rtn;
  }

  default Map<String, List<String>> genTableUniIdxMapExt() {
    return Map0.newHashMap();
  }

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
    sqlList.add(MF0.fmt("  {0} (`{1}`)", Keyword.PRIMARY_KEY, String.join("`,`", this.getIdFieldNameList().stream().map(idFieldName -> this.getDbColumnMap().get(idFieldName)).collect(Collectors.toList()))));
    sqlList.add(String0.CLOSE_PARENTHESIS + String0.SEMICOLON);
    return String.join(String0.BR_LINUX, sqlList);
  }

  default void limitStatement(@NonNull List<String> limitList, @NonNull List<Object> objectList) {
    Pagination pagination = this.getPagination() == null ? new Pagination() : this.getPagination();
    Integer limit = Integer0.gt2d(Integer0.null2Default(pagination.getSize(), Pagination.DEFAULT_SIZE), Pagination.MAX_SIZE);
    limitList.add(MF0.fmt("{0} {1}", Keyword.LIMIT, String.valueOf(limit)));//add String.valueOf to fix 1000+ to 1,000+
    limitList.add(MF0.fmt("{0} {1}", Keyword.OFFSET, String.valueOf(Integer0.lt2d((Integer0.null2Zero(pagination.getPage()) - 1) * limit, 0))));
  }

  ///
  Tuple.Pair<String, List<Object>> deleteSql();

  Tuple.Pair<String, List<Object>> insertSql();

  Tuple.Pair<String, List<Object>> selectCountSql();

  Tuple.Pair<String, List<Object>> selectIdsSql();

  Tuple.Pair<String, List<Object>> selectSql();

  Tuple.Pair<List<String>, List<Object>> selectSql(@NonNull List<String> selectList, @NonNull List<Object> selectObjectList);

  Tuple.Pair<List<String>, List<Object>> selectSql(@NonNull List<String> selectList, @NonNull List<Object> selectObjectList, boolean withoutLimit);

  Tuple.Pair<String, List<Object>> updateSql();
}
