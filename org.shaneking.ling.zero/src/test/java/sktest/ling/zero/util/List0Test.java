package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class List0Test {

  @Test
  void newArrayList() {
    assertLinesMatch(List0.newArrayList(String0.Y, String0.N), List0.newArrayList(List0.newArrayList(String0.Y, String0.N)));
  }

  @Test
  void reverse() {
    assertLinesMatch(List0.newArrayList(String0.N, String0.Y), List0.reverse(List0.newArrayList(String0.Y, String0.N)));
  }
}
