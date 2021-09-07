package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

/**
 * Tenant open to other channel/tenant visible: Row/Record Level
 * <p>
 * scenario1: some resources do not want to be opened to other tenants
 */
public interface Shared {
  @Transient
  String COLUMN__SHARE = "share";
  @Transient
  String FIELD__SHARE = "share";

  String getShare();

  <T extends Shared> T setShare(String share);
}
