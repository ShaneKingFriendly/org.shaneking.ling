package sktest.ling.zero.time;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.time.Stopwatch0;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class Stopwatch0Test {

  @Test
  void createStarted() {
    assertTrue(Stopwatch0.createStarted().isRunning());

    assertTrue(Stopwatch0.createStarted(System::nanoTime).isRunning());
  }

  @Test
  void createUnstarted() {
    assertFalse(Stopwatch0.createUnstarted().isRunning());

    assertFalse(Stopwatch0.createUnstarted(System::nanoTime).isRunning());
  }

  @Test
  void elapsed() throws InterruptedException {
    Stopwatch0 stopwatch0 = Stopwatch0.createStarted();
    Thread.sleep(1000);
    stopwatch0.stop();
    assertAll(
      () -> assertTrue(String0.sameTotal("PT1.003672497S", String.valueOf(stopwatch0.elapsed())) > 5),
      () -> assertTrue(stopwatch0.elapsed(TimeUnit.MILLISECONDS) > 999)
    );

  }

  @Test
  void testToString() {
    assertTrue(Stopwatch0.createStarted().stop().reset().start().toString().contains("μs"));
  }
}
