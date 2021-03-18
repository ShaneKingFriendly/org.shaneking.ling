package org.shaneking.ling.mock;

import org.shaneking.ling.mock.regex.RandomStringGenerator;

import java.security.SecureRandom;

public class Mock {
  public static final RandomStringGenerator RSG = new RandomStringGenerator(new SecureRandom());

  public static String patStr(String pattern) {
    return RSG.generateFromPattern(pattern);
  }

  public static String regStr(String regex) {
    return RSG.generateByRegex(regex);
  }
}
