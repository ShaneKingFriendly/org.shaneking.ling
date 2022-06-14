package org.shaneking.ling.rr;

import io.swagger.v3.oas.annotations.media.Schema;
import jdk.nashorn.internal.ir.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

//Secretly
@Accessors(chain = true)
@ToString
public class ReqMsg<I> {
  @Getter
  @Setter
  private String rno;//RequestNo. Unique
  @Getter
  @Setter
  private String tno;//TracingNo
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
  private String uno;//UserNo, proxy bdy.uno to do.
  @Getter
  @Schema(hidden = true)
  @Setter
  private ObjectNode jsn;
  @Getter
  @Setter
  private ReqMsgBdy<I> bdy;

  public static <I> ReqMsg<I> build() {
    return new ReqMsg<I>();
  }
}
