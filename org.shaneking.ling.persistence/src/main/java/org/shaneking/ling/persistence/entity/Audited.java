package org.shaneking.ling.persistence.entity;

import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Transient;

public interface Audited {
  @Transient
  String COLUMN__IVD = "ivd";
  @Transient
  String COLUMN__LM_DSZ = "lm_dsz";
  @Transient
  String COLUMN__LM_UID = "lm_uid";
  @Transient
  String FIELD__IVD = "ivd";
  @Transient
  String FIELD__LM_DSZ = "lmDsz";
  @Transient
  String FIELD__LM_UID = "lmUid";

  String getIvd();

  <T extends Audited> T setIvd(String ivd);

  String getLmDsz();

  <T extends Audited> T setLmDsz(String lmDsz);

  String getLmUid();

  <T extends Audited> T setLmUid(String lmUid);

  //set if nullOrEmptyTo
  default <T extends Audited> T sinIvd(String ivd) {
    return setIvd(String0.nullOrEmptyTo(getIvd(), ivd));
  }
}
