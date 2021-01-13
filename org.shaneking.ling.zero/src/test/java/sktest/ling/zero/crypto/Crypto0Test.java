package sktest.ling.zero.crypto;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.crypto.Crypto0;
import org.shaneking.ling.zero.lang.String0;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import static org.junit.jupiter.api.Assertions.*;

class Crypto0Test {

  @Test
  void aesDecrypt() throws BadPaddingException, IllegalBlockSizeException {
    assertEquals("ILoveYou", Crypto0.aesDecrypt("TcC53mabDUr4WU5NjQZLTw=="));
  }

  @Test
  void aesEncrypt() throws BadPaddingException, IllegalBlockSizeException {
    assertEquals("TcC53mabDUr4WU5NjQZLTw==", Crypto0.aesEncrypt("ILoveYou"));
    assertAll(
      () -> assertThrows(NullPointerException.class, () -> Crypto0.aesEncrypt(null)),
      () -> assertDoesNotThrow(() -> Crypto0.aesEncrypt(String0.EMPTY))
    );
  }

  @Test
  void genKey() {
    assertEquals("494c6f7665596f75", Crypto0.genKey("ILoveYou"));
    assertNotNull(Crypto0.genKey());
    assertThrows(IllegalArgumentException.class, () -> Crypto0.genKey("LengthNotEight"));
  }
}
