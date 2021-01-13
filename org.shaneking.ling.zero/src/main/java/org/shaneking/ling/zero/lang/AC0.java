package org.shaneking.ling.zero.lang;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AC0 {
  public static boolean close(AutoCloseable autoCloseable) {
    return close(autoCloseable, true);
  }

  public static boolean close(AutoCloseable autoCloseable, boolean quietly) {
    boolean rtn = true;
    if (autoCloseable != null) {
      try {
        autoCloseable.close();
      } catch (Exception e) {
        if (quietly) {
          log.error(e.getMessage(), e);
          rtn = false;
        } else {
          throw new ZeroException(e);
        }
      }
    }
    return rtn;
  }

  public static boolean close(AutoCloseable autoCloseable, int times) {
    return close(autoCloseable, true, times);
  }

  public static boolean close(AutoCloseable autoCloseable, boolean lastQuietly, int times) {
    boolean closed = false;
    while (!closed && times > 0) {
      closed = close(autoCloseable, true);
      times--;
    }
    return closed ? closed : close(autoCloseable, lastQuietly);
  }
}
