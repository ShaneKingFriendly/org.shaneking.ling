package sktest.ling.zero.lang.reflect;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.reflect.Array0;

import static org.junit.jupiter.api.Assertions.*;

class Array0Test {

  @Test
  void newArray() {
    assertAll(
      () -> assertNotEquals(new String[3], Array0.newArray(String.class, 3)),//just memory address not equals. expected: [Ljava.lang.String;@77ec78b9<[null, null, null]> but was: [Ljava.lang.String;@1a3869f4<[null, null, null]>
      () -> assertEquals(3, Array0.newArray(String.class, 3).length),
      () -> assertThrows(NullPointerException.class, () -> Array0.newArray(null, 3))
    );
  }
}
