package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

/**
 * Tenant open to channel readable: Busi Level
 * <p>
 * scenario1: one user(userNo) registered in multiple tenants, so multiple tenant resources can be used
 */
public interface ChannelReadable extends Tenanted {
  @Transient
  String ERR_CODE__PERMISSION_DENIED = "CHANNEL_READABLE__PERMISSION_DENIED";
}
