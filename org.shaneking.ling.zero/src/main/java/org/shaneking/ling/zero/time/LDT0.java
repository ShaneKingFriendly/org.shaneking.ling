package org.shaneking.ling.zero.time;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.util.Date0;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Accessors(chain = true)
public class LDT0 {
  @Getter
  @Setter
  private LocalDateTime localDateTime;

  private LDT0(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  public static LDT0 on() {
    return LDT0.on(LocalDateTime.now());
  }

  public static LDT0 on(LocalDateTime localDateTime) {
    return new LDT0(localDateTime);
  }

  public String d() {
    return f(Date0.Y_M_D);
  }

  public LDT0 d(String s) {
    return p(Date0.Y_M_D, s);
  }

  public String dT() {
    return f(Date0.DATE_TIME);
  }

  public LDT0 dT(String s) {
    return p(Date0.DATE_TIME, s);
  }

  public String dTS() {
    return f(Date0.DATE_TIME_SSS);
  }

  public LDT0 dTS(String s) {
    return p(Date0.DATE_TIME_SSS, s);
  }

  public String dt() {
    return f(Date0.DATETIME);
  }

  public LDT0 dt(String s) {
    return p(Date0.DATETIME, s);
  }

  public String dts() {
    return f(Date0.DATETIMESSS);
  }

  public LDT0 dts(String s) {
    return p(Date0.DATETIMESSS, s);
  }

  public String f(String pattern) {
    return this.getLocalDateTime().format(DateTimeFormatter.ofPattern(pattern));
  }

  public LDT0 p(String s) {
    return p(Date0.DATE_TIME, s);
  }

  public LDT0 p(String pattern, String s) {
    return this.setLocalDateTime(LocalDateTime.parse(s, DateTimeFormatter.ofPattern(pattern)));
  }

  public String t() {
    return f(Date0.H_MI_S);
  }

  public LDT0 t(String s) {
    return p(Date0.H_MI_S, s);
  }

  public String tS() {
    return f(Date0.TIME_SSS);
  }

  public LDT0 tS(String s) {
    return p(Date0.TIME_SSS, s);
  }

  public String ts() {
    return f(Date0.TIMESSS);
  }

  public LDT0 ts(String s) {
    return p(Date0.TIMESSS, s);
  }

  public String ymd() {
    return f(Date0.YMD);
  }

  public LDT0 ymd(String s) {
    return p(Date0.YMD, s);
  }

  public String ySmSd() {
    return f(Date0.YsMsD);
  }

  public LDT0 ySmSd(String s) {
    return p(Date0.YsMsD, s);
  }
}
