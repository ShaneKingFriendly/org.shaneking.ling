package sktest.ling.zero.crypto;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.crypto.Cipher0;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Cipher0Test {
  @Test
  void test() {
    assertNotEquals(Cipher0.AES_CBC_PKCS5Padding, Cipher0.AES_ECB_PKCS5Padding);
  }
}
