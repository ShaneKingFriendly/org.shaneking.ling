package org.shaneking.ling.zero.util;

import java.util.Map;

public class MW<K, V> {
  private final Map<K, V> map = Map0.newHashMap();

  public MW<K, V> put(K k, V v) {
    if (k != null) {
      map.put(k, v);
    }
    return this;
  }

  public MW<K, V> putAll(Map<K, V> m) {
    if (m != null) {
      map.putAll(m);
    }
    return this;
  }

  public Map<K, V> map() {
    return map;
  }

  public static <K, V> MW<K, V> wrap(K k, V v) {
    MW<K, V> mw = new MW<>();
    if (k != null) {
      mw.map.put(k, v);
    }
    return mw;
  }

  public static <K, V> MW<K, V> wrap(Map<K, V> m) {
    MW<K, V> mw = new MW<>();
    if (m != null) {
      mw.map.putAll(m);
    }
    return mw;
  }
}
