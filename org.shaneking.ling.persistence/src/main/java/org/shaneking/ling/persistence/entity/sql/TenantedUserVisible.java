package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

/**
 * Tenant open to other channel/tenant visible: Row/Record Level
 * <p>
 * used in business logic. the different with TenantVisible is must registered user
 */
public interface TenantedUserVisible {
  @Transient
  String ERR_CODE__PERMISSION_DENIED = "TENANTED_USER_VISIBLE__PERMISSION_DENIED";

  @Transient
  String COLUMN__OVU = "ovu";

  @Transient
  String FIELD__OVU = "ovu";

  String getOvu();

  <T extends TenantedUserVisible> T setOvu(String ovu);
}
