package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;
import org.shaneking.ling.zero.util.LruMap;
import org.shaneking.ling.zero.util.UUID0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LruMapTest {

  @Test
  void get4exception() {
    assertThrows(ZeroException.class, () -> new LruMap<String, String>(3).get(String0.EMPTY, () -> {
      throw new ZeroException();
    }));
  }

  @Test
  void get4lru() {
    LruMap<Integer, Integer> lruMap = new LruMap<>(3);
    for (int i = 0; i < 8; i++) {
      lruMap.put(i, i);
      if (i >= 3) {
        lruMap.get(4);
      }
    }
    assertEquals(4, lruMap.get(4));
  }

  @Test
  void get4size() {
    LruMap<String, String> lruMap = new LruMap<>(3);
    for (int i = 0; i < 8; i++) {
      lruMap.put(UUID0.cUl33(), UUID0.cUl33());
      if (i >= 3) {
        assertEquals(3, lruMap.size());
      }
    }
  }
}
