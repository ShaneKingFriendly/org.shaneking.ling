package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class List0Test {

  @Test
  void newArrayList() {
    String[] strings = new String[]{String0.Y, String0.N};
    assertAll(
      () -> assertLinesMatch(List0.newArrayList(String0.Y, String0.N), List0.newArrayList(List0.newArrayList(String0.Y, String0.N))),
      () -> assertLinesMatch(List0.newArrayList(String0.Y, String0.N), List0.newArrayList(String0.Y, String0.N)),
      () -> assertLinesMatch(List0.newArrayList(String0.Y, String0.N), List0.newArrayList(String0.Y, String0.N)),

      () -> assertLinesMatch(List0.newArrayList(String0.Y, String0.N), List0.newArrayList(strings))
    );
  }

  @Test
  void reverse() {
    assertLinesMatch(List0.newArrayList(String0.N, String0.Y), List0.reverse(List0.newArrayList(String0.Y, String0.N)));
  }

  @Test
  void retainAll() {
    List<String> a = List0.newArrayList(String0.Y, String0.N, String0.T);
    List<String> b = List0.newArrayList(String0.Y, String0.N, String0.F);
    a.retainAll(b);
    assertLinesMatch(List0.newArrayList(String0.Y, String0.N), a);
  }
}
