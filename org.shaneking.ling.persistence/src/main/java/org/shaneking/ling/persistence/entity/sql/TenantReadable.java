package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

//Open to other tenant readable: Table Level
public interface TenantReadable {
  @Transient
  String ERR_CODE__PERMISSION_DENIED = "TENANT_READABLE__PERMISSION_DENIED";
}
