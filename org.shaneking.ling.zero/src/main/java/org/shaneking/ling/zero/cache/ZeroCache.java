package org.shaneking.ling.zero.cache;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.LruMap;
import org.shaneking.ling.zero.util.Map0;

import java.util.List;
import java.util.Map;

public interface ZeroCache {
  String ERR_CODE__CACHE_HIT_ALL = "ROC_CACHES__CACHE_HIT_ALL";
  String ERR_CODE__CACHE_HIT_MISS = "ROC_CACHES__CACHE_HIT_MISS";
  String ERR_CODE__CACHE_HIT_PART = "ROC_CACHES__CACHE_HIT_PART";

  LruMap<String, String> LRU_MAP = new LruMap<>(1023);
  LruMap<String, LruMap<String, String>> LRU_MAP2 = new LruMap<>(1023);

  ThreadLocal<Map<String, List<String>>> DEL_MAP = ThreadLocal.withInitial(Map0::newHashMap);//by transaction, by key list
  ThreadLocal<Map<String, Map<String, List<String>>>> DEL_MAP2 = ThreadLocal.withInitial(Map0::newHashMap);//by transaction, by key, field list

  default Boolean del(@NonNull String key) {
    return del(false, key);
  }

  default Boolean del(boolean withoutTransactional, @NonNull String key) {
    LRU_MAP.remove(key);
    return true;
  }

  default String get(@NonNull String key) {
    return LRU_MAP.get(key);
  }

  default Long hdel(@NonNull String key, @NonNull String... fields) {
    return hdel(false, key, fields);
  }

  default Long hdel(boolean withoutTransactional, @NonNull String key, @NonNull String... fields) {
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
        if (value != null) {
          rtn.add(value);
        }
      }
    }
    return rtn;
  }

  default void hmset(@NonNull String key, @NonNull Map<String, String> map) {
    LRU_MAP2.computeIfAbsent(key, k -> new LruMap<>(1023)).putAll(map);
  }

  default void hset(@NonNull String key, @NonNull String field, @NonNull String value) {
    LRU_MAP2.computeIfAbsent(key, k -> new LruMap<>(1023)).put(field, value);
  }

  default void set(@NonNull String key, @NonNull String value) {
    LRU_MAP.put(key, value);
  }

  default void set(@NonNull String key, int seconds, @NonNull String value) {
    LRU_MAP.put(key, value);
  }

  default boolean inTransactional() {
    return false;
  }

  default String currentTransactionName() {
    return null;
  }
}
