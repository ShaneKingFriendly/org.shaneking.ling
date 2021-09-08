package sktest.ling.zero.crypto;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.crypto.MD5a;

import static org.junit.jupiter.api.Assertions.*;

class MD5aTest {

  @Test
  void encrypt() {
    assertAll(
      () -> assertEquals("d41d8cd98f00b204e9800998ecf8427e", MD5a.encrypt("")),
      () -> assertEquals("62accaf23ac9a73c0b28765b7dfaf75a", MD5a.encrypt("ILoveYou")),
      () -> assertNull(MD5a.encrypt(null))
    );
  }
}
