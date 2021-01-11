package org.shaneking.ling.zero.time;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.util.Date0;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Accessors(chain = true)
@ToString
public class LocalDate0 {
  @Getter
  @Setter
  private LocalDate localDate = LocalDate.now();

  private LocalDate0(LocalDate localDate) {
    this.localDate = localDate;
  }

  public static LocalDate now() {
    return LocalDate.now();
  }

  public static LocalDate0 on() {
    return LocalDate0.on(LocalDate0.now());
  }

  public static LocalDate0 on(LocalDate localDate) {
    return new LocalDate0(localDate);
  }

  public String date() {
    return format(Date0.Y_M_D);
  }

  public String format(String pattern) {
    return this.getLocalDate().format(DateTimeFormatter.ofPattern(pattern));
  }

  public LocalDate0 parse(String s) {
    return parse(Date0.Y_M_D, s);
  }

  public LocalDate0 parse(String pattern, String s) {
    return this.setLocalDate(LocalDate.parse(s, DateTimeFormatter.ofPattern(pattern)));
  }

  public String ymd() {
    return format(Date0.YMD);
  }

  public String ySmSd() {
    return format(Date0.YsMsD);
  }
}
