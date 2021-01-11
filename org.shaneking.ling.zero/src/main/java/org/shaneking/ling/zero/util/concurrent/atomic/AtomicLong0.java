package org.shaneking.ling.zero.util.concurrent.atomic;

import lombok.NonNull;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLong0 {
  public static boolean tryDecreaseFailed(@NonNull AtomicLong al, long min, long step) {
    long l = al.longValue();
    while (l > min) {
      if (al.compareAndSet(l, l - step)) {
        return true;
      }
      l = al.longValue();
    }
    return false;
  }

  public static boolean tryIncreaseFailed(@NonNull AtomicLong al, long max, long step) {
    long l = al.longValue();
    while (l < max) {
      if (al.compareAndSet(l, l + step)) {
        return true;
      }
      l = al.longValue();
    }
    return false;
  }
}
