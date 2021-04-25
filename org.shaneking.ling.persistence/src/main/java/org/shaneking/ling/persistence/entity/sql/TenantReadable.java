package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

/**
 * Tenant open to other channel/tenant readable: Table Level
 * <p>
 * use in generate select sql
 */
public interface TenantReadable extends Tenanted {
  @Transient
  String ERR_CODE__PERMISSION_DENIED = "TENANT_READABLE__PERMISSION_DENIED";
}
