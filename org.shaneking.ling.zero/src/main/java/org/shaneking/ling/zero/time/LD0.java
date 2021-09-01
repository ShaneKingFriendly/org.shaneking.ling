package org.shaneking.ling.zero.time;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.util.Date0;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Accessors(chain = true)
public class LD0 {
  @Getter
  @Setter
  private LocalDate localDate;

  private LD0(LocalDate localDate) {
    this.localDate = localDate;
  }

  public static LD0 on() {
    return LD0.on(LocalDate.now());
  }

  public static LD0 on(LocalDate localDate) {
    return new LD0(localDate);
  }

  public String date() {
    return format(Date0.Y_M_D);
  }

  public String format(String pattern) {
    return this.getLocalDate().format(DateTimeFormatter.ofPattern(pattern));
  }

  public LD0 parse(String s) {
    return parse(Date0.Y_M_D, s);
  }

  public LD0 parse(String pattern, String s) {
    return this.setLocalDate(LocalDate.parse(s, DateTimeFormatter.ofPattern(pattern)));
  }

  public String ymd() {
    return format(Date0.YMD);
  }

  public String ySmSd() {
    return format(Date0.YsMsD);
  }
}
