package org.shaneking.ling.persistence.entity.sql;

import org.shaneking.ling.persistence.entity.*;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.time.ZDT0;

public interface DialectSqlEntities extends Identified, Versioned, Deleted, Audited, Numbered, SqlEntities {
  default <T extends DialectSqlEntities> T initWithUid(String userId) {
    initVer();
    sinDd(String0.N);
    return sinIvd(String0.N).setLmDsz(ZDT0.on().dTSZ()).setLmUid(userId);
  }

  default <T extends DialectSqlEntities> T initWithUidAndId(String userId, String id) {
    return initWithUidNoId(userId, id, id);
  }

  default <T extends DialectSqlEntities> T initWithUidNoId(String userId, String no, String id) {
    sinId(id);
    sinNo(no);
    return initWithUid(userId);
  }
}
