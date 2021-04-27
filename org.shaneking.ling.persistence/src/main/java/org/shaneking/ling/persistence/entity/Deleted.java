package org.shaneking.ling.persistence.entity;

import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Transient;

public interface Deleted {
  @Transient
  String ERR_CODE__DATA_CAN_NO_LONGER_BE_USED = "NUMBERED__DATA_CAN_NO_LONGER_BE_USED";

  @Transient
  String COLUMN__DD = "dd";
  @Transient
  String FIELD__DD = "dd";

  String getDd();

  <T extends Deleted> T setDd(String dd);

  //set if nullOrEmpty
  default <T extends Deleted> T sinDd(String dd) {
    return setDd(String0.nullOrEmptyTo(getDd(), dd));
  }
}
