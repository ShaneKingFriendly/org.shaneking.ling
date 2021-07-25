package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

public interface ToTenanted {
  @Transient
  String COLUMN__TO_TENANT_ID = "to_tenant_id";
  @Transient
  String FIELD__TO_TENANT_ID = "toTenantId";

  String getToTenantId();

  <T extends ToTenanted> T setToTenantId(String toTenantId);
}
