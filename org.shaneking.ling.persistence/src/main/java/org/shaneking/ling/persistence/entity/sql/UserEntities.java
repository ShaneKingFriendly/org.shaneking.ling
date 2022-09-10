package org.shaneking.ling.persistence.entity.sql;

import org.shaneking.ling.persistence.CacheableEntities;

public interface UserEntities extends CacheableEntities, Named, Tenanted {

  //@see sktest.roc.rr.cfg.RrCfg.simpleUserEntity
  <T extends UserEntities> Class<T> entityClass();

  String getEmail();

  <T extends UserEntities> T setEmail(String email);

  String getHaha();

  <T extends UserEntities> T setHaha(String haha);

  String getMobile();

  <T extends UserEntities> T setMobile(String mobile);
}
