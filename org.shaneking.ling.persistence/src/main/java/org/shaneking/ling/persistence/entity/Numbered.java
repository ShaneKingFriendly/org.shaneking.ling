package org.shaneking.ling.persistence.entity;

import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Transient;

public interface Numbered {
  @Transient
  String ERR_CODE__NOT_FOUND_BY_NUMBER = "NUMBERED__NOT_FOUND_BY_NUMBER";

  @Transient
  String COLUMN__NO = "no";
  @Transient
  String FIELD__NO = "no";

  String getNo();

  <T extends Numbered> T setNo(String no);

  //set if nullOrEmpty
  default <T extends Numbered> T sinNo(String no) {
    return setNo(String0.nullOrEmptyTo(getNo(), no));
  }
}
