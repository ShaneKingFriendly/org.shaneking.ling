package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.MW;
import org.shaneking.ling.zero.util.Map0;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MWTest {

  @Test
  void wrap() {
    String key = null;
    Map<String, String> map = null;
    assertAll(
      () -> assertEquals(3, MW.wrap("1", "1").put("2", "2").putAll(Map0.newHashMap("3", "3")).map().size()),
      () -> assertEquals(0, MW.wrap(key, key).put(null, null).putAll(null).map().size()),

      () -> assertEquals(1, MW.wrap(Map0.newHashMap("1", "1")).put(null, null).putAll(null).map().size()),
      () -> assertEquals(0, MW.wrap(map).put(null, null).putAll(null).map().size())
    );
  }
}
