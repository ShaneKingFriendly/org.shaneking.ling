package sktest.ling.zero.crypto;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.crypto.SKC1;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class SKC1Test {

  @Test
  void decrypt() {
    try {
      System.out.println("有梦才有精彩！");//有梦才有精彩！
      System.out.println(new String("有梦才有精彩！".getBytes(StandardCharsets.UTF_8), "GBK"));//鏈夋ⅵ鎵嶆湁绮惧僵锛�
      System.out.println(new String(new String("有梦才有精彩！".getBytes(StandardCharsets.UTF_8), "GBK").getBytes("GBK"), StandardCharsets.UTF_8));//有梦才有精彩�?
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    assertAll(
      () -> assertEquals(new String("有梦才有精彩！".getBytes(StandardCharsets.UTF_8), "GBK"), SKC1.decrypt("bzo0CkFz86geJhgbyunPGrzq2Bc07XbhBpjLd8QTPAePVWxLMAHYeU0q5KuD1SP/")),//D:U,S:G
      () -> assertEquals("cC53mabDUr4WU5NjQZLTw==", SKC1.decrypt("cC53mabDUr4WU5NjQZLTw==")),
      () -> assertEquals(String0.EMPTY, SKC1.decrypt("Sj7lBZn+sxDVTAps58IiqA==")),
      () -> assertEquals("ILoveYou", SKC1.decrypt("TcC53mabDUr4WU5NjQZLTw==")),
      () -> assertEquals("有梦才有精彩！", SKC1.decrypt("Ziaj84fYKpNiZ1wD1O7vPNrb1dYVC2H65UlR8wlIvCw=")),//D:U,S:U

      () -> assertThrows(ZeroException.class, () -> SKC1.decrypt("cC53mabDUr4WU5NjQZLTw==", false)),

      () -> assertEquals("ILoveYou", SKC1.decrypt("TcC53mabDUr4WU5NjQZLTw==", "494c6f7665596f75")),

      () -> assertEquals("ILoveYou", SKC1.decrypt("TcC53mabDUr4WU5NjQZLTw==", "494c6f7665596f75", true)),

      () -> assertEquals("有梦才有精彩！", SKC1.decrypt("fu3MmngB1XWzzxxph9AQtQ==", SKC1.DEFAULT_SALT, Charset.forName("GBK"), false)),//D:G,S:U
      () -> assertNotEquals(new String("有梦才有精彩！".getBytes(StandardCharsets.UTF_8), "GBK"), SKC1.decrypt("Ziaj84fYKpNiZ1wD1O7vPI0qsFSFHwqOuDqcwzRPt34=", SKC1.DEFAULT_SALT, Charset.forName("GBK"), false))//D:G,S:G
    );
  }

  @Test
  void encrypt() {
    assertAll(
      () -> assertEquals("Ziaj84fYKpNiZ1wD1O7vPNrb1dYVC2H65UlR8wlIvCw=", SKC1.encrypt("有梦才有精彩！")),//S:U,E:U
      () -> assertEquals("TcC53mabDUr4WU5NjQZLTw==", SKC1.encrypt("ILoveYou")),
      () -> assertEquals("Sj7lBZn+sxDVTAps58IiqA==", SKC1.encrypt(String0.EMPTY)),
      () -> assertEquals("bzo0CkFz86geJhgbyunPGrzq2Bc07XbhBpjLd8QTPAePVWxLMAHYeU0q5KuD1SP/", SKC1.encrypt(new String("有梦才有精彩！".getBytes(StandardCharsets.UTF_8), "GBK"))),//S:G,E:U

      () -> assertThrows(NullPointerException.class, () -> SKC1.encrypt(null, false)),

      () -> assertEquals("TcC53mabDUr4WU5NjQZLTw==", SKC1.encrypt("ILoveYou", "494c6f7665596f75")),
      () -> assertEquals("ILoveYou", SKC1.encrypt("ILoveYou", "94c6f7665596f75")),

      () -> assertThrows(ZeroException.class, () -> SKC1.encrypt("ILoveYou", "94c6f7665596f75", false)),
      () -> assertEquals("ILoveYou", SKC1.encrypt("ILoveYou", "94c6f7665596f75", true)),

      () -> assertEquals("fu3MmngB1XWzzxxph9AQtQ==", SKC1.encrypt("有梦才有精彩！", SKC1.DEFAULT_SALT, Charset.forName("GBK"), false)),//S:U,E:G
      () -> assertEquals("Ziaj84fYKpNiZ1wD1O7vPI0qsFSFHwqOuDqcwzRPt34=", SKC1.encrypt(new String("有梦才有精彩！".getBytes(StandardCharsets.UTF_8), "GBK"), SKC1.DEFAULT_SALT, Charset.forName("GBK"), false))//S:G,E:G
    );
  }

  @Test
  void salt() {
    assertAll(
      () -> assertEquals(16, SKC1.salt().length()),

      () -> assertEquals("494c6f7665596f75", SKC1.salt("ILoveYou")),
      () -> assertThrows(IllegalArgumentException.class, () -> SKC1.salt("LengthNotEight"))
    );

  }
}
