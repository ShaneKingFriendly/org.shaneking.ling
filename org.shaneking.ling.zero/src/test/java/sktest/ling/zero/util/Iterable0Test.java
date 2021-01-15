package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.Iterable0;
import org.shaneking.ling.zero.util.List0;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Iterable0Test {

  @Test
  void elementsEqual() {
    assertTrue(Iterable0.elementsEqual(List0.nCopies(3, () -> 5), List0.nCopies(3, () -> 5)));
    assertFalse(Iterable0.elementsEqual(List0.nCopies(8, () -> 5), List0.nCopies(3, () -> 5)));
  }
}
