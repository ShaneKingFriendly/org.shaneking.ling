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
  void test() {
    assertAll(
      () -> assertEquals("{\"ext\":{\"userNo\":\"tstUserNo\"},\"obj\":\"1612353237501_DcNd45KtJXPmSpz2xRB\",\"rtn\":1}", SKC1.decrypt("UpYigwpx9zJXGQraTGg7LinYngjNd4HQlutN8zK/i8dcfoFMyiXjBil7DH5l61v0Lo/SwEEK5xTVyuMTFudTeQdUUmAVUiuVk4vrOn8NgMZKPuUFmf6zENVMCmznwiKo")),
      () -> assertEquals("UpYigwpx9zJXGQraTGg7LinYngjNd4HQlutN8zK/i8cuDbGx3iltSrCg3pUiEt/onwQC1L9bcIgn36b6P7anotLu7HEVhz6w7ibxrGL9x678BH4Bmj76KKSYXNCogdvM", SKC1.encrypt("{\"ext\":{\"userNo\":\"tstUserNo\"},\"obj\":{\"id\":\"1612353237501_DcNd45KtJXPmSpz2xRB\"},\"rtn\":1}")),

      () -> assertEquals("{\"ext\":{\"userNo\":\"tstUserNo\"},\"obj\":{\"whereConditions\":{\"tenantId\":{\"op\":\"=\",\"cs\":\"1612262610215_LoHqeZBGrVYm3MlYmpH\"}},\"id\":\"1612353237501_DcNd45KtJXPmSpz2xRB\",\"tenantId\":\"1612262610215_LoHqeZBGrVYm3MlYmpH\"},\"rtn\":1}", SKC1.decrypt("UpYigwpx9zJXGQraTGg7LinYngjNd4HQlutN8zK/i8ekWhVfldssP4loE+0WhISX/hCnovJeStZRr7LhJxoHu+L4wdNQykpa/D3O/LQiGcVJvIHeAhMGNMBXvOO5LHIZTdIvZABx5C1FjwAgvhHreTKBMqbMuiI3u20F/VM5t/M9XFLbPfahzu3vnRKQt12bd5uGEzJO+bvCckGBcBq9Ztiz+efV4rI8hY437LEBtxg0fY5T0mkOWzfnUXTd0PuYE//w33/xDiz2+Uyc0HQKdr3Z7tJMS2CHu520m7K3Ksk=")),
      () -> assertEquals("UpYigwpx9zJXGQraTGg7LinYngjNd4HQlutN8zK/i8ekWhVfldssP4loE+0WhISX/hCnovJeStZRr7LhJxoHu+L4wdNQykpa/D3O/LQiGcVJvIHeAhMGNMBXvOO5LHIZTdIvZABx5C1FjwAgvhHreTKBMqbMuiI3u20F/VM5t/M9XFLbPfahzu3vnRKQt12bd5uGEzJO+bvCckGBcBq9Ztiz+efV4rI8hY437LEBtxg0fY5T0mkOWzfnUXTd0PuYE//w33/xDiz2+Uyc0HQKdr3Z7tJMS2CHu520m7K3Ksk=", SKC1.encrypt("{\"ext\":{\"userNo\":\"tstUserNo\"},\"obj\":{\"whereConditions\":{\"tenantId\":{\"op\":\"=\",\"cs\":\"1612262610215_LoHqeZBGrVYm3MlYmpH\"}},\"id\":\"1612353237501_DcNd45KtJXPmSpz2xRB\",\"tenantId\":\"1612262610215_LoHqeZBGrVYm3MlYmpH\"},\"rtn\":1}"))
    );
  }

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
      () -> assertEquals("有梦才有精彩！", SKC1.decrypt("Ziaj84fYKpNiZ1wD1O7vPNrb1dYVC2H65UlR8wlIvCw=")),//D:U,S:U
      () -> assertEquals(new String("有梦才有精彩！".getBytes(StandardCharsets.UTF_8), "GBK"), SKC1.decrypt("bzo0CkFz86geJhgbyunPGrzq2Bc07XbhBpjLd8QTPAePVWxLMAHYeU0q5KuD1SP/")),//D:U,S:G
      () -> assertNotEquals(new String("有梦才有精彩！".getBytes(StandardCharsets.UTF_8), "GBK"), SKC1.decrypt("Ziaj84fYKpNiZ1wD1O7vPI0qsFSFHwqOuDqcwzRPt34=", SKC1.DEFAULT_SALT, Charset.forName("GBK"), false)),//D:G,S:G
      () -> assertEquals("有梦才有精彩！", SKC1.decrypt("fu3MmngB1XWzzxxph9AQtQ==", SKC1.DEFAULT_SALT, Charset.forName("GBK"), false)),//D:G,S:U

      () -> assertEquals("ILoveYou", SKC1.decrypt("TcC53mabDUr4WU5NjQZLTw==")),
      () -> assertEquals("ILoveYou", SKC1.decrypt("TcC53mabDUr4WU5NjQZLTw==", "494c6f7665596f75")),

      () -> assertThrows(ZeroException.class, () -> SKC1.decrypt("cC53mabDUr4WU5NjQZLTw==", false)),
      () -> assertDoesNotThrow(() -> SKC1.decrypt("cC53mabDUr4WU5NjQZLTw=="))
    );
  }

  @Test
  void encrypt() {
    assertAll(
      () -> assertEquals("Ziaj84fYKpNiZ1wD1O7vPNrb1dYVC2H65UlR8wlIvCw=", SKC1.encrypt("有梦才有精彩！")),//S:U,E:U
      () -> assertEquals("bzo0CkFz86geJhgbyunPGrzq2Bc07XbhBpjLd8QTPAePVWxLMAHYeU0q5KuD1SP/", SKC1.encrypt(new String("有梦才有精彩！".getBytes(StandardCharsets.UTF_8), "GBK"))),//S:G,E:U
      () -> assertEquals("Ziaj84fYKpNiZ1wD1O7vPI0qsFSFHwqOuDqcwzRPt34=", SKC1.encrypt(new String("有梦才有精彩！".getBytes(StandardCharsets.UTF_8), "GBK"), SKC1.DEFAULT_SALT, Charset.forName("GBK"), false)),//S:G,E:G
      () -> assertEquals("fu3MmngB1XWzzxxph9AQtQ==", SKC1.encrypt("有梦才有精彩！", SKC1.DEFAULT_SALT, Charset.forName("GBK"), false)),//S:U,E:G

      () -> assertEquals("TcC53mabDUr4WU5NjQZLTw==", SKC1.encrypt("ILoveYou")),
      () -> assertEquals("TcC53mabDUr4WU5NjQZLTw==", SKC1.encrypt("ILoveYou", "494c6f7665596f75")),

      () -> assertThrows(ZeroException.class, () -> SKC1.encrypt("ILoveYou", "94c6f7665596f75", false)),
      () -> assertDoesNotThrow(() -> SKC1.encrypt("ILoveYou", "94c6f7665596f75")),

      () -> assertThrows(NullPointerException.class, () -> SKC1.encrypt(null, false)),
      () -> assertDoesNotThrow(() -> SKC1.encrypt(String0.EMPTY))
    );
  }

  @Test
  void genKey() {
    assertAll(
      () -> assertEquals("494c6f7665596f75", SKC1.salt("ILoveYou")),
      () -> assertNotNull(SKC1.salt()),
      () -> assertThrows(IllegalArgumentException.class, () -> SKC1.salt("LengthNotEight"))
    );

  }
}
