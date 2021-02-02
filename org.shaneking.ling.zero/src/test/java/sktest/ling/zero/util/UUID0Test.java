package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.UUID0;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UUID0Test {

  @Test
  void cMl33() {
    assertEquals(33, UUID0.cMl33().length());
  }

  @Test
  void cUl33() {
    assertEquals(33, UUID0.cUl33().length());
    for (int i = 0; i < 10; i++) {
      System.out.println(UUID0.cUl33());
    }
  }

  @Test
  void dMl37() {
    assertEquals(37, UUID0.dMl37().length());
  }

  @Test
  void dUl37() {
    assertEquals(37, UUID0.dUl37().length());
  }

  @Test
  void l19() {
    assertEquals(19, UUID0.l19().length());
  }
}
