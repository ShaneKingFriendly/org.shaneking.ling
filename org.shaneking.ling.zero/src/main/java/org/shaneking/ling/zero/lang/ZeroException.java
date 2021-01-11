package org.shaneking.ling.zero.lang;

public class ZeroException extends RuntimeException {
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
