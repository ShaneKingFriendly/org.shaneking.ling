package org.shaneking.ling.cache;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.LruMap;

import java.util.List;
import java.util.Map;

public interface StringCaches {
  String ERR_CODE__CACHE_HIT_ALL = "STRING_CACHES__CACHE_HIT_ALL";
  String ERR_CODE__CACHE_HIT_MISS = "STRING_CACHES__CACHE_HIT_MISS";
  String ERR_CODE__CACHE_HIT_PART = "STRING_CACHES__CACHE_HIT_PART";

  LruMap<String, String> LRU_MAP = new LruMap<>(1023);
  LruMap<String, LruMap<String, String>> LRU_MAP2 = new LruMap<>(1023);

  default Boolean del(@NonNull String key) {
    LRU_MAP.remove(key);
    return true;
  }

  default String get(@NonNull String key) {
    return LRU_MAP.get(key);
  }

  default Long hdel(@NonNull String key, @NonNull String... fields) {
    long rtn = 0L;
    LruMap<String, String> map = LRU_MAP2.get(key);
    if (map != null) {
      for (String field : fields) {
        rtn += String0.isNullOrEmpty(map.remove(field)) ? 0 : 1;
      }
    }
    return rtn;
  }

  default String hget(@NonNull String key, @NonNull String field) {
    String rtn = null;
    LruMap<String, String> map = LRU_MAP2.get(key);
    if (map != null) {
      rtn = map.get(field);
    }
    return rtn;
  }

  @NonNull
  default List<String> hmget(@NonNull String key, @NonNull String... fields) {
    List<String> rtn = List0.newArrayList();
    LruMap<String, String> map = LRU_MAP2.get(key);
    if (map != null) {
      for (String field : fields) {
        String value = map.get(field);
        {
          if (value != null) {
            rtn.add(value);
          }
        }
      }
    }
    return rtn;
  }

  default void hmset(@NonNull String key, @NonNull Map<String, String> map) {
    LruMap<String, String> lruMap = LRU_MAP2.get(key);
    if (lruMap == null) {
      lruMap = new LruMap<>(1023);
      LRU_MAP2.put(key, lruMap);
    }
    lruMap.putAll(map);
  }

  default void hset(@NonNull String key, @NonNull String field, @NonNull String value) {
    LruMap<String, String> lruMap = LRU_MAP2.get(key);
    if (lruMap == null) {
      lruMap = new LruMap<>(1023);
      LRU_MAP2.put(key, lruMap);
    }
    lruMap.put(field, value);
  }

  default void set(@NonNull String key, @NonNull String value) {
    LRU_MAP.put(key, value);
  }

  void set(@NonNull String key, int seconds, @NonNull String value);
}
