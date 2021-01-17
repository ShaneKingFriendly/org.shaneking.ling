package org.shaneking.ling.persistence;

import lombok.NonNull;
import org.shaneking.ling.persistence.sql.Pagination;

import javax.persistence.Table;
import java.util.List;

public interface Sqler {
  String createTableIfNotExistSql();

  String createTableIndexSql();

  String createTableSql();

  String getDbTableName();

  Table getJavaTable();

  Pagination getPagination();

  void limitStatement(@NonNull List<String> limitList, @NonNull List<Object> objectList);
}
