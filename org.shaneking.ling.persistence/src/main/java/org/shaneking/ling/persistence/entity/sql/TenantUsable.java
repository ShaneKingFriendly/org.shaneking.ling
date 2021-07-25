package org.shaneking.ling.persistence.entity.sql;

/**
 * Tenant open to other tenant usable
 * <p>
 * scenario1: some base resources can be share to other tenants, like set catalog of table, rowStrategy of table
 */
public interface TenantUsable extends Tenanted {
}
