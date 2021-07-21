package org.shaneking.ling.persistence.entity.sql;

import org.shaneking.ling.persistence.entity.*;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Date0;

public interface DialectSqlEntities extends Identified, Versioned, Deleted, Audited, Numbered, SqlEntities {
  default <T extends DialectSqlEntities> T initWithUserId(String userId) {
    initVersion();
    sinDd(String0.N);
    return sinInvalid(String0.N).setLastModifyDateTime(Date0.on().dateTime()).setLastModifyUserId(userId);
  }

  default <T extends DialectSqlEntities> T initWithUserIdAndId(String userId, String id) {
    return initWithUserIdNoId(userId, id, id);
  }

  default <T extends DialectSqlEntities> T initWithUserIdNoId(String userId, String no, String id) {
    sinId(id);
    sinNo(no);
    return initWithUserId(userId);
  }
}
