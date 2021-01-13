package org.shaneking.ling.zero.time;

import lombok.Getter;
import lombok.Setter;
import org.shaneking.ling.zero.util.Date0;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTime0 {
  @Getter
  @Setter
  private ZonedDateTime zonedDateTime;

  private ZonedDateTime0(ZonedDateTime zonedDateTime) {
    this.zonedDateTime = zonedDateTime;
  }

  public static ZonedDateTime0 on() {
    return ZonedDateTime0.on(ZonedDateTime.now());
  }

  public static ZonedDateTime0 on(ZonedDateTime zonedDateTime) {
    return new ZonedDateTime0(zonedDateTime);
  }

  public String date() {
    return format(Date0.Y_M_D);
  }

  public String dateTime() {
    return format(Date0.DATE_TIME);
  }

  public String dateTimeZone() {
    return format(Date0.DATE_TIME_ZONE);
  }

  public String dateTimes() {
    return format(Date0.DATE_TIME_SSS);
  }

  public String dateTimesZone() {
    return format(Date0.DATE_TIME_SSS_ZONE);
  }

  public String datetime() {
    return format(Date0.DATETIME);
  }

  public String datetimes() {
    return format(Date0.DATETIMESSS);
  }

  public String format(String pattern) {
    return this.getZonedDateTime().format(DateTimeFormatter.ofPattern(pattern));
  }
//  public ZonedDateTime0 parse(String s) {
//    return parse(Date0.DATE_TIME_SSS, s);
//  }
//
//  public ZonedDateTime0 parse(String pattern, String s) {
//    return this.setZonedDateTime(ZonedDateTime.parse(s, DateTimeFormatter.ofPattern(pattern)));
//  }

  public String time() {
    return format(Date0.H_MI_S);
  }

  public String timeZone() {
    return format(Date0.TIME_ZONE);
  }

  public String timeS() {
    return format(Date0.TIME_SSS);
  }

  public String timeSZone() {
    return format(Date0.TIME_SSS_ZONE);
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

  public String zone() {
    return format(Date0.XXX);
  }
}
