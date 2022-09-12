package org.shaneking.ling.zero.util;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.String0;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex0 {
  public static final String BACKSLASH = "\\\\";
  public static final String BACKSPACE = "\\b";
  public static final String BLACK = "\\s";
  public static final String BLACKS = "\\s+";
  public static final String BR_LINUX = "\\n";
  public static final String BR_MACOS = "\\r";
  public static final String BR_WINOS = BR_MACOS + BR_LINUX;
  public static final String CLOSE_BRACKET = "\\]";
  public static final String DOT = "\\.";
  public static final String ESCAPE = "\\e";
  public static final String FORM_FEED = "\\f";
  public static final String OPEN_BRACKET = "\\[";
  public static final String SQL_BLACKS_SPLIT = String0.SEMICOLON + BLACK + String0.PLUS + BR_LINUX;
  public static final String SQL_SPLIT = String0.SEMICOLON + BR_LINUX;
  public static final String TAB = "\\t";

  public static List<String> findAll(@NonNull String pattern, @NonNull String string) {
    List<String> rtn = List0.newArrayList();
    Matcher m = Pattern.compile(pattern).matcher(string);
    while (m.find()) {
      rtn.add(m.group(1));
    }
    return rtn;
  }
}
