package org.shaneking.ling.persistence;

import javax.persistence.Transient;

//uni name in all tenant
public interface UniNamed {
  @Transient
  String ERR_CODE__NOT_FOUND_BY_NAME = "UNI_NAMED__NOT_FOUND_BY_NAME";
  @Transient
  String COLUMN__UNAME = "uname";
  @Transient
  String FIELD__UNAME = "uname";

  String getUname();

  <T extends UniNamed> T setUname(String uname);
}
