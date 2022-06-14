package org.shaneking.ling.rr;

import io.swagger.v3.oas.annotations.media.Schema;
import jdk.nashorn.internal.ir.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

//Cacheable
@Accessors(chain = true)
@ToString
public class ReqMsgBdy<I> {
  @Getter
  @Setter
  private String tno;//TenantNo, if null use cno for system call self
  @Getter
  @Setter
  private String uno;//UserNo, if null use cno for system call system
  @Getter
  @Schema(hidden = true)
  @Setter
  private Tbl tbl;//Table for UI
  @Getter
  @Setter
  private I obj;//main object content
  @Getter
  @Schema(hidden = true)
  @Setter
  private ObjectNode jsn;

  public static <I> ReqMsgBdy<I> build() {
    return new ReqMsgBdy<I>();
  }
}
