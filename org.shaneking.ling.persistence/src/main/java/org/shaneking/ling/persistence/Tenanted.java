package org.shaneking.ling.persistence;

import javax.persistence.Transient;

public interface Tenanted {
  @Transient
  String COLUMN__TENANT_ID = "tenant_id";
  @Transient
  String FIELD__TENANT_ID = "tenantId";

  String getTenantId();

  <T extends Tenanted> T setTenantId(String tenantId);
}
