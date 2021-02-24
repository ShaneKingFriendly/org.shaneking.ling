package org.shaneking.ling.persistence.entity.sql;

import org.shaneking.ling.persistence.entity.Audited;
import org.shaneking.ling.persistence.entity.Identified;
import org.shaneking.ling.persistence.entity.SqlEntities;
import org.shaneking.ling.persistence.entity.Versioned;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Date0;

public interface IdAdtVerSqlEntities extends Identified, Audited, Versioned, SqlEntities {
  default <T extends IdAdtVerSqlEntities> T initWithUserId(String userId) {
    initVersion();
    return sinInvalid(String0.N).setLastModifyDateTime(Date0.on().dateTime()).setLastModifyUserId(userId);
  }

  default <T extends IdAdtVerSqlEntities> T initWithUserIdAndId(String userId, String id) {
    sinId(id);
    return initWithUserId(userId);
  }
}
