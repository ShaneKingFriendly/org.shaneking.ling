package org.shaneking.ling.mock.type;

import org.shaneking.ling.zero.lang.Integer0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.security.SR0;

import java.util.List;

public class Basic {
  public static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
  public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static final String NUMBER = "0123456789";
  public static final String SYMBOL = "!@#$%^&*()[]";
  public static final String ALPHA = LOWER + UPPER;
  public static final String BETA = ALPHA + NUMBER + SYMBOL;

  //list
  public static String list(List<String> pool) {
    return pool == null || pool.size() == 0 ? null : pool.get(SR0.absInt(pool.size()));
  }

  //array
  public static String array(String[] pool) {
    return pool == null || pool.length == 0 ? null : pool[SR0.absInt(pool.length)];
  }

  //natural:0,1,2,3,4,5,6,7...10,11,12...
  public static int natural(int min, int max) {
    int mi = Integer0.lt2d(min, 0);
    int ma = Integer0.lt2d(max, 2);
    return mi + SR0.absInt(ma - mi);
  }

  //character
  public static char character() {
    return character(null);
  }

  public static char character(String pool) {
    String p = String0.nullTo(pool, BETA);
    return p.charAt(SR0.absInt(p.length()));
  }

  //String
  public static String string() {
    return string(BETA);
  }

  public static String string(int min, int max) {
    return string(BETA, min, max);
  }

  public static String string(int len) {
    return string(BETA, len);
  }

  public static String string(String pool) {
    return string(pool, 3, 7);
  }

  public static String string(String pool, int min, int max) {
    return string(pool, natural(min, max));
  }

  public static String string(String pool, int len) {
    StringBuilder rtn = new StringBuilder(String0.EMPTY);
    for (int i = 0; i < len; i++) {
      rtn.append(character(pool));
    }
    return rtn.toString();
  }
}
