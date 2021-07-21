package org.shaneking.ling.persistence;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface Entities {
  @Transient
  String ERR_CODE__INVALID = "ENTITIES__INVALID";
  @Transient
  String ERR_CODE__NOT_FOUND = "ENTITIES__NOT_FOUND";

  List<Condition> findHavingConditions(@NonNull String fieldName);

  List<Condition> findWhereConditions(@NonNull String fieldName);

  Map<String, Column> getColumnMap();

  Map<String, String> getDbColumnMap();

  String getDbTableName();

  Map<String, Field> getFieldMap();

  List<String> getFieldNameList();

  List<String> getIdFieldNameList();

  Table getJavaTable();

  Pagination getPagination();

  <T extends Entities> T setPagination(Pagination pagination);

  List<String> getSelectList();

  List<String> getVerFieldNameList();

  String fullTableName();

  String deletedFullTableName();

  void mapRow(ResultSet rs);

  Map<String, Object> fieldNameValues();

  /// set return void
  // /Users/ShaneKing/sk.sync/space/web/com/github/ShaneKingFriendly/org.shaneking.ling/org.shaneking.ling.persistence/src/main/java/org/shaneking/ling/persistence/AbstractEntity.java:81:3
  // java: 名称冲突: org.shaneking.ling.persistence.AbstractEntity中的setSelectList(java.util.List<java.lang.String>)和org.shaneking.ling.persistence.Entities中的<T>setSelectList(java.util.List<java.lang.String>)具有相同疑符, 但两者均不覆盖对方
  void srvSelectList(List<String> selectList);

  default Pagination sroPagination(Pagination pagination) {
    Pagination rtn = getPagination();
    setPagination(pagination);
    return rtn;
  }

  default Entities nullSetter() {
    for (String fieldName : this.getFieldNameList()) {
      try {
        this.getClass().getMethod("set" + String0.upperFirst(fieldName), this.getFieldMap().get(fieldName).getType()).invoke(this, new Object[]{null});
      } catch (Exception e) {
        ///ignore exception : most scenario use in test case
      }
    }
    return this;
  }
}
