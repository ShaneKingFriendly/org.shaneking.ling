package org.shaneking.ling.persistence;

import javax.persistence.Transient;

//uni id in all tenant
public interface Uniquely {
  @Transient
  String COLUMN__UID = "uid";
  @Transient
  String FIELD__UID = "uid";

  String getUid();

  <T extends Uniquely> T setUid(String uid);
}
