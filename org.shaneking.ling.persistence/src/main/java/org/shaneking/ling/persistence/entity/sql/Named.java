package org.shaneking.ling.persistence.entity.sql;

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
}
