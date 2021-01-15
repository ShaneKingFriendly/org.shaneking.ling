package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.FixedList;
import org.shaneking.ling.zero.util.List0;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FixedListTest {

  @Test
  void add() {
    FixedList<Integer> fixedList = new FixedList<>(3);
    for (int i = 0; i < 10; i++) {
      if (i % 2 == 0) {
        fixedList.add(i);
      } else {
        fixedList.add(1, i);
      }
    }
    assertEquals("[6, 9, 8]", fixedList.toString());
  }

  @Test
  void addAll() {
    FixedList<Integer> fixedList = new FixedList<>();
    for (int i = 0; i < 10; i++) {
      final int fi = i;
      if (i % 2 == 0) {
        fixedList.addAll(List0.nCopies(1000, () -> fi));
      } else {
        fixedList.addAll(1, List0.nCopies(1000, () -> fi));
      }
    }
    assertEquals(FixedList.DEFAULT_SIZE, fixedList.size());
  }
}
