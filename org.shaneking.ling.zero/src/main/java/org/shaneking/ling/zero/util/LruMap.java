package org.shaneking.ling.zero.util;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.ZeroException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class LruMap<K, V> extends LinkedHashMap<K, V> {
  private final int maxSize;

  public LruMap(int initialCapacity, float loadFactor, boolean accessOrder, int maxSize) {
    super(initialCapacity, loadFactor, accessOrder);
    this.maxSize = maxSize;
  }

  public LruMap(int maxSize) {
    this(16, maxSize);
  }

  public LruMap(int tableSize, int maxSize) {
    this(tableSize, 0.75f, true, maxSize);
  }

  public V get(K key, @NonNull Callable<V> callable) {
    V v = get(key);
    if (v == null) {
      try {
        v = callable.call();
      } catch (Exception e) {
        throw new ZeroException(e);
      }
      put(key, v);
    }
    return v;
  }

  @Override
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    return size() > maxSize;
  }
}
