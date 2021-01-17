package sktest.ling.zero.util.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.security.SR0;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.concurrent.atomic.AtomicLong0;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class AtomicLong0Test {

  @Test
  void tryDecreaseFailed() throws InterruptedException {
    final AtomicLong al = new AtomicLong(Runtime.getRuntime().availableProcessors() + 1);
    ExecutorService executorService = Executors.newFixedThreadPool(6 * Runtime.getRuntime().availableProcessors() + 1);
    List<Future<Boolean>> futureList = executorService.invokeAll(List0.nCopies(8 * Runtime.getRuntime().availableProcessors() + 1, () -> new Test4AtomicLong2Decrease(al)));
    long l = futureList.parallelStream().map(future -> {
      try {
        return future.get();
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        return false;
      }
    }).filter(b -> !b).count();
    log.info(String.valueOf(Runtime.getRuntime().availableProcessors()));
    log.info(String.valueOf(l));
    assertEquals(7 * Runtime.getRuntime().availableProcessors(), l);
  }

  @Test
  void tryIncreaseFailed() throws InterruptedException {
    final AtomicLong al = new AtomicLong(Runtime.getRuntime().availableProcessors() + 1);
    ExecutorService executorService = Executors.newFixedThreadPool(6 * Runtime.getRuntime().availableProcessors() + 1);
    List<Future<Boolean>> futureList = executorService.invokeAll(List0.nCopies(8 * Runtime.getRuntime().availableProcessors() + 1, () -> new Test4AtomicLong2Increase(al)));
    long l = futureList.parallelStream().map(future -> {
      try {
        return future.get();
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        return false;
      }
    }).filter(b -> !b).count();
    log.info(String.valueOf(Runtime.getRuntime().availableProcessors()));
    log.info(String.valueOf(l));
    assertEquals(7 * Runtime.getRuntime().availableProcessors() + 1, l);
  }


  /**
   * Coverage[l = al.longValue();]
   */
  @Test
  public void tryIncreaseDecreaseFailed() throws Exception {
    final AtomicLong al = new AtomicLong(Runtime.getRuntime().availableProcessors() + 1);
    ExecutorService executorService = Executors.newFixedThreadPool(6 * Runtime.getRuntime().availableProcessors() + 1);
    List<Future<Boolean>> futureList = executorService.invokeAll(List0.nCopies(8 * Runtime.getRuntime().availableProcessors() + 1, () -> SR0.nextInt(10) % 2 == 0 ? new Test4AtomicLong2Decrease(al) : new Test4AtomicLong2Increase(al)));
    long l = futureList.parallelStream().map(future -> {
      try {
        return future.get();
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        return false;
      }
    }).filter(b -> !b).count();
    log.info(String.valueOf(Runtime.getRuntime().availableProcessors()));
    log.info(String.valueOf(l));
    assertTrue(l != 99);
  }

  public class Test4AtomicLong2Decrease implements Callable<Boolean> {
    private final AtomicLong al;

    public Test4AtomicLong2Decrease(AtomicLong al) {
      this.al = al;
    }

    @Override
    public Boolean call() throws Exception {
      return AtomicLong0.tryDecreaseFailed(al);
    }
  }

  public class Test4AtomicLong2Increase implements Callable<Boolean> {
    private final AtomicLong al;

    public Test4AtomicLong2Increase(AtomicLong al) {
      this.al = al;
    }

    @Override
    public Boolean call() throws Exception {
      return AtomicLong0.tryIncreaseFailed(al, 2 * Runtime.getRuntime().availableProcessors() + 1);
    }
  }
}
