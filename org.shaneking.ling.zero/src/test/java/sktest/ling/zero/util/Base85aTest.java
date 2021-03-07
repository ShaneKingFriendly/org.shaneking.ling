package sktest.ling.zero.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Base85a;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Base85aTest {
  //this need static
  static Stream<String> methodSource() {
    return Stream.of("ShaneKing", "有梦才有精彩！", "爱存不存", "About", "这是 ShaneKing 的个人博客，始建于2011.10.10，算是个技术类博客吧（毕竟博主是个敲代码的）");
  }

  @ParameterizedTest
  @EmptySource
  @MethodSource("methodSource")
  @NullSource
  @NullAndEmptySource
  @ValueSource(strings = {"!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstu", "測試中", "اختبارات", "A", "AB", "ABC", "ABCD", "ABCDE", "ABCDEF", "ABCDEFG", "ABCDEFH", "ABCDEFHI", "ABCDEFHIJ", "ABCDEFHIJK"})
  void base85a(String s) {
    if (!String0.isNullOrEmpty(s)) {
      assertAll(
        () -> assertEquals(s, Base85a.getZ85Decoder().decode(Base85a.getZ85Encoder().encode(s)))
      );
    }
  }
}
