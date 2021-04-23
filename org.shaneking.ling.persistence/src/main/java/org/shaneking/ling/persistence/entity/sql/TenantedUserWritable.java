package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

@Deprecated//no scenario
public interface TenantedUserWritable {
  @Transient
  String ERR_CODE__PERMISSION_DENIED = "TENANTED_USER_WRITABLE__PERMISSION_DENIED";
}
