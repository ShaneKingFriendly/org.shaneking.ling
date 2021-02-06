package sktest.ling.zero.crypto;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.crypto.Crypto0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;

import static org.junit.jupiter.api.Assertions.*;

class Crypto0Test {

  @Test
  void aesDecrypt() {
    assertAll(
      () -> assertEquals("ILoveYou", Crypto0.aesDecrypt("TcC53mabDUr4WU5NjQZLTw==")),
      () -> assertEquals("ILoveYou", Crypto0.aesDecrypt("TcC53mabDUr4WU5NjQZLTw==", "494c6f7665596f75")),

      () -> assertThrows(ZeroException.class, () -> Crypto0.aesDecrypt("cC53mabDUr4WU5NjQZLTw==", false)),
      () -> assertDoesNotThrow(() -> Crypto0.aesDecrypt("cC53mabDUr4WU5NjQZLTw=="))
    );
  }

  @Test
  void aesEncrypt() {
    assertAll(
      () -> assertEquals("TcC53mabDUr4WU5NjQZLTw==", Crypto0.aesEncrypt("ILoveYou")),
      () -> assertEquals("TcC53mabDUr4WU5NjQZLTw==", Crypto0.aesEncrypt("ILoveYou", "494c6f7665596f75")),

      () -> assertThrows(ZeroException.class, () -> Crypto0.aesEncrypt("ILoveYou", "94c6f7665596f75", false)),
      () -> assertDoesNotThrow(() -> Crypto0.aesEncrypt("ILoveYou", "94c6f7665596f75")),

      () -> assertThrows(NullPointerException.class, () -> Crypto0.aesEncrypt(null, false)),
      () -> assertDoesNotThrow(() -> Crypto0.aesEncrypt(String0.EMPTY))
    );
  }

  @Test
  void genKey() {
    assertAll(
      () -> assertEquals("494c6f7665596f75", Crypto0.salt("ILoveYou")),
      () -> assertNotNull(Crypto0.salt()),
      () -> assertThrows(IllegalArgumentException.class, () -> Crypto0.salt("LengthNotEight"))
    );
  }
}
