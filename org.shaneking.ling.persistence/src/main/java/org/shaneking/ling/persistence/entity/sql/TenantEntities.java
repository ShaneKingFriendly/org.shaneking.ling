package org.shaneking.ling.persistence.entity.sql;

import org.shaneking.ling.persistence.CacheableEntities;

public interface TenantEntities extends CacheableEntities, Named {

  //@see sktest.roc.rr.cfg.RrCfg.simpleTenantEntity
  <T extends TenantEntities> Class<T> entityClass();

  String getDescription();

  <T extends TenantEntities> T setDescription(String description);
}
