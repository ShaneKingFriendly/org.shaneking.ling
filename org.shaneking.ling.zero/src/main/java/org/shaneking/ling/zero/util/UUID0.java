package org.shaneking.ling.zero.util;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.time.LDT0;

import java.util.UUID;

public class UUID0 {
  private static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    , 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    , 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
  private static final int MAX_RADIX = digits.length;
  private static final int MIN_RADIX = 2;

  public static String cMl33() {
    return cMl33(UUID.randomUUID());
  }

  public static String cMl33(UUID uuid) {
    return System.currentTimeMillis() + String0.MINUS + l19(uuid);
  }

  public static String cUl33() {
    return cUl33(UUID.randomUUID());
  }

  public static String cUl33(UUID uuid) {
    return System.currentTimeMillis() + String0.UNDERLINE + l19(uuid);
  }

  private static String digits(long val, int digits) {
    long hi = 1L << (digits * 4);
    return to62String(hi | (val & (hi - 1)), MAX_RADIX).substring(1);
  }

  public static String dMl37() {
    return dMl37(UUID.randomUUID());
  }

  public static String dMl37(UUID uuid) {
    return LDT0.on().dts() + String0.MINUS + l19(uuid);
  }

  public static String dUl37() {
    return dUl37(UUID.randomUUID());
  }

  public static String dUl37(UUID uuid) {
    return LDT0.on().dts() + String0.UNDERLINE + l19(uuid);
  }

  public static String l19() {
    return l19(UUID.randomUUID());
  }

  public static String l19(@NonNull UUID uuid) {
    return digits(uuid.getMostSignificantBits() >> 32, 8) +
      digits(uuid.getMostSignificantBits() >> 16, 4) +
      digits(uuid.getMostSignificantBits(), 4) +
      digits(uuid.getLeastSignificantBits() >> 48, 4) +
      digits(uuid.getLeastSignificantBits(), 12);
  }

  protected static String to62String(long i, int radix) {
    int reRadix = radix;
    if (reRadix < MIN_RADIX || reRadix > MAX_RADIX)
      reRadix = 10;
    if (reRadix == 10)
      return Long.toString(i);

    final int size = 65;
    int charPos = 64;

    char[] buf = new char[size];
    boolean negative = (i < 0);

    if (!negative) {
      i = -i;
    }
    while (i <= -reRadix) {
      buf[charPos--] = digits[(int) (-(i % reRadix))];
      i = i / reRadix;
    }
    buf[charPos] = digits[(int) (-i)];

    if (negative) {
      buf[--charPos] = '-';
    }
    return new String(buf, charPos, (size - charPos));
  }
}
