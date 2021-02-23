package org.shaneking.ling.persistence.entity;

import org.shaneking.ling.zero.lang.Integer0;

import javax.persistence.Transient;

public interface Versioned {
  @Transient
  String COLUMN__VERSION = "version";

  @Transient
  String FIELD__VERSION = "version";

  Integer getVersion();

  <T extends Versioned> T setVersion(Integer version);

  default <T extends Versioned> T initVersion() {
    return setVersion(Integer0.null2Zero(getVersion()));
  }
}
