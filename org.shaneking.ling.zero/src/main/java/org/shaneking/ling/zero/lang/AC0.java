package org.shaneking.ling.zero.lang;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AC0 {
  public static boolean close(AutoCloseable autoCloseable) {
    boolean rtn = true;
    if (autoCloseable != null) {
      try {
        autoCloseable.close();
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        rtn = false;
      }
    }
    return rtn;
  }

  public static boolean close(AutoCloseable autoCloseable, int times) {
    boolean rtn = close(autoCloseable);
    if (!rtn && times > 0) {
      rtn = close(autoCloseable, --times);
    }
    return rtn;
  }
}
