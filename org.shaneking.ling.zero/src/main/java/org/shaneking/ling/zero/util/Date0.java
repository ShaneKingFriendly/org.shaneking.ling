package org.shaneking.ling.zero.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.lang.String0;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Accessors(chain = true)
public class Date0 {
  public static final String H_MI_S = "HH:mm:ss";
  public static final String HMIS = "HHmmss";
  public static final String SSS = "SSS";
  public static final String XXX = "XXX";//+08:00
  public static final String Y_M_D = "yyyy-MM-dd";
  public static final String YMD = "yyyyMMdd";
  public static final String YsMsD = "yyyy/MM/dd";

  public static final String DATE_TIME = Y_M_D + String0.BLANK + H_MI_S;
  public static final String DATE_TIME_SSS = DATE_TIME + String0.DOT + SSS;
  public static final String DATE_TIME_SSS_ZONE = DATE_TIME_SSS + XXX;
  public static final String DATE_TIME_ZONE = DATE_TIME + XXX;
  public static final String DATETIME = YMD + HMIS;
  public static final String DATETIMESSS = DATETIME + SSS;
  public static final String TIMESSS = HMIS + SSS;
  public static final String TIME_SSS = H_MI_S + String0.DOT + SSS;
  public static final String TIME_SSS_ZONE = TIME_SSS + XXX;
  public static final String TIME_ZONE = H_MI_S + XXX;

  @Getter
  @Setter
  private Date date;

  private Date0(Date date) {
    this.date = date;
  }

  public static Date0 on() {
    return Date0.on(new Date());
  }

  public static Date0 on(Date date) {
    return new Date0(date);
  }

  public String date() {
    return format(Y_M_D);
  }

  public String dateTime() {
    return format(DATE_TIME);
  }

  public String dateTimes() {
    return format(DATE_TIME_SSS);
  }

  public String dateTimesZone() {
    return format(DATE_TIME_SSS_ZONE);
  }

  public String dateTimeZone() {
    return format(DATE_TIME_ZONE);
  }

  public String datetime() {
    return format(DATETIME);
  }

  public String datetimes() {
    return format(DATETIMESSS);
  }

  public String format(String pattern) {
    return new SimpleDateFormat(pattern).format(this.getDate());
  }

  public Date0 parse(String s) throws ParseException {
    return parse(Y_M_D, s);
  }

  public Date0 parse(String pattern, String s) throws ParseException {
    return this.setDate(new SimpleDateFormat(pattern).parse(s));
  }

  public String time() {
    return format(H_MI_S);
  }

  public String timeS() {
    return format(TIME_SSS);
  }

  public String timeSZone() {
    return format(TIME_SSS_ZONE);
  }

  public String timeZone() {
    return format(TIME_ZONE);
  }

  public String times() {
    return format(TIMESSS);
  }

  public String ymd() {
    return format(YMD);
  }

  public String ySmSd() {
    return format(YsMsD);
  }

  public String zone() {
    return format(XXX);
  }
}
