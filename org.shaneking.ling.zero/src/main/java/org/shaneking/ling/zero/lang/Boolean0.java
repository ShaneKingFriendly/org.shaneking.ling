package org.shaneking.ling.zero.lang;

import java.util.function.BooleanSupplier;

public class Boolean0 {
  public static void checkArgument(boolean expression, Object errorMessage) {
    if (!expression) {
      throw new IllegalArgumentException(String.valueOf(errorMessage));
    }
  }

  public static void checkState(boolean expression, Object errorMessage) {
    if (!expression) {
      throw new IllegalStateException(String.valueOf(errorMessage));
    }
  }

  //false to execute
  public static boolean falseTo(boolean expr, BooleanSupplier supplier) {
    return expr ? expr : supplier.getAsBoolean();
  }

  public static boolean nullToFalse(Boolean b) {
    return b != null && b;
  }

  public static boolean nullToTrue(Boolean b) {
    return b == null || b;
  }

  public static String sf(boolean b) {
    return b ? String0.S : String0.F;
  }

  public static boolean sf(String s) {
    return String0.S.equalsIgnoreCase(s);
  }

  public static String tf(boolean b) {
    return b ? String0.T : String0.F;
  }

  public static boolean tf(String s) {
    return String0.T.equalsIgnoreCase(s);
  }

  public static String yn(boolean b) {
    return b ? String0.Y : String0.N;
  }

  public static boolean yn(String s) {
    return String0.Y.equalsIgnoreCase(s);
  }
}
