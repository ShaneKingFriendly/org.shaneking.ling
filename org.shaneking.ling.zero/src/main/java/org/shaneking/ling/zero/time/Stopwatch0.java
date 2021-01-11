package org.shaneking.ling.zero.time;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.Boolean0;

import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

public class Stopwatch0 {
  private final Ticker0 ticker;
  private boolean isRunning;
  private long elapsedNanos;
  private long startTick;

  Stopwatch0() {
    ticker = Ticker0.systemTicker();
  }

  Stopwatch0(@NonNull Ticker0 ticker) {
    this.ticker = ticker;
  }

  public static Stopwatch0 createUnstarted() {
    return new Stopwatch0();
  }

  public static Stopwatch0 createUnstarted(Ticker0 ticker) {
    return new Stopwatch0(ticker);
  }

  public static Stopwatch0 createStarted() {
    return new Stopwatch0().start();
  }

  public static Stopwatch0 createStarted(Ticker0 ticker) {
    return new Stopwatch0(ticker).start();
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

  public boolean isRunning() {
    return isRunning;
  }

  public Stopwatch0 start() {
    Boolean0.checkState(!isRunning, "This stopwatch is already running.");
    isRunning = true;
    startTick = ticker.read();
    return this;
  }

  public Stopwatch0 stop() {
    long tick = ticker.read();
    Boolean0.checkState(isRunning, "This stopwatch is already stopped.");
    isRunning = false;
    elapsedNanos += tick - startTick;
    return this;
  }

  public Stopwatch0 reset() {
    elapsedNanos = 0L;
    isRunning = false;
    return this;
  }

  private long elapsedNanos() {
    return isRunning ? ticker.read() - startTick + elapsedNanos : elapsedNanos;
  }

  public long elapsed(TimeUnit desiredUnit) {
    return desiredUnit.convert(elapsedNanos(), NANOSECONDS);
  }

  public Duration elapsed() {
    return Duration.ofNanos(elapsedNanos());
  }

  @Override
  public String toString() {
    long nanos = elapsedNanos();

    TimeUnit unit = chooseUnit(nanos);
    double value = (double) nanos / NANOSECONDS.convert(1, unit);

    return String.format(Locale.ROOT, "%.4g", value) + " " + abbreviate(unit);
  }
}
