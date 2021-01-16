package org.shaneking.ling.persistence;

import org.shaneking.ling.persistence.sql.Pagination;

import javax.persistence.Table;

public interface Sqler {
  String createTableIndexSql();

  String createTableSql();

  String getDbTableName();

  Table getJavaTable();

  Pagination getPagination();
}
