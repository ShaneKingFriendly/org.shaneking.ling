package org.shaneking.ling.rr.open;

import jdk.nashorn.internal.ir.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class ReqMsgBdy<I> {
  @Getter
  @Setter
  private String uno;//UserNo
  @Getter
  @Setter
  private String tbl;//Table for UI
  @Getter
  @Setter
  private I obj;//main object content
  @Getter
  @Setter
  private ObjectNode jsn;
}
