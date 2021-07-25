package org.shaneking.ling.zero.util.concurrent.locks;

public interface Lockable extends AutoCloseable {
  Lockable lock(int tryTimes, int intervalTime);

  boolean locked();

  void unlock();
}
