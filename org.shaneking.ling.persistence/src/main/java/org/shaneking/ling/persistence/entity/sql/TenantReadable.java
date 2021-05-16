package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

/**
 * Tenant open to other tenant readable: Tech Level
 * <p>
 * scenario1: some base resources can be share to other tenants, like cluster, catalog, rowStrategy
 */
public interface TenantReadable extends Tenanted {
  @Transient
  String ERR_CODE__PERMISSION_DENIED = "TENANT_READABLE__PERMISSION_DENIED";
}
