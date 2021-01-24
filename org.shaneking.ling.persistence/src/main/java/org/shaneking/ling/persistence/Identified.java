package org.shaneking.ling.persistence;

import javax.persistence.Transient;

public interface Identified {
  @Transient
  String COLUMN__BID = "bid";
  @Transient
  String FIELD__BID = "bid";

  String getBid();

  <T extends Identified> T setBid(String bid);
}
