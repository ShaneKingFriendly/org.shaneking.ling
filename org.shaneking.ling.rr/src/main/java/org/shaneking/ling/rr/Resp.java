package org.shaneking.ling.rr;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.lang.Boolean0;
import org.shaneking.ling.zero.lang.String0;

@Accessors(chain = true)
@ToString
public class Resp<O, I> {
  public static final String CODE_UNKNOWN_EXCEPTION = "-1";
  public static final String CODE_SUCCESSFULLY = "0";
  public static final String CODE_PARTIAL_SUCCESS = "1";
  @Getter
  @Setter
  private String mvc;//Message Verification Code
  @Getter
  @Setter
  private String enc;//ciphertext of msg
  @Getter
  @Setter
  private RespMsg<O> msg;

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
    return new Resp<O, I>().setReq(req).setMsg(RespMsg.<O>build().setBody(RespMsgBody.build(code, data, info)));
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
        if (!CODE_UNKNOWN_EXCEPTION.equals(resp.gnaCode()) && !CODE_SUCCESSFULLY.equals(resp.gnaCode())) {
          code = String0.null2EmptyTo(resp.gnaCode(), code);
        }
        info = String0.null2EmptyTo(resp.gnaInfo(), info);
      }
    }
    if (CODE_UNKNOWN_EXCEPTION.equals(this.gnaCode()) || CODE_SUCCESSFULLY.equals(this.gnaCode())) {
      this.gnnMsg().gnnBody().setCode(code);
    }
    this.gnnMsg().gnnBody().setInfo(String0.null2EmptyTo(this.gnaInfo(), info));
    return this;
  }

  public String gnaCode() {
    return this.gnnMsg().gnnBody().getCode();
  }

  public String gnaInfo() {
    return this.gnnMsg().gnnBody().getInfo();
  }

  public RespMsg<O> gnnMsg() {
    if (this.getMsg() == null) {
      this.setMsg(RespMsg.build());
    }
    return this.getMsg();
  }

  public Resp<O, I> srt(RespMsgBody<O> respMsgBody) {
    this.gnnMsg().setBody(respMsgBody);
    return this;
  }

  public Resp<O, I> srt(O o) {
    this.gnnMsg().gnnBody().setData(o);
    return this;
  }
}
