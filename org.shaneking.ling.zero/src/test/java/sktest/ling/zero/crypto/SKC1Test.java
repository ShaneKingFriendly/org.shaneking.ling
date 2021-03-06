package sktest.ling.zero.crypto;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.crypto.SKC1;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;

import static org.junit.jupiter.api.Assertions.*;

class SKC1Test {

  @Test
  void decrypt() {
    assertAll(
      () -> assertEquals("ILoveYou", SKC1.decrypt("TcC53mabDUr4WU5NjQZLTw==")),
      () -> assertEquals("ILoveYou", SKC1.decrypt("TcC53mabDUr4WU5NjQZLTw==", "494c6f7665596f75")),

      () -> assertThrows(ZeroException.class, () -> SKC1.decrypt("cC53mabDUr4WU5NjQZLTw==", false)),
      () -> assertDoesNotThrow(() -> SKC1.decrypt("cC53mabDUr4WU5NjQZLTw=="))
    );
  }

  @Test
  void encrypt() {
    assertAll(
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
