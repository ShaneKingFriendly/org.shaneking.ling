package org.shaneking.ling.rr;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.zero.lang.Boolean0;
import org.shaneking.ling.zero.lang.String0;

@Accessors(chain = true)
@ToString(callSuper = true)
public class Resp<O, I> extends OpenResp<O> {
  public static final String CODE_UNKNOWN_EXCEPTION = "-1";
  public static final String CODE_SUCCESSFULLY = "0";
  public static final String CODE_PARTIAL_SUCCESS = "1";

  @Getter
  @Schema(hidden = true)
  @Setter
  private Boolean rbk;//if true, need rollback
  @Getter
  @Schema(hidden = true)
  @Setter
  private I req;

  public static <O, I> Resp<O, I> build() {
    return new Resp<O, I>();
  }

  public static <O, I> Resp<O, I> build(I req, String code, O data, String info) {
    Resp<O, I> rtn = new Resp<O, I>().setReq(req);
    rtn.setMsg(RespMsg.<O>build().setBody(RespMsgBody.build(code, data, info)));
    if (req instanceof Req) {
      rtn.gnnMsg().setRno(((Req<?>) req).gnnMsg().gnnRno()).setAno(((Req<?>) req).gnnMsg().gnnAno());
    }
    return rtn;
  }

  public static <O, I> Resp<O, I> failed(I req, String code, String info, O data) {
    return build(req, code, data, info);
  }

  public static <O, I> Resp<O, I> failed(I req, String code, String info) {
    return failed(req, code, info, null);
  }

  public static <O, I> Resp<O, I> failed(I req, String code) {
    return failed(req, code, null);
  }

  public static <O, I> Resp<O, I> failed(I req) {
    return failed(req, Resp.CODE_UNKNOWN_EXCEPTION);
  }

  public static <O, I> Resp<O, I> failed() {
    return failed(null);
  }

  public static <O, I> Resp<O, I> success(I req, O data) {
    return build(req, Resp.CODE_SUCCESSFULLY, data, null);
  }

  public static <O, I> Resp<O, I> success(O data) {
    return success(null, data);
  }

  public Resp<O, I> parseExp(@NonNull Exception exp) {
    String code = exp.getClass().getName();
    String info = String0.null2EmptyTo(exp.getMessage(), exp.toString());
    if (exp instanceof RespException) {
      if (exp instanceof RbkRespException) {
        setRbk(true);
      }
      Resp resp = ((RespException) exp).getResp();
      if (resp != null) {
        setRbk(Boolean0.nullToFalse(getRbk()) || Boolean0.nullToFalse(resp.getRbk()));
        if (!CODE_UNKNOWN_EXCEPTION.equals(resp.gnaMsgBodyCode()) && !CODE_SUCCESSFULLY.equals(resp.gnaMsgBodyCode())) {
          code = String0.null2EmptyTo(resp.gnaMsgBodyCode(), code);
        }
        info = String0.null2EmptyTo(resp.gnaMsgBodyInfo(), info);
      }
    }
    if (CODE_UNKNOWN_EXCEPTION.equals(this.gnaMsgBodyCode()) || CODE_SUCCESSFULLY.equals(this.gnaMsgBodyCode())) {
      this.gnnMsg().gnnBody().setCode(code);
    }
    this.gnnMsg().gnnBody().setInfo(String0.null2EmptyTo(this.gnaMsgBodyInfo(), info));
    return this;
  }

  public String gnaMsgBodyCode() {
    return this.gnnMsg().gnnBody().getCode();
  }

  public String gnaMsgBodyInfo() {
    return this.gnnMsg().gnnBody().getInfo();
  }

  public O gnaMsgBodyData() {
    return this.gnnMsg().gnnBody().getData();
  }

  public RespMsg<O> gnnMsg() {
    if (this.getMsg() == null) {
      this.setMsg(RespMsg.build());
    }
    return this.getMsg();
  }

  public Resp<O, I> srtMsgBody(RespMsgBody<O> respMsgBody) {
    this.gnnMsg().setBody(respMsgBody);
    return this;
  }

  public Resp<O, I> srtMsgBodyData(O o) {
    this.gnnMsg().gnnBody().setData(o);
    return this;
  }

  public Resp<O, I> srtMsgBodyCode(String code) {
    this.gnnMsg().gnnBody().setCode(code);
    return this;
  }

  public Resp<O, I> srtMsgBodyInfo(String info) {
    this.gnnMsg().gnnBody().setInfo(info);
    return this;
  }

  public String jsonStr() {
    I req = this.getReq();
    String rtn = OM3.writeValueAsString(this.setReq(null));
    this.setReq(req);
    return rtn;
  }
}
