package sktest.ling.cache;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.cache.StringCaches;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;
import org.shaneking.ling.zero.util.UUID0;

import static org.junit.jupiter.api.Assertions.*;

class StringCachesTest extends SKUnit {

  @Test
  void test() {
    StringCaches stringCaches = new HelloStringCaches();
    stringCaches.hmset("K1", Map0.newHashMap(List0.newArrayList("k11", "k12", "k13"), List0.newArrayList("v11", "v12", "v13")));
    stringCaches.hset("K1", "k14", "v14");
    stringCaches.set("K10", "V10");
    stringCaches.set("K11", "V11");
    assertAll(
      () -> assertTrue(stringCaches.del("K")),
      () -> assertTrue(stringCaches.del("K11")),
      () -> assertNull(stringCaches.get("K")),
      () -> assertEquals("V10", stringCaches.get("K10")),
      () -> assertEquals(0L, stringCaches.hdel("K", "k")),
      () -> assertEquals(0L, stringCaches.hdel("K1", "k")),
      () -> assertEquals(1L, stringCaches.hdel("K1", "k14")),
      () -> assertNull(stringCaches.hget("K", "k")),
      () -> assertNull(stringCaches.hget("K1", "k")),
      () -> assertEquals("v13", stringCaches.hget("K1", "k13")),
      () -> assertLinesMatch(List0.newArrayList(), stringCaches.hmget("K", "k")),
      () -> assertLinesMatch(List0.newArrayList(), stringCaches.hmget("K1", "k")),
      () -> assertLinesMatch(List0.newArrayList("v11"), stringCaches.hmget("K1", "k11")),
      () -> assertLinesMatch(List0.newArrayList("v11", "v12"), stringCaches.hmget("K1", "k11", "k12")),
      () -> assertLinesMatch(List0.newArrayList("v11", "v12"), stringCaches.hmget("K1", "k11", "k12", "k")),
      () -> stringCaches.hmset("K1", Map0.newHashMap("k11", "v11")),
      () -> stringCaches.hmset("K2", Map0.newHashMap("k21", "v21")),
      () -> stringCaches.hmset("K1", Map0.newHashMap("k15", "v15")),
      () -> stringCaches.hset("K2", "k22", "v22"),
      () -> stringCaches.hset("K3", "k31", "v31"),
      () -> stringCaches.set("K11", "V11"),
      () -> stringCaches.set("K12", "V12"),
      () -> stringCaches.set("K13", 1, "V13"),
      () -> stringCaches.set("K12", 1, "V12")
    );

    assertAll(
      () -> assertThrows(NullPointerException.class, () -> stringCaches.del(null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.get(null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hdel(null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hdel(null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hdel(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hdel(null, null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hget(null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hget(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hget(null, null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hmget(null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hmget(null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hmget(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hmget(null, null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hmset(null, Map0.newHashMap())),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hmset(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hmset(null, null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hset(null, null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hset(null, UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hset(UUID0.cUl33(), null, null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hset(null, UUID0.cUl33(), UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hset(UUID0.cUl33(), UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hset(UUID0.cUl33(), null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.hset(null, null, null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.set(null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.set(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.set(null, null)),
      () -> assertThrows(NullPointerException.class, () -> stringCaches.del(null))
    );
  }
}
