package org.shaneking.ling.zero.time;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.util.Date0;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Accessors(chain = true)
public class ZDT0 {
  @Getter
  @Setter
  private ZonedDateTime zonedDateTime;

  private ZDT0(ZonedDateTime zonedDateTime) {
    this.zonedDateTime = zonedDateTime;
  }

  public static ZDT0 on() {
    return ZDT0.on(ZonedDateTime.now());
  }

  public static ZDT0 on(ZonedDateTime zonedDateTime) {
    return new ZDT0(zonedDateTime);
  }

  public String d() {
    return f(Date0.Y_M_D);
  }

  public ZDT0 d(String s) {
    return p(Date0.Y_M_D, s);
  }

  public String dT() {
    return f(Date0.DATE_TIME);
  }

  public ZDT0 dT(String s) {
    return p(Date0.DATE_TIME, s);
  }

  public String dTZ() {
    return f(Date0.DATE_TIME_ZONE);
  }

  public ZDT0 dTZ(String s) {
    return p(Date0.DATE_TIME_ZONE, s);
  }

  public String dTS() {
    return f(Date0.DATE_TIME_SSS);
  }

  public ZDT0 dTS(String s) {
    return p(Date0.DATE_TIME_SSS, s);
  }

  public String dTSZ() {
    return f(Date0.DATE_TIME_SSS_ZONE);
  }

  public ZDT0 dTSZ(String s) {
    return p(Date0.DATE_TIME_SSS_ZONE, s);
  }

  public String dt() {
    return f(Date0.DATETIME);
  }

  public ZDT0 dt(String s) {
    return p(Date0.DATETIME, s);
  }

  public String dts() {
    return f(Date0.DATETIMESSS);
  }

  public ZDT0 dts(String s) {
    return p(Date0.DATETIMESSS, s);
  }

  public String f(String pattern) {
    return this.getZonedDateTime().format(DateTimeFormatter.ofPattern(pattern));
  }

  public ZDT0 p(String pattern, String s) {
    return this.setZonedDateTime(ZonedDateTime.parse(s, DateTimeFormatter.ofPattern(pattern)));
  }

  public String t() {
    return f(Date0.H_MI_S);
  }

  public ZDT0 t(String s) {
    return p(Date0.H_MI_S, s);
  }

  public String tZ() {
    return f(Date0.TIME_ZONE);
  }

  public ZDT0 tZ(String s) {
    return p(Date0.TIME_ZONE, s);
  }

  public String tS() {
    return f(Date0.TIME_SSS);
  }

  public ZDT0 tS(String s) {
    return p(Date0.TIME_SSS, s);
  }

  public String tSZ() {
    return f(Date0.TIME_SSS_ZONE);
  }

  public ZDT0 tSZ(String s) {
    return p(Date0.TIME_SSS_ZONE, s);
  }

  public String ts() {
    return f(Date0.TIMESSS);
  }

  public ZDT0 ts(String s) {
    return p(Date0.TIMESSS, s);
  }

  public String ymd() {
    return f(Date0.YMD);
  }

  public ZDT0 ymd(String s) {
    return p(Date0.YMD, s);
  }

  public String ySmSd() {
    return f(Date0.YsMsD);
  }

  public ZDT0 ySmSd(String s) {
    return p(Date0.YsMsD, s);
  }

  public String z() {
    return f(Date0.XXX);
  }

  public ZDT0 z(String s) {
    return p(Date0.XXX, s);
  }
}
