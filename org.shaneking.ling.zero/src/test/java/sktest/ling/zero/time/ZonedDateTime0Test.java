package sktest.ling.zero.time;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.time.ZonedDateTime0;
import org.shaneking.ling.zero.util.Date0;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class ZonedDateTime0Test {

  @Test
  void date() {
    assertEquals(Date0.on().date(), ZonedDateTime0.on().date());
  }

  @Test
  void dateTime() {
    assertEquals(Date0.on().dateTime(), ZonedDateTime0.on().dateTime());
  }

  @Test
  void dateTimeZone() {
    assertEquals(Date0.on().dateTimeZone(), ZonedDateTime0.on().dateTimeZone());
  }

  @Test
  void dateTimes() {
    /*
Expected :2021-01-15 21:17:13.568
Actual   :2021-01-15 21:17:13.569
     */
    assertTrue(String0.sameTotal(Date0.on().dateTimes(), ZonedDateTime0.on().dateTimes()) > 20);
  }

  @Test
  void dateTimesZone() {
    /*
Expected :2021-01-15 21:17:13.568+0800
Actual   :2021-01-15 21:17:13.569+0800
     */
    assertTrue(String0.sameTotal(Date0.on().dateTimesZone(), ZonedDateTime0.on().dateTimesZone()) > 20);
  }

  @Test
  void datetime() {
    assertEquals(Date0.on().datetime(), ZonedDateTime0.on().datetime());
  }

  @Test
  void datetimes() {
    assertTrue(String0.sameTotal(Date0.on().datetimes(), ZonedDateTime0.on().datetimes()) > 14);//maybe not same second
  }

  @Test
  void format() {
    assertEquals(Date0.on().format(Date0.Y_M_D), ZonedDateTime0.on().format(Date0.Y_M_D));
  }

  @Test
  void parse() {
    assertAll(
      () -> assertThrows(DateTimeParseException.class, () -> ZonedDateTime0.on().parse(Date0.on().ySmSd())),
      () -> assertDoesNotThrow(() -> ZonedDateTime0.on().parse(Date0.on().dateTimeZone()))
    );
  }

  @Test
  void time() {
    assertEquals(Date0.on().time(), ZonedDateTime0.on().time());
  }

  @Test
  void timeZone() {
    assertEquals(Date0.on().timeZone(), ZonedDateTime0.on().timeZone());
  }

  @Test
  void timeS() {
    /*
Expected :21:17:13.794
Actual   :21:17:13.796
     */
    assertTrue(String0.sameTotal(Date0.on().timeS(), ZonedDateTime0.on().timeS()) > 10);
  }

  @Test
  void timeSZone() {
    /*
Expected :21:17:13.794+0800
Actual   :21:17:13.796+0800
     */
    assertTrue(String0.sameTotal(Date0.on().timeSZone(), ZonedDateTime0.on().timeSZone()) > 10);
  }

  @Test
  void times() {
    /*
Expected :212051705
Actual   :212051706
     */
    assertTrue(String0.sameTotal(Date0.on().times(), ZonedDateTime0.on().times()) > 6);
  }

  @Test
  void ymd() {
    assertEquals(Date0.on().ymd(), ZonedDateTime0.on().ymd());
  }

  @Test
  void ySmSd() {
    assertEquals(Date0.on().ySmSd(), ZonedDateTime0.on().ySmSd());
  }

  @Test
  void zone() {
    assertEquals(Date0.on().zone(), ZonedDateTime0.on().zone());
  }
}
