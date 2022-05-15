package org.shaneking.ling.zero.util;

public class Hex0 {
  public static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  public static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

  public static byte[] decodeHex(final char[] data) {
    int len = data.length;
    if ((len & 0x01) != 0) {
      throw new IllegalArgumentException("Characters length " + len + " not odd number.");
    }
    byte[] out = new byte[len >> 1];
    // two characters form the hex value.
    for (int i = 0, j = 0; j < len; i++) {
      int f = toDigit(data[j], j++) << 4;
      f = f | toDigit(data[j], j++);
      out[i] = (byte) (f & 0xFF);
    }
    return out;
  }

  public static char[] encodeHex(final byte[] data) {
    return encodeHex(data, true);
  }

  public static char[] encodeHex(final byte[] data, final boolean toLowerCase) {
    return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
  }

  public static char[] encodeHex(final byte[] data, final char[] toDigits) {
    final int l = data.length;
    final char[] out = new char[l << 1];
    encodeHex(data, 0, data.length, toDigits, out, 0);
    return out;
  }

  public static void encodeHex(final byte[] data, final int dataOffset, final int dataLen, final char[] toDigits, final char[] out, final int outOffset) {
    // two characters form the hex value.
    for (int i = dataOffset, j = outOffset; i < dataOffset + dataLen; i++) {
      out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
      out[j++] = toDigits[0x0F & data[i]];
    }
  }

  public static String encodeHexString(final byte[] data) {
    return new String(encodeHex(data));
  }

  public static int toDigit(final char ch, final int index) {
    int digit = Character.digit(ch, 16);
    if (digit == -1) {
      throw new IllegalArgumentException("Illegal hexadecimal character " + ch + " at index " + index + ".");
    }
    return digit;
  }
}
