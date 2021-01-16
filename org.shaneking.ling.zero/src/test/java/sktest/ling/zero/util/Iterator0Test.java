package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.Iterator0;
import org.shaneking.ling.zero.util.List0;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Iterator0Test {
  @Test
  void elementsEqual() {
    assertFalse(Iterator0.elementsEqual(List0.nCopies(8, () -> 5).iterator(), List0.nCopies(3, () -> 5).iterator()));
    assertFalse(Iterator0.elementsEqual(List0.nCopies(3, () -> 5).iterator(), List0.nCopies(3, () -> 8).iterator()));
  }

  @Test
  void get() {
    assertThrows(IndexOutOfBoundsException.class, () -> Iterator0.get(List0.nCopies(3, () -> 5).iterator(), 8));
  }
}
