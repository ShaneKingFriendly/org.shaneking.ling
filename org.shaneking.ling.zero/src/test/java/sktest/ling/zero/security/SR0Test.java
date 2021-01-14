package sktest.ling.zero.security;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.security.SR0;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SR0Test {

  @Test
  void nextInt() {
    assertTrue(SR0.nextInt(9) < 9);
  }
}
