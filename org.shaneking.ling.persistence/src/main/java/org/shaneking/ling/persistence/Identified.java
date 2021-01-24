package org.shaneking.ling.persistence;

import javax.persistence.Transient;

public interface Identified {
  @Transient
  String COLUMN__TID = "tid";
  @Transient
  String FIELD__TID = "tid";

  String getTid();

  <T extends Identified> T setTid(String tid);
}
