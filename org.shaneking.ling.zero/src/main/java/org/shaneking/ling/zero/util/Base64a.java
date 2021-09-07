package org.shaneking.ling.zero.util;

/**
 * https://blog.csdn.net/wzl19870309/article/details/51372764
 */
public class Base64a {
  private static final char[] intToBase64 = {
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
    'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
    'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
    'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
    'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
    '9', '+', '/'
  };

  private static final int[] base64ToInt = {
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, 62, -1, -1, -1, 63, 52, 53,
    54, 55, 56, 57, 58, 59, 60, 61, -1, -1,
    -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
    5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
    15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
    25, -1, -1, -1, -1, -1, -1, 26, 27, 28,
    29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
    39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
    49, 50, 51
  };

  private static int base64Toint(char c) {
    int index = base64ToInt[c];
    if (index < 0) {
      throw new IllegalArgumentException("Illegal character " + c);
    }
    return index;
  }

  public static byte[] decode(String s) {
    int length = s.length();
    int groupNum = length / 4;
    if (groupNum * 4 != length) {
      throw new IllegalArgumentException("String length must be a multiple of 4.");
    }
    int lastMissingNum = 0;
    int numFullGroup = groupNum;
    if (length != 0) {
      if (s.charAt(length - 1) == '=') {
        lastMissingNum++;
        numFullGroup--;
      }
      if (s.charAt(length - 2) == '=') {
        lastMissingNum++;
      }
    }
    byte[] result = new byte[groupNum * 3 - lastMissingNum];
    int charIndex = 0;
    int resultIndex = 0;
    for (int i = 0; i < numFullGroup; i++) {
      int char0 = base64Toint(s.charAt(charIndex++));
      int char1 = base64Toint(s.charAt(charIndex++));
      int char2 = base64Toint(s.charAt(charIndex++));
      int char3 = base64Toint(s.charAt(charIndex++));
      result[resultIndex++] = (byte) (char0 << 2 | char1 >> 4);
      result[resultIndex++] = (byte) (char1 << 4 | char2 >> 2);
      result[resultIndex++] = (byte) (char2 << 6 | char3);
    }
    if (lastMissingNum != 0) {
      int char0 = base64Toint(s.charAt(charIndex++));
      int char1 = base64Toint(s.charAt(charIndex++));
      result[resultIndex++] = (byte) (char0 << 2 | char1 >> 4);
      if (lastMissingNum == 1) {
        int char2 = base64Toint(s.charAt(charIndex++));
        result[resultIndex++] = (byte) (char1 << 4 | char2 >> 2);
      }
    }
    return result;
  }

  public static String encode(byte[] a) {
    int totalLen = a.length;
    int groupNum = a.length / 3;
    int lastGroup = totalLen - groupNum * 3;
    int index = 0;
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < groupNum; i++) {
      int first = a[index++] & 0xff;
      int second = a[index++] & 0xff;
      int third = a[index++] & 0xff;
      result.append(intToBase64[first >> 2]);
      result.append(intToBase64[(first << 4) & 0x3f | second >> 4]);
      result.append(intToBase64[(second << 2) & 0x3f | third >> 6]);
      result.append(intToBase64[third & 0x3f]);
    }
    if (lastGroup != 0) {
      int first = a[index++] & 0xff;
      result.append(intToBase64[first >> 2]);
      if (lastGroup == 1) {
        result.append(intToBase64[(first << 4) & 0x3f]);
        result.append("==");
      } else {
        int second = a[index++] & 0xff;
        result.append(intToBase64[(first << 4) & 0x3f | second >> 4]);
        result.append(intToBase64[(second << 2) & 0x3f]);
        result.append("=");
      }
    }
    return result.toString();
  }
}
