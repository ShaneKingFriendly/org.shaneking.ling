package sktest.ling.mock.type;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.mock.type.Misc;

import static org.junit.jupiter.api.Assertions.*;

class MiscTest {
  @Test
  void aaa() {
    assertAll(
      () -> assertNotNull(new Misc())
    );
  }

  @RepeatedTest(3)
  void id() {
    assertEquals(18, Misc.id().length());
  }
}
