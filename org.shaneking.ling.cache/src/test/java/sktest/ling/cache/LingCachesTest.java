package sktest.ling.cache;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.cache.LingCaches;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;
import org.shaneking.ling.zero.util.UUID0;

import static org.junit.jupiter.api.Assertions.*;

class LingCachesTest extends SKUnit {

  @Test
  void test() {
    LingCaches lingCaches = new HelloLingCaches();
    lingCaches.hmset("K1", Map0.newHashMap(List0.newArrayList("k11", "k12", "k13"), List0.newArrayList("v11", "v12", "v13")));
    lingCaches.hset("K1", "k14", "v14");
    lingCaches.set("K10", "V10");
    lingCaches.set("K11", "V11");
    assertAll(
      () -> assertTrue(lingCaches.del("K")),
      () -> assertTrue(lingCaches.del("K11")),
      () -> assertNull(lingCaches.get("K")),
      () -> assertEquals("V10", lingCaches.get("K10")),
      () -> assertEquals(0L, lingCaches.hdel("K", "k")),
      () -> assertEquals(0L, lingCaches.hdel("K1", "k")),
      () -> assertEquals(1L, lingCaches.hdel("K1", "k14")),
      () -> assertNull(lingCaches.hget("K", "k")),
      () -> assertNull(lingCaches.hget("K1", "k")),
      () -> assertEquals("v13", lingCaches.hget("K1", "k13")),
      () -> assertLinesMatch(List0.newArrayList(), lingCaches.hmget("K", "k")),
      () -> assertLinesMatch(List0.newArrayList(), lingCaches.hmget("K1", "k")),
      () -> assertLinesMatch(List0.newArrayList("v11"), lingCaches.hmget("K1", "k11")),
      () -> assertLinesMatch(List0.newArrayList("v11", "v12"), lingCaches.hmget("K1", "k11", "k12")),
      () -> assertLinesMatch(List0.newArrayList("v11", "v12"), lingCaches.hmget("K1", "k11", "k12", "k")),
      () -> lingCaches.hmset("K1", Map0.newHashMap("k11", "v11")),
      () -> lingCaches.hmset("K2", Map0.newHashMap("k21", "v21")),
      () -> lingCaches.hmset("K1", Map0.newHashMap("k15", "v15")),
      () -> lingCaches.hset("K2", "k22", "v22"),
      () -> lingCaches.hset("K3", "k31", "v31"),
      () -> lingCaches.set("K11", "V11"),
      () -> lingCaches.set("K12", "V12"),
      () -> lingCaches.set("K13", 1, "V13"),
      () -> lingCaches.set("K12", 1, "V12")
    );

    assertAll(
      () -> assertThrows(NullPointerException.class, () -> lingCaches.del(null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.get(null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hdel(null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hdel(null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hdel(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hdel(null, null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hget(null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hget(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hget(null, null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hmget(null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hmget(null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hmget(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hmget(null, null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hmset(null, Map0.newHashMap())),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hmset(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hmset(null, null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hset(null, null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hset(null, UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hset(UUID0.cUl33(), null, null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hset(null, UUID0.cUl33(), UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hset(UUID0.cUl33(), UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hset(UUID0.cUl33(), null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.hset(null, null, null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.set(null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.set(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.set(null, null)),
      () -> assertThrows(NullPointerException.class, () -> lingCaches.del(null))
    );
  }
}
