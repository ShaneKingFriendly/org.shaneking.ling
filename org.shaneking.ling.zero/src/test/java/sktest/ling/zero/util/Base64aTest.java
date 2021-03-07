package sktest.ling.zero.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Base64a;

import java.util.Base64;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

//java.util.Base64 != org.shaneking.ling.zero.util.Base64a
class Base64aTest {

  //this need static
  static Stream<String> methodSource() {
    return Stream.of("ShaneKing", "有梦才有精彩！", "爱存不存", "About", "这是 ShaneKing 的个人博客，始建于2011.10.10，算是个技术类博客吧（毕竟博主是个敲代码的）");
  }

  @ParameterizedTest
  @EmptySource
  @MethodSource("methodSource")
  @NullSource
  @NullAndEmptySource
//  @ValueSource(strings = {"从懵懂的Wordpress，至Java+Bootstrap自建，到现在的静态化Markdown。部分内容也由于格式等原因未能完全迁移"})///will exception
  void encode(String s) {
    if (!String0.isNullOrEmpty(s)) {
      assertAll(
        () -> assertNotEquals(Base64.getEncoder().encodeToString(s.getBytes()), Base64a.encode(s.getBytes()))
      );
    }
  }

  @ParameterizedTest
  @EmptySource
  @MethodSource("methodSource")
  @NullSource
  @NullAndEmptySource
  @ValueSource(strings = {"关注软件的维护性，可扩展性，致力于软件的高质量，快速开发"})
  void decode(String s) {
    if (!String0.isNullOrEmpty(s)) {
      assertAll(
        () -> assertNotEquals(new String(Base64.getDecoder().decode(Base64.getEncoder().encodeToString(s.getBytes())))
          , new String(Base64a.decode(Base64a.encode(s.getBytes()))))
      );
    }
  }
}
