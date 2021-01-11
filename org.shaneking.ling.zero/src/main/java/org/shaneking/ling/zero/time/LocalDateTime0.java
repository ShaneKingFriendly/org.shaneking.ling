package org.shaneking.ling.zero.time;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.util.Date0;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Accessors(chain = true)
@ToString
public class LocalDateTime0 {
  @Getter
  @Setter
  private LocalDateTime localDateTime = LocalDateTime.now();

  private LocalDateTime0(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  public static LocalDateTime now() {
    return LocalDateTime.now();
  }

  public static LocalDateTime0 on() {
    return LocalDateTime0.on(LocalDateTime0.now());
  }

  public static LocalDateTime0 on(LocalDateTime localDateTime) {
    return new LocalDateTime0(localDateTime);
  }

  public String date() {
    return format(Date0.Y_M_D);
  }

  public String dateTime() {
    return format(Date0.DATE_TIME);
  }

  public String dateTimes() {
    return format(Date0.DATE_TIME_SSS);
  }

  public String datetime() {
    return format(Date0.DATETIME);
  }

  public String datetimes() {
    return format(Date0.DATETIMESSS);
  }

  public String format(String pattern) {
    return this.getLocalDateTime().format(DateTimeFormatter.ofPattern(pattern));
  }

  public LocalDateTime0 parse(String s) {
    return parse(Date0.DATE_TIME, s);
  }

  public LocalDateTime0 parse(String pattern, String s) {
    return this.setLocalDateTime(LocalDateTime.parse(s, DateTimeFormatter.ofPattern(pattern)));
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

  public String ymd() {
    return format(Date0.YMD);
  }

  public String ySmSd() {
    return format(Date0.YsMsD);
  }
}
