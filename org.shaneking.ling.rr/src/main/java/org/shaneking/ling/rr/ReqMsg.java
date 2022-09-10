package org.shaneking.ling.rr;

import io.swagger.v3.oas.annotations.media.Schema;
import jdk.nashorn.internal.ir.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.UUID0;

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

  public String gnnRno() {
    if (String0.isNullOrEmpty(rno)) {
      setRno(UUID0.cUl33());
    }
    return rno;
  }

  public String gnnTno() {
    if (String0.isNullOrEmpty(tno)) {
      setTno(UUID0.cUl33());
    }
    return tno;
  }

  public String gnnAno() {
    //org.shaneking.ling.zero.lang.ZeroException: com.fasterxml.jackson.databind.JsonMappingException: (was java.lang.NullPointerException) (through reference chain: org.shaneking.ling.rr.Req["msg"]->org.shaneking.ling.rr.ReqMsg["ano"])
    if (String0.isNullOrEmpty(ano) && asy != null && asy > 0) {
      setAno(UUID0.cUl33());
    }
    return ano;
  }

  public ReqMsgBdy<I> gnnBdy() {
    if (getBdy() == null) {
      setBdy(ReqMsgBdy.build());
    }
    return getBdy();
  }
}
