package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

/**
 * Tenant open to other channel/tenant visible: Row/Record Level
 * <p>
 * scenario1: some resources do not want to be opened to other tenants
 */
public interface OpenVisible extends Tenanted {
  @Transient
  String ERR_CODE__PERMISSION_DENIED = "OPEN_VISIBLE__PERMISSION_DENIED";

  @Transient
  String COLUMN__OV = "ov";
  @Transient
  String FIELD__OV = "ov";

  String getOv();

  <T extends OpenVisible> T setOv(String ov);
}
