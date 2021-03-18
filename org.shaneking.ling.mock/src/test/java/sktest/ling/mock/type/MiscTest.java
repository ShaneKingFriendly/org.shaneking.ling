package sktest.ling.mock.type;

import org.junit.jupiter.api.RepeatedTest;
import org.shaneking.ling.mock.type.Misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MiscTest {

  @RepeatedTest(3)
  void id() {
    assertEquals(18, Misc.id().length());
  }
}
