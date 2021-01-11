package org.shaneking.ling.zero.lang;

public class Boolean0 {
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
}
