package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Base85a;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Base85aTest {
  //this need static
  static Stream<String> methodSource() {
    return Stream.of("ShaneKing", "有梦才有精彩！", "爱存不存", "About", "这是 ShaneKing 的个人博客，始建于2011.10.10，算是个技术类博客吧（毕竟博主是个敲代码的）");
  }

  @Test
  void constructor() {
    assertAll(
      () -> assertNotNull(new Base85a())
    );
  }

  @ParameterizedTest
//  @EmptySource
  @MethodSource("methodSource")
//  @NullSource
  @NullAndEmptySource
  @ValueSource(strings = {"!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstu", "測試中", "اختبارات", "A", "AB", "ABC", "ABCD", "ABCDE", "ABCDEF", "ABCDEFG", "ABCDEFH", "ABCDEFHI", "ABCDEFHIJ", "ABCDEFHIJK"})
  void base85a(String s) {
    if (!String0.isNullOrEmpty(s)) {
      assertAll(
        () -> assertEquals(s, Base85a.getZ85Decoder().decode(Base85a.getZ85Encoder().encode(s)))
      );
    }
  }

  @Test
  void getRfc1924Encoder() {
    assertNotNull(Base85a.getRfc1924Encoder());
  }

  @Test
  void getRfc1924Decoder() {
    assertNotNull(Base85a.getRfc1924Decoder());
  }

  @Test
  void getAscii85Encoder() {
    assertNotNull(Base85a.getAscii85Encoder());
  }

  @Test
  void getAscii85Decoder() {
    assertNotNull(Base85a.getAscii85Decoder());
  }
}
