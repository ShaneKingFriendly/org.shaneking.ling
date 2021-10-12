package org.shaneking.ling.rr.open;

import jdk.nashorn.internal.ir.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class ReqMsg<I> {
  @Getter
  @Setter
  private String tno;//TenantNo
  @Getter
  @Setter
  private String rno;//RequestNo. Unique
  @Getter
  @Setter
  private String sno;//SeriesNo
  @Getter
  @Setter
  private Integer asy;//seconds
  @Getter
  @Setter
  private String ano;//AsyncNo. Unique
  @Getter
  @Setter
  private String dsz;//DateTimeSssZone
  @Getter
  @Setter
  private ObjectNode jsn;
  @Getter
  @Setter
  private ReqMsgBdy<I> bdy;
}
