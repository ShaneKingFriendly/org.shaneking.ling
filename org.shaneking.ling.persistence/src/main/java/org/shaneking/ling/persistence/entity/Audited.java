package org.shaneking.ling.persistence.entity;

import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Transient;

public interface Audited {
  @Transient
  String COLUMN__INVALID = "invalid";
  @Transient
  String FIELD__INVALID = "invalid";
  @Transient
  String COLUMN__LAST_MODIFY_DATE_TIME = "last_modify_date_time";
  @Transient
  String FIELD__LAST_MODIFY_DATE_TIME = "lastModifyDateTime";
  @Transient
  String COLUMN__LAST_MODIFY_USER_ID = "last_modify_user_id";
  @Transient
  String FIELD__LAST_MODIFY_USER_ID = "lastModifyUserId";

  String getInvalid();

  <T extends Audited> T setInvalid(String invalid);

  String getLastModifyDateTime();

  <T extends Audited> T setLastModifyDateTime(String lastModifyDateTime);

  String getLastModifyUserId();

  <T extends Audited> T setLastModifyUserId(String lastModifyUserId);

  //set if nullOrEmptyTo
  default <T extends Audited> T sinInvalid(String invalid) {
    return setInvalid(String0.nullOrEmptyTo(getInvalid(), invalid));
  }
}
