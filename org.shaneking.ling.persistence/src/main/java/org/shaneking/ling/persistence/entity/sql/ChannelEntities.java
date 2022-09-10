package org.shaneking.ling.persistence.entity.sql;

import org.shaneking.ling.persistence.CacheableEntities;

import javax.persistence.Transient;

public interface ChannelEntities extends CacheableEntities, Named {
  @Transient
  String ERR_CODE__ALGORITHM_UNSUPPORTED = "CHANNEL_ENTITIES__ALGORITHM_UNSUPPORTED";
  @Transient
  String ERR_CODE__BAD_REQUEST_WITH_MESSAGE = "CHANNEL_ENTITIES__BAD_REQUEST_WITH_MESSAGE";
  @Transient
  String ERR_CODE__BAD_REQUEST_WITH_TAMPERED = "CHANNEL_ENTITIES__BAD_REQUEST_WITH_TAMPERED";
  @Transient
  String ERR_CODE__NEED_ENCODING = "CHANNEL_ENTITIES__NEED_ENCODING";
  @Transient
  String ERR_CODE__NEED_MVC = "CHANNEL_ENTITIES__NEED_MVC";
  @Transient
  String ERR_CODE__INVALID_TIMESTAMP = "CHANNEL_ENTITIES__INVALID_TIMESTAMP";

  @Transient
  String TOKEN_VALUE_TYPE__PROP = "PROP";
  @Transient
  String TOKEN_VALUE_TYPE__SELF = "SELF";

  //@see sktest.roc.rr.cfg.RrCfg.simpleChannelEntity
  <T extends ChannelEntities> Class<T> entityClass();

  String getDescription();

  <T extends ChannelEntities> T setDescription(String description);

  Integer getDszSeconds();

  <T extends ChannelEntities> T setDszSeconds(Integer dszForce);

  String getEncTf();

  <T extends ChannelEntities> T setEncTf(String encTf);

  String getEncTat();

  <T extends ChannelEntities> T setEncTat(String encTat);

  String getEncTvt();

  <T extends ChannelEntities> T setEncTvt(String encTvt);

  String getEncTv();

  <T extends ChannelEntities> T setEncTv(String encTv);

  String getMvcTv();

  <T extends ChannelEntities> T setMvcTv(String mvcTv);

  String getMvcTvt();

  <T extends ChannelEntities> T setMvcTvt(String mvcTvt);

  String getMvcTat();

  <T extends ChannelEntities> T setMvcTat(String mvcTat);

  String getMvcTf();

  <T extends ChannelEntities> T setMvcTf(String mvcTf);
}
