package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

public interface Tenanted {
  @Transient
  String ERR_CODE__NOT_FOUND = "TENANTED__NOT_FOUND";

  @Transient
  String COLUMN__TENANT_ID = "tenant_id";

  @Transient
  String FIELD__TENANT_ID = "tenantId";

  String getTenantId();

  <T extends Tenanted> T setTenantId(String tenantId);
}
