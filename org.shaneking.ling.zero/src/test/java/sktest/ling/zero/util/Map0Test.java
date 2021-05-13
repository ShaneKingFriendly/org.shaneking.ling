package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import java.util.Map;

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

  @Test
  void keySet() {
    Map<String, String> map = Map0.newHashMap(List0.newArrayList(String0.Y, String0.N), List0.newArrayList(String0.Y, String0.N));
    map.keySet().remove(String0.Y);
    assertEquals(1, map.size());
  }

  @Test
  void computeIfAbsent() {
    assertAll(
      () -> assertEquals("v", Map0.newHashMap().computeIfAbsent("k", k -> "v")),
      () -> assertEquals("v2", Map0.newHashMap("k", "v2").computeIfAbsent("k", k -> "v"))
    );
  }
}
