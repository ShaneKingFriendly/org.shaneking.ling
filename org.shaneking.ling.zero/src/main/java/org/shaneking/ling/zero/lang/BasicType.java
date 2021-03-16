package org.shaneking.ling.zero.lang;

import lombok.NonNull;

public class BasicType {
  public static String getType(@NonNull Object a) {
    return a.getClass().toString();
  }
}
