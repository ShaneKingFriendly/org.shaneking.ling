package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

/**
 * Tenant open to other channel/tenant visible: Row/Record Level
 * <p>
 * used in business logic
 */
public interface TenantVisible extends Tenanted {
  @Transient
  String ERR_CODE__PERMISSION_DENIED = "TENANT_VISIBLE__PERMISSION_DENIED";

  @Transient
  String COLUMN__OV = "ov";
  @Transient
  String FIELD__OV = "ov";

  String getOv();

  <T extends TenantVisible> T setOv(String ov);
}
