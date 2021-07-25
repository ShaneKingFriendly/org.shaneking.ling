package org.shaneking.ling.zero.util.concurrent.locks;

public interface DistributedLockable extends AutoCloseable {
  DistributedLockable lock(String lockKey, int tryTimes, int intervalTime);

  boolean locked();

  void unlock();
}
