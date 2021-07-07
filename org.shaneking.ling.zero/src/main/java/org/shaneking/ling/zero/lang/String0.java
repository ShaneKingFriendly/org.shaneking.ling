package org.shaneking.ling.zero.lang;

import lombok.NonNull;
import org.shaneking.ling.zero.util.List0;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class String0 {
  public static final String AMPERSAND = String.valueOf(Char0.AMPERSAND);
  public static final String ANGLE = String.valueOf(Char0.ANGLE);
  public static final String APPROXIMATELY = String.valueOf(Char0.APPROXIMATELY);
  public static final String ARROW = String.valueOf(Char0.ARROW);
  public static final String ASTERISK = String.valueOf(Char0.ASTERISK);
  public static final String BACKSLASH = String.valueOf(Char0.BACKSLASH);
  public static final String BACKSPACE = String.valueOf(Char0.BACKSPACE);
  public static final String BLANK = String.valueOf(Char0.BLANK);
  public static final String BR = String.valueOf(Char0.BR);
  public static final String CELSIUS = String.valueOf(Char0.CELSIUS);
  public static final String CIRCLE = String.valueOf(Char0.CIRCLE);
  public static final String CIRCUMFERENCE = String.valueOf(Char0.CIRCUMFERENCE);
  public static final String CLOSE_BRACE = String.valueOf(Char0.CLOSE_BRACE);
  public static final String CLOSE_BRACKET = String.valueOf(Char0.CLOSE_BRACKET);
  public static final String CLOSE_PARENTHESIS = String.valueOf(Char0.CLOSE_PARENTHESIS);
  public static final String COLON = String.valueOf(Char0.COLON);
  public static final String COMMA = String.valueOf(Char0.COMMA);
  public static final String DASH = String.valueOf(Char0.DASH);
  public static final String DEGREE = String.valueOf(Char0.DEGREE);
  public static final String DIVIDE = String.valueOf(Char0.DIVIDE);
  public static final String DOT = String.valueOf(Char0.DOT);
  public static final String DOUBLE_QUOTATION = String.valueOf(Char0.DOUBLE_QUOTATION);
  public static final String EMAIL = String.valueOf(Char0.EMAIL);
  public static final String EQUAL = String.valueOf(Char0.EQUAL);
  public static final String EQUAL_APPROXIMATELY = String.valueOf(Char0.EQUAL_APPROXIMATELY);
  public static final String EQUIVALENT = String.valueOf(Char0.EQUIVALENT);
  public static final String ESCAPE_B = String.valueOf(Char0.ESCAPE_B);
  public static final String ESCAPE_N = String.valueOf(Char0.ESCAPE_N);
  public static final String ESCAPE_R = String.valueOf(Char0.ESCAPE_R);
  public static final String ESCAPE_T = String.valueOf(Char0.ESCAPE_T);
  public static final String EXCLAMATION = String.valueOf(Char0.EXCLAMATION);
  public static final String HENCE = String.valueOf(Char0.HENCE);
  public static final String INFINITY = String.valueOf(Char0.INFINITY);
  public static final String INTEGRAL = String.valueOf(Char0.INTEGRAL);
  public static final String INTERSECTION = String.valueOf(Char0.INTERSECTION);
  public static final String LESS = String.valueOf(Char0.LESS);
  public static final String LESS_EQUAL = String.valueOf(Char0.LESS_EQUAL);
  public static final String MINUS = String.valueOf(Char0.MINUS);
  public static final String MINUTE = String.valueOf(Char0.MINUTE);
  public static final String MULTIPLY = String.valueOf(Char0.MULTIPLY);
  public static final String MORE = String.valueOf(Char0.MORE);
  public static final String MORE_EQUAL = String.valueOf(Char0.MORE_EQUAL);
  public static final String NOT_EQUAL = String.valueOf(Char0.NOT_EQUAL);
  public static final String NOT_LESS = String.valueOf(Char0.NOT_LESS);
  public static final String NOT_MORE = String.valueOf(Char0.NOT_MORE);
  public static final String OPEN_BRACE = String.valueOf(Char0.OPEN_BRACE);
  public static final String OPEN_BRACKET = String.valueOf(Char0.OPEN_BRACKET);
  public static final String OPEN_PARENTHESIS = String.valueOf(Char0.OPEN_PARENTHESIS);
  public static final String PARALLEL = String.valueOf(Char0.PARALLEL);
  public static final String PERCENT = String.valueOf(Char0.PERCENT);
  public static final String PERMILL = String.valueOf(Char0.PERMILL);
  public static final String PERPENDICULAR = String.valueOf(Char0.PERPENDICULAR);
  public static final String PI = String.valueOf(Char0.PI);
  public static final String PLUS = String.valueOf(Char0.PLUS);
  public static final String PLUS_MINUS = String.valueOf(Char0.PLUS_MINUS);
  public static final String POUND = String.valueOf(Char0.POUND);
  public static final String PROPORTION = String.valueOf(Char0.PROPORTION);
  public static final String QUESTION = String.valueOf(Char0.QUESTION);
  public static final String SECOND = String.valueOf(Char0.SECOND);
  public static final String SECTION = String.valueOf(Char0.SECTION);
  public static final String SEMICIRCLE = String.valueOf(Char0.SEMICIRCLE);
  public static final String SEMICOLON = String.valueOf(Char0.SEMICOLON);
  public static final String SIGMA = String.valueOf(Char0.SIGMA);
  public static final String SINCE = String.valueOf(Char0.SINCE);
  public static final String SINGLE_QUOTATION = String.valueOf(Char0.SINGLE_QUOTATION);
  public static final String SLASH = String.valueOf(Char0.SLASH);
  public static final String SQUARE = String.valueOf(Char0.SQUARE);
  public static final String TRIANGLE = String.valueOf(Char0.TRIANGLE);
  public static final String UNDERLINE = String.valueOf(Char0.UNDERLINE);
  public static final String UNION = String.valueOf(Char0.UNION);
  public static final String VARIES = String.valueOf(Char0.VARIES);
  public static final String VERTICAL = String.valueOf(Char0.VERTICAL);

  public static final String MALE = String.valueOf(Char0.MALE);
  public static final String FEMALE = String.valueOf(Char0.FEMALE);

  public static final String C_DOT = String.valueOf(Char0.C_DOT);

  public static final String Y = String.valueOf(Char0.Y);//Yes
  public static final String N = String.valueOf(Char0.N);//No
  public static final String T = String.valueOf(Char0.T);//True
  public static final String F = String.valueOf(Char0.F);//False/Failed
  public static final String S = String.valueOf(Char0.S);//Successfully

  public static final String I = String.valueOf(Char0.I);//Input
  public static final String O = String.valueOf(Char0.O);//Output

  public static final String C7 = String.valueOf(Char0.C7);
  public static final String C9 = String.valueOf(Char0.C9);
  public static final String C10 = String.valueOf(Char0.C10);
  public static final String C12 = String.valueOf(Char0.C12);
  public static final String C13 = String.valueOf(Char0.C13);
  public static final String C27 = String.valueOf(Char0.C27);
  public static final String C127 = String.valueOf(Char0.C127);

  public static final String BR_LINUX = "\n";
  public static final String BR_MACOS = "\r";
  public static final String BR_WINOS = "\r\n";

  public static final String EMPTY = "";
  public static final String NULL = "NULL";

  public static final String ARY_BIN = "01";
  public static final String ARY_OCT = "01234567";
  public static final String ARY_DEC = "0123456789";
  public static final String DIGITAL = ARY_DEC;
  public static final String ARY_HEX = "0123456789abcdef";
  public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static final String ARY_L62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

  //first_name -&gt; firstName
  public static String dbColumn2Field(String string) {
    return dbColumn2Field(string, UNDERLINE);
  }

  public static String dbColumn2Field(String string, String with) {
    return lowerFirst(dbColumn2SetField(string, with));
  }

  //first_name -&gt; FirstName
  public static String dbColumn2SetField(String string) {
    return dbColumn2SetField(string, UNDERLINE);
  }

  public static String dbColumn2SetField(String string, String with) {
    return String0.isNullOrEmpty(string) ? string : String.join(EMPTY, List0.newArrayList(string.split(with)).stream().map(String0::upperFirst).collect(Collectors.toList()));
  }

  public static String emptyToNull(String s) {
    return EMPTY.equals(s) ? null : s;
  }

  //firstName -&gt; first_name
  public static String field2DbColumn(String string) {
    return field2DbColumn(string, UNDERLINE);
  }

  public static String field2DbColumn(String string, String with) {
    return String0.isNullOrEmpty(string) ? string : String.join(EMPTY, List0.newArrayList(string.split(EMPTY)).stream().map(alphabet -> alphabet.equals(alphabet.toUpperCase()) ? with + alphabet.toLowerCase() : alphabet).collect(Collectors.toList()));
  }

  public static String format(@NonNull String pattern, @NonNull Object... args) {
    String rePattern = pattern;
    Matcher m = Pattern.compile("\\{(\\d)\\}").matcher(rePattern);
    while (m.find()) {
      rePattern = rePattern.replace(m.group(), String.valueOf(args[Integer.parseInt(m.group(1))]));
    }
    return rePattern;
  }

  public static boolean isNull2Empty(String s) {
    return isNullOrEmpty(s) || Object0.NULL.equals(s);
  }

  public static boolean isNullOrEmpty(String s) {
    return s == null || s.isEmpty();
  }

  public static boolean isNumeric(String s) {
    boolean rtn = !isNullOrEmpty(s);
    for (int i = 0; rtn && i < s.length(); i++) {
      rtn = Char0.isDigital(s.charAt(i));
    }
    return rtn;
  }

  public static String join(String d, String... ss) {
    StringBuilder sb = new StringBuilder();
    for (String s : ss) {
      if (!String0.isNullOrEmpty(s)) {
        sb.append(d).append(s);
      }
    }
    return sb.length() > d.length() ? sb.substring(d.length()) : sb.toString();
  }

  public static String lower(String s) {
    return s == null ? null : s.toLowerCase();
  }

  public static String lowerFirst(String s) {
    return String0.isNullOrEmpty(s) ? s : (s.substring(0, 1).toLowerCase() + s.substring(1));
  }

  public static String maxLenStr(String s, int max) {
    return String0.isNullOrEmpty(s) ? s : (s.length() < max ? s : s.substring(0, max));
  }

  public static String notNull2EmptyTo(String s, String d) {
    return isNull2Empty(s) ? s : d;
  }

  public static String notNull2EmptyTo(String s, @NonNull Supplier<String> ss) {
    return isNull2Empty(s) ? s : ss.get();
  }

  public static String notNullOrEmptyTo(String s, String d) {
    return isNullOrEmpty(s) ? s : d;
  }

  public static String notNullOrEmptyTo(String s, @NonNull Supplier<String> ss) {
    return isNullOrEmpty(s) ? s : ss.get();
  }

  public static String null2EmptyTo(String s, String d) {
    return isNull2Empty(s) ? d : s;
  }

  public static String null2EmptyTo(String s, @NonNull Supplier<String> ss) {
    return isNull2Empty(s) ? ss.get() : s;
  }

  public static String nullOrEmptyTo(String s, String d) {
    return isNullOrEmpty(s) ? d : s;
  }

  public static String nullOrEmptyTo(String s, @NonNull Supplier<String> ss) {
    return isNullOrEmpty(s) ? ss.get() : s;
  }

  public static String nullTo(String s, String d) {
    return s == null ? d : s;
  }

  public static String nullToEmpty(String s) {
    return nullTo(s, EMPTY);
  }

  public static String repeat(String string, int count) {
    String[] array = new String[count];
    Arrays.fill(array, string);
    return String.join(EMPTY, array);
  }

  public static int sameTotal(String s1, String s2) {
    int rtn = 0;
    if (!String0.isNullOrEmpty(s1) && !String0.isNullOrEmpty(s2)) {
      int min = Math.min(s1.length(), s2.length());
      for (int i = 0; i < min; i++) {
        if (s1.charAt(i) == s2.charAt(i)) {
          rtn++;
        }
      }
    }
    return rtn;
  }

  public static String type(@NonNull Object a) {
    return a.getClass().toString();
  }

  public static String upper(String s) {
    return s == null ? null : s.toUpperCase();
  }

  public static String upperFirst(String s) {
    return String0.isNullOrEmpty(s) ? s : (s.substring(0, 1).toUpperCase() + s.substring(1));
  }

  public static String valueOf(Object o) {
    return o == null ? EMPTY : o.toString();
  }

  public static String wrapBlack(String s) {
    return wrap(s, BLANK);
  }

  public static String wrapBrace(String s) {
    return wrap(s, OPEN_BRACE, CLOSE_BRACE);
  }

  public static String wrapBracket(String s) {
    return wrap(s, OPEN_BRACKET, CLOSE_BRACKET);
  }

  public static String wrapParenthesis(String s) {
    return wrap(s, OPEN_PARENTHESIS, CLOSE_PARENTHESIS);
  }

  public static String wrap(String s, String around) {
    return wrap(s, around, around);
  }

  public static String wrap(String s, String open, String close) {
    return open + s + close;
  }
}
