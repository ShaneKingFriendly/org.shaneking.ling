package org.shaneking.ling.persistence.sql.entity;

import lombok.NonNull;
import org.shaneking.ling.persistence.sql.Keyword;
import org.shaneking.ling.persistence.sql.Pagination;
import org.shaneking.ling.persistence.sql.SqlEntities;
import org.shaneking.ling.zero.lang.Integer0;

import javax.persistence.Transient;
import java.text.MessageFormat;
import java.util.List;

public interface DialectSqlEntities extends SqlEntities {
  @Transient
  String EMPTY_COMMENT_WITH_BLACK_PREFIX = " ''";
  @Transient
  String UNIQUE_INDEX_NAME_PREFIX = "u_idx_";

  String createColumnStatement(String columnName, boolean idOrVersion);

  String createTableIfNotExistSql();

  String createTableIndexSql();

  String createTableSql();

  default void limitStatement(@NonNull List<String> limitList, @NonNull List<Object> objectList) {
    Pagination pagination = this.getPagination() == null ? new Pagination() : this.getPagination();
    Integer limit = Integer0.gt2d(Integer0.null2Default(pagination.getSize(), Pagination.DEFAULT_SIZE), Pagination.MAX_SIZE);
    limitList.add(MessageFormat.format("{0} {1}", Keyword.LIMIT, String.valueOf(limit)));//add String.valueOf to fix 1000+ to 1,000+
    limitList.add(MessageFormat.format("{0} {1}", Keyword.OFFSET, String.valueOf(Integer0.lt2d((Integer0.null2Zero(pagination.getPage()) - 1) * limit, 0))));
  }
}
