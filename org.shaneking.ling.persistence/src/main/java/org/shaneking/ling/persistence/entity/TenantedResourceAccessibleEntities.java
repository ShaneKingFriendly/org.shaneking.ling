package org.shaneking.ling.persistence.entity;

import lombok.NonNull;
import org.shaneking.ling.persistence.CacheableEntities;
import org.shaneking.ling.persistence.entity.sql.Tenanted;
import org.shaneking.ling.persistence.entity.sql.ToTenanted;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;

import javax.persistence.Transient;
import java.util.List;

public interface TenantedResourceAccessibleEntities extends CacheableEntities, Tenanted, ToTenanted {
  @Transient
  String COLUMN__DEFAULT_ACCESSIBLE = "default_accessible";
  @Transient
  String FIELD__DEFAULT_ACCESSIBLE = "defaultAccessible";

  static List<String> calc(List<? extends TenantedResourceAccessibleEntities> list, String clazz, @NonNull List<String> defaultTenantIds) {
    List<String> rtn = List0.newArrayList();
    rtn.addAll(defaultTenantIds);
    if (list != null && list.size() > 0 && !String0.isNullOrEmpty(clazz)) {
      for (TenantedResourceAccessibleEntities tenantedResourceAccessibleEntities : list) {
        String tId = tenantedResourceAccessibleEntities.calcReadableTenantId(clazz);
        if (!String0.isNullOrEmpty(tId) && !rtn.contains(tId)) {
          rtn.add(tId);
        }
      }
    }
    return rtn;
  }

  static List<String> calc(List<? extends TenantedResourceAccessibleEntities> list, String clazz, String defaultTenantId) {
    return calc(list, clazz, List0.newArrayList(defaultTenantId));
  }

  String calcReadableTenantId(String clazz);

  <T extends TenantedResourceAccessibleEntities> Class<T> entityClass();

  String getDefaultAccessible();

  <T extends TenantedResourceAccessibleEntities> T setDefaultAccessible(String defaultAccessible);
}
