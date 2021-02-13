package org.shaneking.ling.zero.security;

import java.security.SecureRandom;

public class SR0 {
  private static final SecureRandom SR = new SecureRandom();

  public static int nextInt(int max) {
    return SR.nextInt() % max;
  }
}
