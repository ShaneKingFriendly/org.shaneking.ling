package org.shaneking.ling.persistence;

import org.shaneking.ling.persistence.sql.Pagination;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface Entities {
  Map<String, Column> getColumnMap();

  Map<String, String> getDbColumnMap();

  String getDbTableName();

  Map<String, Field> getFieldMap();

  List<String> getFieldNameList();

  List<String> getIdFieldNameList();

  Table getJavaTable();

  Pagination getPagination();

  List<String> getVerFieldNameList();
}
