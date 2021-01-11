package org.shaneking.ling.zero.time;

public abstract class Ticker0 {
  private static final Ticker0 SYSTEM_TICKER =
    new Ticker0() {
      @Override
      public long read() {
        return System.nanoTime();
      }
    };

  protected Ticker0() {
  }

  public static Ticker0 systemTicker() {
    return SYSTEM_TICKER;
  }

  public abstract long read();
}
