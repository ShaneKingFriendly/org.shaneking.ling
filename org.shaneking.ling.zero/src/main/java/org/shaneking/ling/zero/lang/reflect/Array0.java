package org.shaneking.ling.zero.lang.reflect;

import java.lang.reflect.Array;

public class Array0 {
  public static <T> T[] newArray(Class<T> type, int length) {
    return (T[]) Array.newInstance(type, length);
  }
}
