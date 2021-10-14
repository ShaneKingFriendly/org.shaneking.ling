package org.shaneking.ling.rr.open;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class Resp<O, I> {
  public static final String CODE_UNKNOWN_EXCEPTION = "-1";
  public static final String CODE_SUCCESSFULLY = "0";
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

  public static <O, I> Resp<O, I> build(String code, O data, String info) {
    return new Resp<O, I>().setMsg(RespMsg.<O>build().setBody(RespMsgBody.build(code, data, info)));
  }

  public static <O, I> Resp<O, I> failed(String code, String info, O data) {
    return build(code, data, info);
  }

  public static <O, I> Resp<O, I> failed(String code, String info) {
    return failed(code, info, null);
  }

  public static <O, I> Resp<O, I> failed(String code) {
    return failed(code, null);
  }

  public static <O, I> Resp<O, I> failed() {
    return failed(Resp.CODE_UNKNOWN_EXCEPTION);
  }

  public static <O, I> Resp<O, I> success(O data) {
    return build(Resp.CODE_SUCCESSFULLY, data, null);
  }
}
