package org.shaneking.ling.zero.util;

import org.shaneking.ling.zero.lang.String0;

public class Regex0 {
  public static final String BACKSLASH = "\\\\";
  public static final String BACKSPACE = "\\b";
  public static final String BLACK = "\\s";
  public static final String BLACKS = "\\s+";
  public static final String BR_LINUX = "\\n";
  public static final String BR_MACOS = "\\r";
  public static final String BR_WINOS = BR_MACOS + BR_LINUX;
  public static final String DOT = "\\.";
  public static final String ESCAPE = "\\e";
  public static final String FORM_FEED = "\\f";
  public static final String SQL_BLACKS_SPLIT = String0.SEMICOLON + BLACK + String0.PLUS + BR_LINUX;
  public static final String SQL_SPLIT = String0.SEMICOLON + BR_LINUX;
  public static final String TAB = "\\t";
}
