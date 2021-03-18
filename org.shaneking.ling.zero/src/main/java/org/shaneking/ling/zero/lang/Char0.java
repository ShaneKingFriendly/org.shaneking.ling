package org.shaneking.ling.zero.lang;

public class Char0 {
  public static final char AMPERSAND = '&';
  public static final char ANGLE = '∠';
  public static final char APPROXIMATELY = '≈';
  public static final char ARROW = '→';
  public static final char ASTERISK = '*';
  public static final char BACKSLASH = '\\';
  public static final char BACKSPACE = '\b';
  public static final char BLANK = ' ';
  public static final char BR = '\n';
  public static final char CELSIUS = '℃';
  public static final char CIRCLE = '⊙';
  public static final char CIRCUMFERENCE = '○';
  public static final char CLOSE_BRACE = '}';
  public static final char CLOSE_BRACKET = ']';
  public static final char CLOSE_PARENTHESIS = ')';
  public static final char COLON = ':';
  public static final char COMMA = ',';
  public static final char DASH = '-';
  public static final char DEGREE = '°';
  public static final char DIVIDE = '÷';
  public static final char DOT = '.';
  public static final char DOUBLE_QUOTATION = '\"';
  public static final char EMAIL = '@';
  public static final char EQUAL = '=';
  public static final char EQUAL_APPROXIMATELY = '≌';
  public static final char EQUIVALENT = '≡';
  public static final char ESCAPE_B = '\b';
  public static final char ESCAPE_N = '\n';
  public static final char ESCAPE_R = '\r';
  public static final char ESCAPE_T = '\t';
  public static final char EXCLAMATION = '!';
  public static final char HENCE = '∴';
  public static final char INFINITY = '∞';
  public static final char INTEGRAL = '∫';
  public static final char INTERSECTION = '∩';
  public static final char LESS = '<';
  public static final char LESS_EQUAL = '≤';
  public static final char MINUS = '-';
  public static final char MINUTE = '′';
  public static final char MULTIPLY = '×';
  public static final char MORE = '>';
  public static final char MORE_EQUAL = '≥';
  public static final char NOT_EQUAL = '≠';
  public static final char NOT_LESS = '≮';
  public static final char NOT_MORE = '≯';
  public static final char OPEN_BRACE = '{';
  public static final char OPEN_BRACKET = '[';
  public static final char OPEN_PARENTHESIS = '(';
  public static final char PARALLEL = '‖';
  public static final char PERCENT = '%';
  public static final char PERMILL = '‰';
  public static final char PERPENDICULAR = '⊥';
  public static final char PI = 'π';
  public static final char PLUS = '+';
  public static final char PLUS_MINUS = '±';
  public static final char POUND = '#';
  public static final char PROPORTION = '∷';
  public static final char QUESTION = '?';
  public static final char SECOND = '〃';
  public static final char SECTION = '§';
  public static final char SEMICIRCLE = '⌒';
  public static final char SEMICOLON = ';';
  public static final char SIGMA = '∑';
  public static final char SINCE = '∵';
  public static final char SINGLE_QUOTATION = '\'';
  public static final char SLASH = '/';
  public static final char SQUARE = '√';
  public static final char TRIANGLE = '△';
  public static final char UNDERLINE = '_';
  public static final char UNION = '∪';
  public static final char VARIES = '∝';
  public static final char VERTICAL = '|';

  public static final char MALE = '♂';
  public static final char FEMALE = '♀';

  public static final char C_DOT = '。';

  public static final char Y = 'Y';//Yes
  public static final char N = 'N';//No
  public static final char T = 'T';//True
  public static final char F = 'F';//False/Failed
  public static final char S = 'S';//Successfully

  public static final char I = 'I';//Input
  public static final char O = 'O';//Output

  public static final char C7 = 0x07;//BEL=\b, bell
  public static final char C9 = 0x09;//TAB=\t, horizontal tab
  public static final char C10 = 0x0A;//LF=\n, NL line feed, new line
  public static final char C12 = 0x0C;//FF=\f, NP form feed, new page
  public static final char C13 = 0x0D;//CR=\r, carriage return
  public static final char C27 = 0x1B;//ESC=\e, escape
  public static final char C127 = 0x7F;//DEL

  public static boolean isAlphabet(char c) {
    return String0.ALPHABET.contains(String.valueOf(c));
  }

  public static boolean isAlphabetOrDigital(char c) {
    return isAlphabet(c) || isDigital(c);
  }

  public static boolean isDigital(char c) {
    return String0.DIGITAL.contains(String.valueOf(c));
  }
}
