package org.shaneking.ling.zero.lang;

public class ZeroException extends RuntimeException {
  public static final String ERR_CODE__ANNOTATION_SETTING_ERROR = "ZERO_EXCEPTION__ANNOTATION_SETTING_ERROR";

  public ZeroException() {
    super();
  }

  public ZeroException(String message) {
    super(message);
  }

  public ZeroException(String message, Throwable cause) {
    super(message, cause);
  }

  public ZeroException(Throwable cause) {
    super(cause);
  }
}
