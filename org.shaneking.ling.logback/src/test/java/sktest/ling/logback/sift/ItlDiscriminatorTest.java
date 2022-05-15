package sktest.ling.logback.sift;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.logback.sift.ItlDiscriminator;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;
import org.slf4j.MDC;

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

    MDC.put("skLogMdcTracing", "stream");
    ItlDiscriminator.itl().put("skLogItlFile", "stream");
    IntStream.range(1, 100).boxed().forEach(i -> {
      log.info(String.valueOf(i));
    });

    MDC.put("skLogMdcTracing", "ForkJoinPool");
    ItlDiscriminator.itl().put("skLogItlFile", "ForkJoinPool");
    IntStream.range(1, 100).boxed().parallel().forEach(i -> {
      log.info(String.valueOf(i));
    });

    MDC.put("skLogMdcTracing", "ThreadPool");
    ItlDiscriminator.itl().put("skLogItlFile", "ThreadPool");
    ExecutorService es = Executors.newFixedThreadPool(3);
    List<Future<?>> list = List0.newArrayList();
    for (int i = 0; i < 100; i++) {
      int fi = i;
      list.add(es.submit(() -> log.info(String.valueOf(fi))));
    }
    for (Future<?> f : list) {
      f.get();
    }
    MDC.put("skLogMdcTracing", String0.EMPTY);
    ItlDiscriminator.itl().put("skLogItlFile", String0.EMPTY);

    log.info("END");
  }
}
