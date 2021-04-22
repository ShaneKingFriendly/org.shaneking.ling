package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

//Open to other tenant visible: Row Level
public interface TenantVisible {
  @Transient
  String ERR_CODE__PERMISSION_DENIED = "TENANT_VISIBLE__PERMISSION_DENIED";

  @Transient
  String COLUMN__TV = "tv";

  @Transient
  String FIELD__TV = "tv";

  String getTv();

  <T extends TenantVisible> T setTv(String tv);
}
