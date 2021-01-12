package sktest.ling.zero.util.concurrent;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.concurrent.EvictingQueue;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EvictingQueueTest {

  @Test
  void offer4WithoutCapacity() {
    EvictingQueue<Integer> evictingQueue = new EvictingQueue<>();
    for (int i = 0; i < 8; i++) {
      evictingQueue.offer(i);
    }
    assertEquals("[0, 1, 2, 3, 4, 5, 6, 7]", String.valueOf(evictingQueue));
  }

  @Test
  void offer4WithCapacity() {
    EvictingQueue<Integer> evictingQueue = new EvictingQueue<>(3);
    for (int i = 0; i < 8; i++) {
      evictingQueue.offer(i);
    }
    assertEquals("[5, 6, 7]", String.valueOf(evictingQueue));
  }

  @Test
  void addAll() {
    EvictingQueue<Integer> evictingQueue = new EvictingQueue<>(3);
    for (int i = 0; i < 8; i++) {
      evictingQueue.offer(i);
    }
    evictingQueue.addAll(List0.newArrayList(10, 11, 12, 13));
    assertEquals("[11, 12, 13]", String.valueOf(evictingQueue));
  }

  @Test
  void addAll4Bitwise() {
    AtomicLong atomicLong = new AtomicLong(0);
    Supplier<Boolean> supplier = () -> {
      atomicLong.incrementAndGet();
      return true;
    };
    boolean b = false;
    assertEquals(false, b & supplier.get());
    assertEquals(1, atomicLong.get());//executed
    assertEquals(false, b && supplier.get());
    assertEquals(1, atomicLong.get());//unExecuted
  }
}
