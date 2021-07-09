package org.shaneking.ling.persistence.entity;

import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Transient;

public interface Named {
  @Transient
  String ERR_CODE__NOT_FOUND_BY_NAME = "NAMED__NOT_FOUND_BY_NAME";

  @Transient
  String COLUMN__NAME = "name";
  @Transient
  String FIELD__NAME = "name";

  String getName();

  <T extends Named> T setName(String name);

  //set if nullOrEmpty
  default <T extends Named> T sinName(String name) {
    return setName(String0.nullOrEmptyTo(getName(), name));
  }
}
