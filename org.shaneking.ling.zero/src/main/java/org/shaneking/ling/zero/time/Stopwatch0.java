package org.shaneking.ling.zero.time;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.Boolean0;

import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.LongSupplier;

import static java.util.concurrent.TimeUnit.*;

public class Stopwatch0 {
  private final LongSupplier ticker;
  private boolean isRunning;
  private long elapsedNanos;
  private long startTick;

  Stopwatch0() {
    ticker = System::nanoTime;
  }

  Stopwatch0(@NonNull LongSupplier ticker) {
    this.ticker = ticker;
  }

  public static Stopwatch0 createStarted() {
    return new Stopwatch0().start();
  }

  public static Stopwatch0 createStarted(LongSupplier ticker) {
    return new Stopwatch0(ticker).start();
  }

  public static Stopwatch0 createUnstarted() {
    return new Stopwatch0();
  }

  public static Stopwatch0 createUnstarted(LongSupplier ticker) {
    return new Stopwatch0(ticker);
  }

  private static String abbreviate(TimeUnit unit) {
    switch (unit) {
      case NANOSECONDS:
        return "ns";
      case MICROSECONDS:
        return "\u03bcs"; // μs
      case MILLISECONDS:
        return "ms";
      case SECONDS:
        return "s";
      case MINUTES:
        return "min";
      case HOURS:
        return "h";
      case DAYS:
        return "d";
      default:
        throw new AssertionError();
    }
  }

  private static TimeUnit chooseUnit(long nanos) {
    if (DAYS.convert(nanos, NANOSECONDS) > 0) {
      return DAYS;
    }
    if (HOURS.convert(nanos, NANOSECONDS) > 0) {
      return HOURS;
    }
    if (MINUTES.convert(nanos, NANOSECONDS) > 0) {
      return MINUTES;
    }
    if (SECONDS.convert(nanos, NANOSECONDS) > 0) {
      return SECONDS;
    }
    if (MILLISECONDS.convert(nanos, NANOSECONDS) > 0) {
      return MILLISECONDS;
    }
    if (MICROSECONDS.convert(nanos, NANOSECONDS) > 0) {
      return MICROSECONDS;
    }
    return NANOSECONDS;
  }

  public Duration elapsed() {
    return Duration.ofNanos(elapsedNanos());
  }

  public long elapsed(@NonNull TimeUnit desiredUnit) {
    return desiredUnit.convert(elapsedNanos(), NANOSECONDS);
  }

  public boolean isRunning() {
    return isRunning;
  }

  public Stopwatch0 reset() {
    elapsedNanos = 0L;
    isRunning = false;
    return this;
  }

  public Stopwatch0 start() {
    Boolean0.checkState(!isRunning, "This stopwatch is already running.");
    isRunning = true;
    startTick = ticker.getAsLong();
    return this;
  }

  public Stopwatch0 stop() {
    long tick = ticker.getAsLong();
    Boolean0.checkState(isRunning, "This stopwatch is already stopped.");
    isRunning = false;
    elapsedNanos += tick - startTick;
    return this;
  }

  @Override
  public String toString() {
    long nanos = elapsedNanos();

    TimeUnit unit = chooseUnit(nanos);
    double value = (double) nanos / NANOSECONDS.convert(1, unit);

    return String.format(Locale.ROOT, "%.4g", value) + " " + abbreviate(unit);
  }

  private long elapsedNanos() {
    return isRunning ? ticker.getAsLong() - startTick + elapsedNanos : elapsedNanos;
  }
}
