package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.Hex0;
import sktest.ling.zero.ZeroTest;

import java.nio.charset.StandardCharsets;

class Hex0Test {

  @Test
  void decodeHex() {
    System.out.println(Hex0.encodeHexString(ZeroTest.SLOGAN.getBytes(StandardCharsets.UTF_8)));//e69c89e6a2a6e6898de69c89e7b2bee5bda9efbc81
  }

  @Test
  void encodeHex() {
    System.out.println(new String(Hex0.decodeHex(Hex0.encodeHexString(ZeroTest.SLOGAN.getBytes(StandardCharsets.UTF_8)).toCharArray()), StandardCharsets.UTF_8));//有梦才有精彩！
  }
}
