package org.shaneking.ling.rr;

//Need Rollback Exception
public class RbkRespException extends RespException {
  public RbkRespException(Resp resp) {
    super(resp);
  }

  public RbkRespException(Resp resp, String message) {
    super(resp, message);
  }

  public RbkRespException(Resp resp, String message, Throwable cause) {
    super(resp, message, cause);
  }

  public RbkRespException(Resp resp, Throwable cause) {
    super(resp, cause);
  }
}
