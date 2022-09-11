package org.shaneking.ling.zero.util;

import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Map0 {
  public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap() {
    return new ConcurrentHashMap<K, V>();
  }

  public static <K, V> HashMap<K, V> newHashMap() {
    return new HashMap<K, V>();
  }

  public static <K, V> HashMap<K, V> newHashMap(K k, V v) {
    HashMap<K, V> rtnMap = Map0.newHashMap();
    rtnMap.put(k, v);
    return rtnMap;
  }

  public static <I> HashMap<I, I> newHashMap(I... ii) {
    HashMap<I, I> rtnMap = Map0.newHashMap();
    for (int i = 1; i < ii.length; i = i + 2) {
      rtnMap.put(ii[i - 1], ii[i]);
    }
    return rtnMap;
  }

  public static <K, V> HashMap<K, V> newHashMap(@NonNull List<K> keys, @NonNull List<V> values) {
    HashMap<K, V> rtn = Map0.newHashMap();
    if (keys.size() > values.size()) {
      for (int i = 0; i < values.size(); i++) {
        rtn.put(keys.get(i), values.get(i));
      }
      for (int i = values.size(); i < keys.size(); i++) {
        rtn.put(keys.get(i), null);
      }
    } else {
      for (int i = 0; i < keys.size(); i++) {
        rtn.put(keys.get(i), values.get(i));
      }
    }
    return rtn;
  }
}
