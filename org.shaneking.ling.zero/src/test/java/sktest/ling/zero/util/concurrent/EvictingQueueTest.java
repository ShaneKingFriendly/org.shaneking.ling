package sktest.ling.zero.util.concurrent;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.concurrent.EvictingQueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class EvictingQueueTest {

  @Test
  void addAll() {
    assertAll(
      () -> {
        EvictingQueue<Integer> evictingQueue = new EvictingQueue<>(3);
        for (int i = 0; i < 8; i++) {
          evictingQueue.put(i);
        }
        evictingQueue.addAll(List0.newArrayList(10, 11, 12, 13));
        assertEquals("[11, 12, 13]", String.valueOf(evictingQueue));
      },
      () -> {//addAll4bitwise
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
    );
  }

  @Test
  void offer() {
    assertAll(
      () -> {//offer4timeout
        EvictingQueue<Integer> evictingQueue = new EvictingQueue<>(3);
        for (int i = 0; i < 8; i++) {
          evictingQueue.offer(i, 1000, TimeUnit.MILLISECONDS);
        }
        assertEquals("[5, 6, 7]", String.valueOf(evictingQueue));
      },
      () -> {//offer4timeout4exception
        EvictingQueue<Integer> evictingQueue = new EvictingQueue<>(3);
        for (int i = 0; i < 8; i++) {
          final int fi = i;
          assertDoesNotThrow(() -> evictingQueue.offer(fi, 1, TimeUnit.NANOSECONDS));
        }
//    assertThrows(ZeroException.class, () -> evictingQueue.offer(9, 1, TimeUnit.NANOSECONDS, false));
        assertEquals("[5, 6, 7]", String.valueOf(evictingQueue));
      },
      () -> {//offer4withCapacity
        EvictingQueue<Integer> evictingQueue = new EvictingQueue<>(3);
        for (int i = 0; i < 8; i++) {
          evictingQueue.offer(i);
        }
        assertEquals("[5, 6, 7]", String.valueOf(evictingQueue));
      },
      () -> {//offer4withoutCapacity
        EvictingQueue<Integer> evictingQueue = new EvictingQueue<>();
        for (int i = 0; i < 8; i++) {
          evictingQueue.add(i);
        }
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7]", String.valueOf(evictingQueue));
      }
    );
  }
}
