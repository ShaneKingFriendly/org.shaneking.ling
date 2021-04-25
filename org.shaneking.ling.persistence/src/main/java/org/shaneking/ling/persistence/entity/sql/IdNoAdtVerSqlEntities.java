package org.shaneking.ling.persistence.entity.sql;

import org.shaneking.ling.persistence.entity.*;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Date0;

public interface IdNoAdtVerSqlEntities extends Identified, Numbered, Audited, Versioned, SqlEntities {
  default <T extends IdNoAdtVerSqlEntities> T initWithUserId(String userId) {
    initVersion();
    return sinInvalid(String0.N).setLastModifyDateTime(Date0.on().dateTime()).setLastModifyUserId(userId);
  }

  default <T extends IdNoAdtVerSqlEntities> T initWithUserIdAndId(String userId, String id) {
    return initWithUserIdNoId(userId, id, id);
  }

  default <T extends IdNoAdtVerSqlEntities> T initWithUserIdNoId(String userId, String no, String id) {
    sinId(id);
    sinNo(no);
    return initWithUserId(userId);
  }
}
