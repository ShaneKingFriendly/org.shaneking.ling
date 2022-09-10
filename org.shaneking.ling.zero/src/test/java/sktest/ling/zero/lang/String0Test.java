package sktest.ling.zero.lang;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.Object0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Date0;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import java.util.List;
import java.util.Map;

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
  void emptyToNull() {
    assertNull(String0.emptyToNull(""));
    assertNotNull(String0.emptyToNull("ab#cd#ef"));
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
    assertEquals("[0][1][2][3][4][5][6][7][8][9][10][11][12][13]"
      , String0.format("[{0}][{1}][{2}][{3}][{4}][{5}][{6}][{7}][{8}][{9}][{10}][{11}][{12}][{13}]"
        , "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"));
  }

  @Test
  void fmt() {
    assertEquals("I Love You", String0.fmt("I{}Love{}You", String0.BLANK, String0.BLANK));
  }

  @Test
  void isNull2Empty() {
    assertTrue(String0.isNull2Empty(null));
    assertTrue(String0.isNull2Empty(Object0.NULL));
    assertFalse(String0.isNull2Empty(String0.BLANK));
    assertTrue(String0.isNull2Empty(String0.EMPTY));
    assertFalse(String0.isNull2Empty(String0.F));
    assertFalse(String0.isNull2Empty(String0.NULL));
  }

  @Test
  void isNullOrEmpty() {
    assertTrue(String0.isNullOrEmpty(null));
    assertFalse(String0.isNullOrEmpty(Object0.NULL));
    assertFalse(String0.isNullOrEmpty(String0.BLANK));
    assertTrue(String0.isNullOrEmpty(String0.EMPTY));
    assertFalse(String0.isNullOrEmpty(String0.F));
    assertFalse(String0.isNullOrEmpty(String0.NULL));
  }

  @Test
  void isNumeric() {
    assertAll(
      () -> assertFalse(String0.isNumeric(null)),
      () -> assertFalse(String0.isNumeric(Object0.NULL)),
      () -> assertTrue(String0.isNumeric(String0.ARY_BIN)),
      () -> assertTrue(String0.isNumeric(String0.ARY_DEC)),
      () -> assertFalse(String0.isNumeric(String0.ARY_HEX)),
      () -> assertTrue(String0.isNumeric(String0.ARY_OCT)),
      () -> assertFalse(String0.isNumeric(String0.BLANK)),
      () -> assertTrue(String0.isNumeric(String0.DIGITAL)),
      () -> assertFalse(String0.isNumeric(String0.EMPTY)),
      () -> assertFalse(String0.isNumeric(String0.NULL))
    );
  }

  @Test
  void lower() {
    assertAll(
      () -> assertEquals("lowerfirst", String0.lower("LowerFirst")),
      () -> assertNull(String0.lower(null)),
      () -> assertEquals(String0.EMPTY, String0.lower(String0.EMPTY))
    );
  }

  @Test
  void lowerFirst() {
    assertAll(
      () -> assertEquals("lowerFirst", String0.lowerFirst("LowerFirst")),
      () -> assertNull(String0.lowerFirst(null)),
      () -> assertEquals(String0.EMPTY, String0.lowerFirst(String0.EMPTY))
    );
  }

  @Test
  void notNull2EmptyTo() {
    assertEquals(Object0.NULL, String0.notNull2EmptyTo(Object0.NULL, String0.Y));
    assertEquals(String0.Y, String0.notNull2EmptyTo(String0.N, String0.Y));

    assertEquals(Object0.NULL, String0.notNull2EmptyTo(Object0.NULL, () -> String0.Y));
    assertEquals(String0.Y, String0.notNull2EmptyTo(String0.N, () -> String0.Y));
  }

  @Test
  void notNullOrEmptyTo() {
    assertEquals(String0.EMPTY, String0.notNullOrEmptyTo(String0.EMPTY, String0.Y));
    assertEquals(String0.Y, String0.notNullOrEmptyTo(String0.N, String0.Y));

    assertEquals(String0.EMPTY, String0.notNullOrEmptyTo(String0.EMPTY, () -> String0.Y));
    assertEquals(String0.Y, String0.notNullOrEmptyTo(String0.N, () -> String0.Y));
  }

  @Test
  void null2EmptyTo() {
    assertEquals(String0.Y, String0.null2EmptyTo(Object0.NULL, String0.Y));
    assertEquals(String0.N, String0.null2EmptyTo(String0.N, String0.Y));

    assertEquals(String0.Y, String0.null2EmptyTo(Object0.NULL, () -> String0.Y));
    assertEquals(String0.N, String0.null2EmptyTo(String0.N, () -> String0.Y));
  }

  @Test
  void nullOrEmptyTo() {
    assertEquals(String0.Y, String0.nullOrEmptyTo(String0.EMPTY, String0.Y));
    assertEquals(Object0.NULL, String0.nullOrEmptyTo(Object0.NULL, String0.Y));

    assertEquals(String0.Y, String0.nullOrEmptyTo(String0.EMPTY, () -> String0.Y));
    assertEquals(Object0.NULL, String0.nullOrEmptyTo(Object0.NULL, () -> String0.Y));
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
  void type() {
    //四种整数类型(byte、short、int、long)
    byte b = 1;//byte：8 位，用于表示最小数据单位，如文件中数据，-128~127
    short s = 1;//short：16 位，-32768 ~ 32767
    int i = 1;//int：32 位，-2^31-1~2^31 （21 亿）
    long l = 1;//long：64 位
    //两种浮点数类型(float、double
    float f = 1;//float：32 位，后缀 F 或 f
    double d = 1;//double：64 位，最常用，后缀 D 或 d
    //一种字符类型(char)
    char c = 1;//char：16 位，是整数类型，用单引号括起来的 1 个字符（可以是一个中文字符）///注意事项： 不能为 0个字符。
    //一种布尔类型(boolean)
    boolean boo = true;//true 真 和 false 假

    byte[] ba = new byte[]{1};
    short[] sa = new short[]{1};
    int[] ia = new int[]{1};
    long[] la = new long[]{1};
    float[] fa = new float[]{1};
    double[] da = new double[]{1};
    char[] ca = new char[]{1};
    boolean[] booa = new boolean[]{true};

    Byte[] boa = new Byte[]{1};
    Short[] soa = new Short[]{1};
    Integer[] ioa = new Integer[]{1};
    Long[] loa = new Long[]{1l};
    Float[] foa = new Float[]{1f};
    Double[] doa = new Double[]{1d};
    Character[] coa = new Character[]{1};
    Boolean[] boooa = new Boolean[]{true};

    String str = "1";
    String[] stra = new String[]{"1"};

    Object obj = new Object();
    Object[] obja = new Object[]{};

    List list = List0.newArrayList();
    Map map = Map0.newHashMap();

    Object n = null;

    assertAll(
      () -> assertEquals("class java.lang.Byte", String0.type(b)),
      () -> assertEquals("class java.lang.Short", String0.type(s)),
      () -> assertEquals("class java.lang.Integer", String0.type(i)),
      () -> assertEquals("class java.lang.Long", String0.type(l)),
      () -> assertEquals("class java.lang.Float", String0.type(f)),
      () -> assertEquals("class java.lang.Double", String0.type(d)),
      () -> assertEquals("class java.lang.Character", String0.type(c)),
      () -> assertEquals("class java.lang.Boolean", String0.type(boo)),

      () -> assertEquals("class [B", String0.type(ba)),
      () -> assertEquals("class [S", String0.type(sa)),
      () -> assertEquals("class [I", String0.type(ia)),
      () -> assertEquals("class [J", String0.type(la)),///
      () -> assertEquals("class [F", String0.type(fa)),
      () -> assertEquals("class [D", String0.type(da)),
      () -> assertEquals("class [C", String0.type(ca)),
      () -> assertEquals("class [Z", String0.type(booa)),///

      () -> assertEquals("class [Ljava.lang.Byte;", String0.type(boa)),
      () -> assertEquals("class [Ljava.lang.Short;", String0.type(soa)),
      () -> assertEquals("class [Ljava.lang.Integer;", String0.type(ioa)),
      () -> assertEquals("class [Ljava.lang.Long;", String0.type(loa)),
      () -> assertEquals("class [Ljava.lang.Float;", String0.type(foa)),
      () -> assertEquals("class [Ljava.lang.Double;", String0.type(doa)),
      () -> assertEquals("class [Ljava.lang.Character;", String0.type(coa)),
      () -> assertEquals("class [Ljava.lang.Boolean;", String0.type(boooa)),

      () -> assertEquals("class java.lang.String", String0.type(str)),
      () -> assertEquals("class [Ljava.lang.String;", String0.type(stra)),

      () -> assertEquals("class java.lang.Object", String0.type(obj)),
      () -> assertEquals("class [Ljava.lang.Object;", String0.type(obja)),

      () -> assertEquals("class java.util.ArrayList", String0.type(list)),
      () -> assertEquals("class java.util.HashMap", String0.type(map)),

      () -> assertThrows(NullPointerException.class, () -> String0.type(n))
    );
  }

  @Test
  void upper() {
    assertAll(
      () -> assertEquals("UPPERFIRST", String0.upper("upperFirst")),
      () -> assertNull(String0.upper(null)),
      () -> assertEquals(String0.EMPTY, String0.upper(String0.EMPTY))
    );
  }

  @Test
  void upperFirst() {
    assertAll(
      () -> assertEquals("UpperFirst", String0.upperFirst("upperFirst")),
      () -> assertNull(String0.upperFirst(null)),
      () -> assertEquals(String0.EMPTY, String0.upperFirst(String0.EMPTY))
    );
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
    assertEquals(String0.OPEN_BRACE + "null" + String0.CLOSE_BRACE, String0.wrapBrace(null));
    assertEquals(String0.OPEN_BRACE + String0.BLANK + String0.CLOSE_BRACE, String0.wrapBrace(String0.BLANK));

    assertNull(String0.wrapBrace(null, true));
  }

  @Test
  void wrapBracket() {
    assertEquals(String0.OPEN_BRACKET + "null" + String0.CLOSE_BRACKET, String0.wrapBracket(null));
    assertEquals(String0.OPEN_BRACKET + String0.BLANK + String0.CLOSE_BRACKET, String0.wrapBracket(String0.BLANK));

    assertNull(String0.wrapBracket(null, true));
  }

  @Test
  void wrapParenthesis() {
    assertEquals(String0.OPEN_PARENTHESIS + "null" + String0.CLOSE_PARENTHESIS, String0.wrapParenthesis(null));
    assertEquals(String0.OPEN_PARENTHESIS + String0.BLANK + String0.CLOSE_PARENTHESIS, String0.wrapParenthesis(String0.BLANK));

    assertNull(String0.wrapParenthesis(null, true));
  }
}
