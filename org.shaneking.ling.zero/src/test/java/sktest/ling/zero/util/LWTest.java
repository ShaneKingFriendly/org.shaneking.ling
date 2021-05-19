package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.LW;
import org.shaneking.ling.zero.util.List0;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class LWTest {

  @Test
  void wrap() {
    String str = null;
    List<String> list = null;
    assertAll(
      () -> assertLinesMatch(List0.newArrayList("1", "2", "3"), LW.wrap("1").add("2").addAll(List0.newArrayList("3")).list()),
      () -> assertLinesMatch(List0.newArrayList("1"), LW.wrap(List0.newArrayList("1")).add(null).addAll(null).list()),
      () -> assertLinesMatch(List0.newArrayList(), LW.wrap(str).add(null).addAll(null).list()),
      () -> assertLinesMatch(List0.newArrayList(), LW.wrap(list).add(null).addAll(null).list())
    );
  }
}
