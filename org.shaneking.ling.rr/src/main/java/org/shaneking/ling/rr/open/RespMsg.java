package org.shaneking.ling.rr.open;

import jdk.nashorn.internal.ir.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

//Secretly
@Accessors(chain = true)
@ToString
public class RespMsg<O> {
  @Getter
  @Setter
  private String rno;//RequestNo. Unique
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
  private RespMsgBody<O> body;

  public static <O> RespMsg<O> build() {
    return new RespMsg<O>();
  }
}
