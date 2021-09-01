package org.shaneking.ling.zero.time;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.util.Date0;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Accessors(chain = true)
public class LT0 {
  @Getter
  @Setter
  private LocalTime localTime;

  private LT0(LocalTime localTime) {
    this.localTime = localTime;
  }

  public static LT0 on() {
    return LT0.on(LocalTime.now());
  }

  public static LT0 on(LocalTime localTime) {
    return new LT0(localTime);
  }

  public String format(String pattern) {
    return this.getLocalTime().format(DateTimeFormatter.ofPattern(pattern));
  }

  public LT0 parse(String s) {
    return parse(Date0.H_MI_S, s);
  }

  public LT0 parse(String pattern, String s) {
    return this.setLocalTime(LocalTime.parse(s, DateTimeFormatter.ofPattern(pattern)));
  }

  public String time() {
    return format(Date0.H_MI_S);
  }

  public String timeS() {
    return format(Date0.TIME_SSS);
  }

  public String times() {
    return format(Date0.TIMESSS);
  }
}
