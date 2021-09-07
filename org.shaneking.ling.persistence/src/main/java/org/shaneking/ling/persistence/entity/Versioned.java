package org.shaneking.ling.persistence.entity;

import org.shaneking.ling.zero.lang.Integer0;

import javax.persistence.Transient;

public interface Versioned {
  @Transient
  String COLUMN__VER = "ver";
  @Transient
  String FIELD__VER = "ver";

  Integer getVer();

  default <T extends Versioned> T initVer() {
    return setVer(Integer0.null2Zero(getVer()));
  }

  <T extends Versioned> T setVer(Integer ver);
}
