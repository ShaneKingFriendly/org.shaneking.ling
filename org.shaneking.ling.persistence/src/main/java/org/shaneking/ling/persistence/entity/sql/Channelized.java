package org.shaneking.ling.persistence.entity.sql;

import javax.persistence.Transient;

public interface Channelized {
  @Transient
  String COLUMN__CHANNEL_ID = "channel_id";

  @Transient
  String FIELD__CHANNEL_ID = "channelId";

  String getChannelId();

  <T extends Channelized> T setChannelId(String channel);
}
