package org.shaneking.ling.zero.util;

public class Hex0 {
  private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

  public static char[] encodeHex(final byte[] data) {
    return encodeHex(data, true);
  }

  public static char[] encodeHex(final byte[] data, final boolean toLowerCase) {
    return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
  }

  protected static char[] encodeHex(final byte[] data, final char[] toDigits) {
    final int l = data.length;
    final char[] out = new char[l << 1];
    encodeHex(data, 0, data.length, toDigits, out, 0);
    return out;
  }

  private static void encodeHex(final byte[] data, final int dataOffset, final int dataLen, final char[] toDigits, final char[] out, final int outOffset) {
    // two characters form the hex value.
    for (int i = dataOffset, j = outOffset; i < dataOffset + dataLen; i++) {
      out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
      out[j++] = toDigits[0x0F & data[i]];
    }
  }

  public static String encodeHexString(final byte[] data) {
    return new String(encodeHex(data));
  }
}
