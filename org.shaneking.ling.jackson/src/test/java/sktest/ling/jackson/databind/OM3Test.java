package sktest.ling.jackson.databind;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.zero.lang.Object0;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OM3Test {

  @Test
  void writeValueAsString() {
    assertAll(
      () -> assertEquals(Object0.NULL, OM3.writeValueAsString(null)),
      () -> assertEquals(OM3.OBJECT_ERROR_STRING, OM3.writeValueAsString(Map0.newHashMap())),
      () -> assertEquals("[]", OM3.writeValueAsString(List0.newArrayList()))
    );
  }
}
