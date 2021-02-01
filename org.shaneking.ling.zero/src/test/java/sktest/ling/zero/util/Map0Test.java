package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import static org.junit.jupiter.api.Assertions.*;

class Map0Test {

  @Test
  void newHashMap() {
    assertAll(
      () -> assertLinesMatch(List0.newArrayList(Map0.<String, String>newConcurrentHashMap().keySet()), List0.newArrayList(Map0.<String, String>newConcurrentHashMap().values())),
      () -> assertLinesMatch(List0.newArrayList(Map0.<String, String>newHashMap().keySet()), List0.newArrayList(Map0.<String, String>newHashMap().values())),
      () -> assertLinesMatch(List0.newArrayList(Map0.newHashMap("a", "a").keySet()), List0.newArrayList(Map0.newHashMap("a", "a").values())),
      () -> assertLinesMatch(List0.newArrayList(Map0.newHashMap(List0.newArrayList("a", "b"), List0.newArrayList("a", "b")).keySet())
        , List0.newArrayList(Map0.newHashMap(List0.newArrayList("a", "b"), List0.newArrayList("a", "b")).values())),
      () -> assertEquals(3, Map0.newHashMap(List0.newArrayList("a", "b", "c"), List0.newArrayList("a", "b")).size())
    );
  }
}
