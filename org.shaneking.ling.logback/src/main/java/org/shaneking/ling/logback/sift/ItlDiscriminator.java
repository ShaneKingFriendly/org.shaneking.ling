package org.shaneking.ling.logback.sift;

import ch.qos.logback.classic.sift.MDCBasedDiscriminator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.shaneking.ling.zero.util.Map0;

import java.util.Map;

public class ItlDiscriminator extends MDCBasedDiscriminator {
  public static final InheritableThreadLocal<Map<String, String>> ITL = new InheritableThreadLocal<>();

  @Override
  public String getDiscriminatingValue(ILoggingEvent event) {
    String rtn;
    Map<String, String> itlMap = itl();
    if (itlMap.get(getKey()) != null) {
      rtn = itlMap.get(getKey());
    } else {
      rtn = super.getDiscriminatingValue(event);
    }
    return rtn;
  }

  public static Map<String, String> itl() {
    Map<String, String> rtn = ITL.get();
    if (rtn == null) {
      rtn = Map0.newHashMap();
      ITL.set(rtn);
    }
    return rtn;
  }
}
