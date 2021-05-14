package sktest.ling.zero.cache;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.cache.ZeroCache;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;
import org.shaneking.ling.zero.util.UUID0;

import static org.junit.jupiter.api.Assertions.*;

class ZeroCacheTest {

  @Test
  void test() {
    ZeroCache cache = new HelloZeroCache();
    cache.hmset("K1", Map0.newHashMap(List0.newArrayList("k11", "k12", "k13"), List0.newArrayList("v11", "v12", "v13")));
    cache.hset("K1", "k14", "v14");
    cache.set("K10", "V10");
    cache.set("K11", "V11");
    assertAll(
      () -> assertTrue(cache.del("K")),
      () -> assertTrue(cache.del("K11")),
      () -> assertNull(cache.get("K")),
      () -> assertEquals("V10", cache.get("K10")),
      () -> assertEquals(0L, cache.hdel("K", "k")),
      () -> assertEquals(0L, cache.hdel("K1", "k")),
      () -> assertEquals(1L, cache.hdel("K1", "k14")),
      () -> assertNull(cache.hget("K", "k")),
      () -> assertNull(cache.hget("K1", "k")),
      () -> assertEquals("v13", cache.hget("K1", "k13")),
      () -> assertLinesMatch(List0.newArrayList(), cache.hmget("K", "k")),
      () -> assertLinesMatch(List0.newArrayList(), cache.hmget("K1", "k")),
      () -> assertLinesMatch(List0.newArrayList("v11"), cache.hmget("K1", "k11")),
      () -> assertLinesMatch(List0.newArrayList("v11", "v12"), cache.hmget("K1", "k11", "k12")),
      () -> assertLinesMatch(List0.newArrayList("v11", "v12"), cache.hmget("K1", "k11", "k12", "k")),
      () -> cache.hmset("K1", Map0.newHashMap("k11", "v11")),
      () -> cache.hmset("K2", Map0.newHashMap("k21", "v21")),
      () -> cache.hmset("K1", Map0.newHashMap("k15", "v15")),
      () -> cache.hset("K2", "k22", "v22"),
      () -> cache.hset("K3", "k31", "v31"),
      () -> cache.set("K11", "V11"),
      () -> cache.set("K12", "V12"),
      () -> cache.set("K13", 1, "V13"),
      () -> cache.set("K12", 1, "V12")
    );

    assertAll(
      () -> assertThrows(NullPointerException.class, () -> cache.del(null)),
      () -> assertThrows(NullPointerException.class, () -> cache.get(null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hdel(null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hdel(null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> cache.hdel(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hdel(null, null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hget(null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> cache.hget(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hget(null, null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hmget(null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hmget(null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> cache.hmget(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hmget(null, null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hmset(null, Map0.newHashMap())),
      () -> assertThrows(NullPointerException.class, () -> cache.hmset(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hmset(null, null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hset(null, null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> cache.hset(null, UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hset(UUID0.cUl33(), null, null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hset(null, UUID0.cUl33(), UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> cache.hset(UUID0.cUl33(), UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> cache.hset(UUID0.cUl33(), null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> cache.hset(null, null, null)),
      () -> assertThrows(NullPointerException.class, () -> cache.set(null, UUID0.cUl33())),
      () -> assertThrows(NullPointerException.class, () -> cache.set(UUID0.cUl33(), null)),
      () -> assertThrows(NullPointerException.class, () -> cache.set(null, null)),
      () -> assertThrows(NullPointerException.class, () -> cache.del(null))
    );
  }
}
