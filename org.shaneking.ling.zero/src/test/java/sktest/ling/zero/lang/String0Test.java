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
    assertEquals(String0.Y, String0.notNull2EmptyTo(String0.N, String0.Y));
    assertEquals(String0.Y, String0.notNull2EmptyTo(String0.N, () -> String0.Y));
    assertEquals(String0.EMPTY, String0.notNull2EmptyTo(String0.EMPTY, String0.Y));
    assertEquals(String0.EMPTY, String0.notNull2EmptyTo(String0.EMPTY, () -> String0.Y));
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
}
