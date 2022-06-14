package org.shaneking.ling.zero.util.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.zero.lang.ZeroException;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

//https://www.cnblogs.com/cxhfuujust/p/10772908.html
/*
|-|throw exception|particular value|block|timeout|
|insert|add(e)|offer(e)|put(e)|offer(e, time, unit)|
|delete|remove()|poll()|take()|poll(time, unit)|
|check|element()|peek()|not available|not available|
 */
@Slf4j
public class EvictingQueue<T> extends LinkedBlockingQueue<T> {
  public static final int DEFAULT_CAPACITY = 1023;

  public EvictingQueue() {
    this(DEFAULT_CAPACITY);
  }

  public EvictingQueue(int capacity) {
    super(capacity);
  }

  @Override
  public boolean add(T t) {
    return offer(t);
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    boolean rtn = true;
    for (T t : c) {
      rtn = rtn && offer(t);
    }
    return rtn;
  }

  @Override
  public boolean offer(T t) {
    if (super.offer(t)) {
      return true;
    } else {
        /*
similarity: there are delete element when exist
difference: if queue is empty
  poll: return null
  remove: throw NoSuchElementException
  take: block util hash element
         */
      super.poll();//super.take();
      return super.offer(t);
    }
  }

  @Override
  public boolean offer(T t, long timeout, TimeUnit unit) {
    return offer(t, timeout, unit, true);
  }

  public boolean offer(T t, long timeout, TimeUnit unit, boolean quietly) {
    try {
      if (super.offer(t, timeout, unit)) {
        return true;
      } else {
        super.poll();
        return super.offer(t, timeout, unit);
      }
    } catch (Exception e) {
      if (quietly) {
        log.error(String.valueOf(t), e);
        return false;
      } else {
        throw new ZeroException(e);
      }
    }
  }

  @Override
  public void put(T t) {
    if (!offer(t)) {
      log.warn(String.valueOf(t));
    }
  }
}
