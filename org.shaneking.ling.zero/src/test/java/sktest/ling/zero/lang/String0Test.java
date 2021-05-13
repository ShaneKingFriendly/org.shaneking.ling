package sktest.ling.zero.lang;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.Object0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Date0;

import static org.junit.jupiter.api.Assertions.*;

class String0Test {

  @Test
  void dbColumn2Field() {
    assertEquals("abCdEf", String0.dbColumn2Field("ab_cd_ef"));
    assertEquals("abCdEf", String0.dbColumn2Field("ab#cd#ef", String0.POUND));
  }

  @Test
  void dbColumn2SetField() {
    assertEquals("AbCdEf", String0.dbColumn2SetField("ab_cd_ef"));
    assertEquals("AbCdEf", String0.dbColumn2SetField("ab#cd#ef", String0.POUND));
  }

  @Test
  void field2DbColumn() {
    assertEquals("id", String0.field2DbColumn("id"));
    assertEquals("ab_cd_ef", String0.field2DbColumn("abCdEf"));
    assertEquals("ab#cd#ef", String0.field2DbColumn("abCdEf", String0.POUND));
  }

  @Test
  void format() {
    assertEquals("I Love You", String0.format("I{0}Love{1}You", String0.BLANK, String0.BLANK));
  }

  @Test
  void isNull2Empty() {
    assertTrue(String0.isNull2Empty(null));
    assertTrue(String0.isNull2Empty(String0.EMPTY));
    assertTrue(String0.isNull2Empty(Object0.NULL));
    assertFalse(String0.isNull2Empty(String0.NULL));
    assertFalse(String0.isNull2Empty(String0.BLANK));
    assertFalse(String0.isNull2Empty(String0.F));
  }

  @Test
  void isNullOrEmpty() {
    assertTrue(String0.isNullOrEmpty(null));
    assertTrue(String0.isNullOrEmpty(String0.EMPTY));
    assertFalse(String0.isNullOrEmpty(Object0.NULL));
    assertFalse(String0.isNullOrEmpty(String0.NULL));
    assertFalse(String0.isNullOrEmpty(String0.BLANK));
    assertFalse(String0.isNullOrEmpty(String0.F));
  }

  @Test
  void isNumeric() {
    assertAll(
      () -> assertFalse(String0.isNumeric(null)),
      () -> assertFalse(String0.isNumeric(String0.EMPTY)),
      () -> assertFalse(String0.isNumeric(String0.BLANK)),
      () -> assertFalse(String0.isNumeric(Object0.NULL)),
      () -> assertFalse(String0.isNumeric(String0.NULL)),
      () -> assertTrue(String0.isNumeric(String0.ARY_BIN)),
      () -> assertTrue(String0.isNumeric(String0.ARY_OCT)),
      () -> assertTrue(String0.isNumeric(String0.ARY_DEC)),
      () -> assertTrue(String0.isNumeric(String0.DIGITAL)),
      () -> assertFalse(String0.isNumeric(String0.ARY_HEX))
    );
  }

  @Test
  void lowerFirst() {
    assertEquals("lowerFirst", String0.lowerFirst("LowerFirst"));
  }

  @Test
  void notNull2EmptyTo() {
    assertEquals(String0.Y, String0.notNull2EmptyTo(String0.N, String0.Y));
    assertEquals(String0.Y, String0.notNull2EmptyTo(String0.N, () -> String0.Y));
    assertEquals(Object0.NULL, String0.notNull2EmptyTo(Object0.NULL, String0.Y));
    assertEquals(Object0.NULL, String0.notNull2EmptyTo(Object0.NULL, () -> String0.Y));
  }

  @Test
  void notNullOrEmptyTo() {
    assertEquals(String0.Y, String0.notNullOrEmptyTo(String0.N, String0.Y));
    assertEquals(String0.Y, String0.notNullOrEmptyTo(String0.N, () -> String0.Y));
    assertEquals(String0.EMPTY, String0.notNullOrEmptyTo(String0.EMPTY, String0.Y));
    assertEquals(String0.EMPTY, String0.notNullOrEmptyTo(String0.EMPTY, () -> String0.Y));
  }

  @Test
  void null2EmptyTo() {
    assertEquals(String0.Y, String0.null2EmptyTo(Object0.NULL, String0.Y));
    assertEquals(String0.Y, String0.null2EmptyTo(Object0.NULL, () -> String0.Y));
    assertEquals(String0.N, String0.null2EmptyTo(String0.N, String0.Y));
    assertEquals(String0.N, String0.null2EmptyTo(String0.N, () -> String0.Y));
  }

  @Test
  void nullOrEmptyTo() {
    assertEquals(Object0.NULL, String0.nullOrEmptyTo(Object0.NULL, String0.Y));
    assertEquals(Object0.NULL, String0.nullOrEmptyTo(Object0.NULL, () -> String0.Y));
    assertEquals(String0.Y, String0.nullOrEmptyTo(String0.EMPTY, String0.Y));
    assertEquals(String0.Y, String0.nullOrEmptyTo(String0.EMPTY, () -> String0.Y));
  }

  @Test
  void nullToEmpty() {
    assertEquals(String0.EMPTY, String0.nullToEmpty(null));
    assertEquals(String0.Y, String0.nullToEmpty(String0.Y));
  }

  @Test
  void repeat() {
    assertEquals(Date0.SSS, String0.repeat(String0.S, 3));
  }

  @Test
  void sameTotal() {
    assertEquals(1, String0.sameTotal(String0.S, Date0.SSS));
  }

  @Test
  void upperFirst() {
    assertEquals("UpperFirst", String0.upperFirst("upperFirst"));
  }

  @Test
  void valueOf() {
    assertEquals(String0.EMPTY, String0.valueOf(null));
    Object o = null;
    assertEquals(Object0.NULL, String.valueOf(o));
  }

  @Test
  void wrapBlack() {
    assertEquals(String0.BLANK + String0.BLANK + String0.BLANK, String0.wrapBlack(String0.BLANK));
  }

  @Test
  void wrapBrace() {
    assertEquals(String0.OPEN_BRACE + String0.BLANK + String0.CLOSE_BRACE, String0.wrapBrace(String0.BLANK));
  }

  @Test
  void wrapBracket() {
    assertEquals(String0.OPEN_BRACKET + String0.BLANK + String0.CLOSE_BRACKET, String0.wrapBracket(String0.BLANK));
  }

  @Test
  void wrapParenthesis() {
    assertEquals(String0.OPEN_PARENTHESIS + String0.BLANK + String0.CLOSE_PARENTHESIS, String0.wrapParenthesis(String0.BLANK));
  }
}
