package org.shaneking.ling.rr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;

@Accessors(chain = true)
@ToString(callSuper = true)
public class RespException extends ZeroException {
  @Getter
  @Setter
  private Resp resp;

  public RespException(Resp resp) {
    this(resp, String0.null2EmptyTo(resp.getMsg(), resp.getCode()));
  }

  public RespException(Resp resp, String message) {
    super(String0.null2EmptyTo(message, String0.null2EmptyTo(resp.getMsg(), resp.getCode())));
    this.setResp(resp);
  }

  public RespException(Resp resp, String message, Throwable cause) {
    super(String0.null2EmptyTo(message, String0.null2EmptyTo(resp.getMsg(), resp.getCode())), cause);
    this.setResp(resp);
  }

  public RespException(Resp resp, Throwable cause) {
    this(resp, String0.null2EmptyTo(resp.getMsg(), resp.getCode()), cause);
  }
}
