package org.shaneking.ling.rr;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.zero.lang.Boolean0;
import org.shaneking.ling.zero.lang.String0;

/**
 * https://github.com/ShaneKingFriendly/sk-js/blob/master/src/Resp.js
 */
@Accessors(chain = true)
@Slf4j
@ToString
public class Resp<D> {
  public static final String CODE_UNKNOWN_EXCEPTION = "-1";
  public static final String CODE_SUCCESSFULLY = "0";

  @Getter
  @Setter
  private String code;

  @Getter
  @Setter
  private D data;//Business Object

  @Getter
  @Setter
  private String mesg;//Required if code is not 0

  @Getter
  @Schema(hidden = true)
  @Setter
  private Boolean ndrb;

  public static <D> Resp<D> build(String code, D data, String mesg) {
    return new Resp<D>().setCode(code).setData(data).setMesg(mesg);
  }

  public static <D> Resp<D> failed(String code, String mesg, D data) {
    return build(code, data, mesg);
  }

  public static <D> Resp<D> failed(String code, String mesg) {
    return failed(code, mesg, null);
  }

  public static <D> Resp<D> failed(String code) {
    return failed(code, null);
  }

  public static <D> Resp<D> failed() {
    return failed(Resp.CODE_UNKNOWN_EXCEPTION);
  }

  public static <D> Resp<D> success(D data) {
    return build(Resp.CODE_SUCCESSFULLY, data, null);
  }

  public Resp<D> parseExp(@NonNull Exception exp) {
    String code = exp.getClass().getName();
    String mesg = String0.null2EmptyTo(exp.getMessage(), exp.toString());
    if (exp instanceof RespException) {
      if (exp instanceof NdrbRespException) {
        ndrb = true;
      }
      Resp resp = ((RespException) exp).getResp();
      if (resp != null) {
        setNdrb(Boolean0.nullToFalse(getNdrb()) || Boolean0.nullToFalse(resp.getNdrb()));
        if (!CODE_UNKNOWN_EXCEPTION.equals(resp.getCode()) && !CODE_SUCCESSFULLY.equals(resp.getCode())) {
          code = resp.getCode();
        }
        mesg = String0.null2EmptyTo(resp.getMesg(), mesg);
      }
    }
    if (CODE_UNKNOWN_EXCEPTION.equals(this.getCode()) || CODE_SUCCESSFULLY.equals(this.getCode())) {
      this.setCode(code);
    }
    return this.setMesg(String0.null2EmptyTo(this.getMesg(), mesg));
  }
}
