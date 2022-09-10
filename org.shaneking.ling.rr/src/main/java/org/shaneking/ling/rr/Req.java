package org.shaneking.ling.rr;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Transient;

@Accessors(chain = true)
@ToString
public class Req<I> {
  @Transient
  public static final String ERR_CODE__REQUIRED_CHANNEL_NUMBER = "REQ__REQUIRED_CHANNEL_NUMBER";
  @Transient
  public static final String ERR_CODE__REQUIRED_REQ_MSG_NODE = "REQ__REQUIRED_REQ_MSG_NODE";
  @Getter
  @Setter
  private String cno;//ChannelNo
  @Getter
  @Setter
  private String tkn;//Token, for one request on secret key scenario.
  @Getter
  @Setter
  private String mvc;//Message Verification Code
  @Getter
  @Setter
  private String enc;//ciphertext of msg
  @Getter
  @Setter
  private ReqMsg<I> msg;

  @Getter
  @Schema(hidden = true)
  @Setter
  private Ctx ctx;

  public static <I> Req<I> build() {
    return new Req<I>();
  }

  public Ctx gnnCtx() {
    if (getCtx() == null) {
      setCtx(new Ctx());
    }
    return getCtx();
  }

  public ReqMsg<I> gnnMsg() {
    if (getMsg() == null) {
      setMsg(ReqMsg.build());
    }
    return getMsg();
  }

  public String gnaMsgBdyTno() {
    if (String0.isNullOrEmpty(this.gnnMsg().gnnBdy().getTno())) {
      this.gnnMsg().gnnBdy().setTno(this.getCno());
    }
    return this.gnnMsg().gnnBdy().getTno();
  }

  public String gnaMsgBdyUno() {
    if (String0.isNullOrEmpty(this.gnnMsg().gnnBdy().getUno())) {
      if (String0.isNullOrEmpty(this.gnnMsg().getUno())) {
        this.gnnMsg().gnnBdy().setUno(this.getCno());
      } else {
        this.gnnMsg().gnnBdy().setUno(this.gnnMsg().getUno());
      }
    }
    return this.gnnMsg().gnnBdy().getUno();
  }

  public String gnaMsgUno() {
    if (String0.isNullOrEmpty(this.gnnMsg().getUno())) {
      this.gnnMsg().setUno(this.getCno());
    }
    return this.gnnMsg().getUno();
  }

  public I gnaMsgBdyObj() {
    return this.gnnMsg().gnnBdy().getObj();
  }

  public Req<I> srtMsgBdy(ReqMsgBdy<I> reqMsgBdy) {
    this.gnnMsg().setBdy(reqMsgBdy);
    return this;
  }

  public Req<I> srtObj(I i) {
    this.gnnMsg().gnnBdy().setObj(i);
    return this;
  }

  public String jsonStr() {
    Ctx ctx = this.gnnCtx();
    String rtn = OM3.writeValueAsString(this.setCtx(null));
    this.setCtx(ctx);
    return rtn;
  }
}
