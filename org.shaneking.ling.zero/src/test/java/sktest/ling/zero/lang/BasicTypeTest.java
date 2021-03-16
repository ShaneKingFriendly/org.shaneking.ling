package sktest.ling.zero.lang;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.BasicType;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BasicTypeTest {
  @Test
  void typeString() {
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
      () -> assertEquals("class java.lang.Byte", BasicType.getType(b)),
      () -> assertEquals("class java.lang.Short", BasicType.getType(s)),
      () -> assertEquals("class java.lang.Integer", BasicType.getType(i)),
      () -> assertEquals("class java.lang.Long", BasicType.getType(l)),
      () -> assertEquals("class java.lang.Float", BasicType.getType(f)),
      () -> assertEquals("class java.lang.Double", BasicType.getType(d)),
      () -> assertEquals("class java.lang.Character", BasicType.getType(c)),
      () -> assertEquals("class java.lang.Boolean", BasicType.getType(boo)),

      () -> assertEquals("class [B", BasicType.getType(ba)),
      () -> assertEquals("class [S", BasicType.getType(sa)),
      () -> assertEquals("class [I", BasicType.getType(ia)),
      () -> assertEquals("class [J", BasicType.getType(la)),///
      () -> assertEquals("class [F", BasicType.getType(fa)),
      () -> assertEquals("class [D", BasicType.getType(da)),
      () -> assertEquals("class [C", BasicType.getType(ca)),
      () -> assertEquals("class [Z", BasicType.getType(booa)),///

      () -> assertEquals("class [Ljava.lang.Byte;", BasicType.getType(boa)),
      () -> assertEquals("class [Ljava.lang.Short;", BasicType.getType(soa)),
      () -> assertEquals("class [Ljava.lang.Integer;", BasicType.getType(ioa)),
      () -> assertEquals("class [Ljava.lang.Long;", BasicType.getType(loa)),
      () -> assertEquals("class [Ljava.lang.Float;", BasicType.getType(foa)),
      () -> assertEquals("class [Ljava.lang.Double;", BasicType.getType(doa)),
      () -> assertEquals("class [Ljava.lang.Character;", BasicType.getType(coa)),
      () -> assertEquals("class [Ljava.lang.Boolean;", BasicType.getType(boooa)),

      () -> assertEquals("class java.lang.String", BasicType.getType(str)),
      () -> assertEquals("class [Ljava.lang.String;", BasicType.getType(stra)),

      () -> assertEquals("class java.lang.Object", BasicType.getType(obj)),
      () -> assertEquals("class [Ljava.lang.Object;", BasicType.getType(obja)),

      () -> assertEquals("class java.util.ArrayList", BasicType.getType(list)),
      () -> assertEquals("class java.util.HashMap", BasicType.getType(map)),

      () -> assertThrows(NullPointerException.class, () -> BasicType.getType(n))
    );
  }
}
