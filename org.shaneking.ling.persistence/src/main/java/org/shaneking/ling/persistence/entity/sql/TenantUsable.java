package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

/**
 * Tenant open to other channel/tenant readable: db level(all table)
 * <p>
 * use in generate select sql
 * <p>
 * scenario1: the platform administrator add [CBRT](common base resource tenant), open to the most/all tenants
 */
public interface TenantUsable {
  @Transient
  String ERR_CODE__PERMISSION_DENIED = "TENANT_USABLE__PERMISSION_DENIED";
}
