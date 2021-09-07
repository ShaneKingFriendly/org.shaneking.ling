package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class List0Test {

  @Test
  void constructor() {
    assertAll(
      () -> assertNotNull(new List0())
    );
  }

  @Test
  void newArrayList() {
    String str = null;
    String[] strings = new String[]{String0.Y, String0.N};
    String[] sa = null;
    Iterable iterable = null;
    Iterator iterator = null;
    assertAll(
      () -> assertLinesMatch(List0.newArrayList(), List0.newArrayList(new String[]{null})),
      () -> assertLinesMatch(List0.newArrayList(String0.Y, String0.N), List0.newArrayList(String0.Y, String0.N)),
      () -> assertLinesMatch(List0.newArrayList(String0.Y, String0.N), List0.newArrayList(String0.Y, String0.N)),
      () -> assertLinesMatch(List0.newArrayList(String0.Y, String0.N), List0.newArrayList(strings)),
      () -> assertLinesMatch(List0.newArrayList(), List0.newArrayList(sa)),
      () -> assertLinesMatch(List0.newArrayList(), List0.newArrayList(str)),

      () -> assertThrows(NullPointerException.class, () -> List0.newArrayList(iterable)),
      () -> assertLinesMatch(List0.newArrayList(String0.Y, String0.N), List0.newArrayList(List0.newArrayList(String0.Y, String0.N))),

      () -> assertThrows(NullPointerException.class, () -> List0.newArrayList(iterator))
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
