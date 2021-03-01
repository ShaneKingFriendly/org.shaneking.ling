package org.shaneking.ling.persistence.entity.sql;

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
}
