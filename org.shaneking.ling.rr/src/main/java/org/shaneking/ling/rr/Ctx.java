package org.shaneking.ling.rr;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.persistence.entity.sql.*;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@ToString
public class Ctx {
  @Getter
  @Setter
  private RrAuditLogEntities auditLog;
  @Getter
  @Setter
  private ChannelEntities channel;
  @Getter
  @Setter
  private ObjectNode jon;//json object node
  @Getter
  @Setter
  private String language;//default zh-CN, ref: http://www.rfc-editor.org/rfc/bcp/bcp47.txt
  @Getter
  @Setter
  private TenantEntities tenant;
  @Getter
  @Setter
  private UserEntities user;
  @Getter
  @Setter
  private Map<String, UserEntities> rtuMap = Map0.newHashMap();//readable tenant user map
  @Getter
  @Setter
  private List<TenantReadTenantEntities> trtList = List0.newArrayList();//tenant read tenant
  @Getter
  @Setter
  private List<TenantUseTenantEntities> tutList = List0.newArrayList();//tenant use tenant

  public String gnaAuditId() {
    return getAuditLog() == null ? null : getAuditLog().getId();
  }

  public String gnaChannelId() {
    return getChannel() == null ? null : getChannel().getId();
  }

  public String gnaTenantId() {
    return getTenant() == null ? null : getTenant().getId();
  }

  public String gnaUserId() {
    return getUser() == null ? null : getUser().getId();
  }

  public String gnaUserInMessage() {
    return getUser() == null ? null : String0.wrap(getUser().getNo()
        + String0.wrap(getUser().getId(), String0.OPEN_BRACKET, String0.CLOSE_BRACKET, true)
      , String0.OPEN_PARENTHESIS, String0.CLOSE_PARENTHESIS);
  }

  public String gnaUserNo() {
    return getUser() == null ? null : getUser().getNo();
  }

  public String jsonStr() {
    RrAuditLogEntities auditLog = this.getAuditLog();
    String rtn = OM3.writeValueAsString(this.setAuditLog(null));
    this.setAuditLog(auditLog);
    return rtn;
  }
}
