package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.Iterable0;
import org.shaneking.ling.zero.util.List0;

import static org.junit.jupiter.api.Assertions.*;

class Iterable0Test {

  @Test
  void constructor() {
    assertAll(
      () -> assertNotNull(new Iterable0())
    );
  }

  @Test
  void elementsEqual() {
    assertAll(
      () -> assertThrows(NullPointerException.class, () -> Iterable0.elementsEqual(List0.nCopies(3, () -> 5), null)),
      () -> assertTrue(Iterable0.elementsEqual(List0.nCopies(3, () -> 5), List0.nCopies(3, () -> 5))),
      () -> assertFalse(Iterable0.elementsEqual(List0.nCopies(8, () -> 5), List0.nCopies(3, () -> 5))),
      () -> assertThrows(NullPointerException.class, () -> Iterable0.elementsEqual(null, null))
    );
  }

  @Test
  void get() {
    assertAll(
      () -> assertThrows(NullPointerException.class, () -> Iterable0.get(null, 0))
    );
  }

  @Test
  void toArray() {
    assertAll(
      () -> assertThrows(NullPointerException.class, () -> Iterable0.toArray(null, String.class))
    );
  }
}
