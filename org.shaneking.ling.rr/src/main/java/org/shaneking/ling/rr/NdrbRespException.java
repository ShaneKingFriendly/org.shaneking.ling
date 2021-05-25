package org.shaneking.ling.rr;

//NeedRollback
public class NdrbRespException extends RespException {
  public NdrbRespException(Resp resp) {
    super(resp);
  }

  public NdrbRespException(Resp resp, String message) {
    super(resp, message);
  }

  public NdrbRespException(Resp resp, String message, Throwable cause) {
    super(resp, message, cause);
  }

  public NdrbRespException(Resp resp, Throwable cause) {
    super(resp, cause);
  }
}
