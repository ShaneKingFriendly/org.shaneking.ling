package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.Iterator0;
import org.shaneking.ling.zero.util.List0;

import static org.junit.jupiter.api.Assertions.*;

class Iterator0Test {

  @Test
  void constructor() {
    assertAll(
      () -> assertNotNull(new Iterator0())
    );
  }

  @Test
  void advance() {
    assertAll(
      () -> assertThrows(NullPointerException.class, () -> Iterator0.advance(null, 0))
    );
  }

  @Test
  void elementsEqual() {
    assertAll(
      () -> assertThrows(NullPointerException.class, () -> Iterator0.elementsEqual(null, null)),
      () -> assertFalse(Iterator0.elementsEqual(List0.nCopies(3, () -> 5).iterator(), List0.nCopies(3, () -> 8).iterator())),
      () -> assertFalse(Iterator0.elementsEqual(List0.nCopies(8, () -> 5).iterator(), List0.nCopies(3, () -> 5).iterator())),
      () -> assertThrows(NullPointerException.class, () -> Iterator0.elementsEqual(List0.newArrayList().iterator(), null))
    );
  }

  @Test
  void get() {
    assertThrows(NullPointerException.class, () -> Iterator0.get(null, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> Iterator0.get(List0.nCopies(3, () -> 5).iterator(), 8));
  }
}
