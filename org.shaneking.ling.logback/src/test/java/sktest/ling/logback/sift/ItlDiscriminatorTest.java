package sktest.ling.logback.sift;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.logback.sift.ItlDiscriminator;
import org.shaneking.ling.zero.util.List0;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Slf4j
class ItlDiscriminatorTest {

  @Test
  void getDiscriminatingValue() throws ExecutionException, InterruptedException {
    log.info("BEGIN");

    ItlDiscriminator.itl().put("logItlFn", "ForkJoinPool");
    IntStream.range(1, 100).boxed().parallel().forEach(i -> {
      log.info(String.valueOf(i));
    });

    ItlDiscriminator.itl().put("logItlFn", "ThreadPool");
    ExecutorService es = Executors.newFixedThreadPool(3);
    List<Future<?>> list = List0.newArrayList();
    for (int i = 0; i < 100; i++) {
      int fi = i;
      list.add(es.submit(() -> log.info(String.valueOf(fi))));
    }
    for (Future<?> f : list) {
      f.get();
    }
    ItlDiscriminator.itl().clear();

    log.info("END");
  }
}
