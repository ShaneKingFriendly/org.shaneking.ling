package org.shaneking.ling.persistence.entity.sql;

import org.shaneking.ling.persistence.entity.Numbered;
import org.shaneking.ling.persistence.entity.UniIdxed;

public interface TenantedNumberedUniIdx extends Tenanted, Numbered, UniIdxed {
}
