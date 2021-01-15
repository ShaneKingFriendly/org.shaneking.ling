package sktest.ling.zero.time;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.time.LocalDateTime0;
import org.shaneking.ling.zero.util.Date0;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateTime0Test {

  @Test
  void date() {
    assertEquals(Date0.on().date(), LocalDateTime0.on().date());
  }

  @Test
  void dateTime() {
    assertEquals(Date0.on().dateTime(), LocalDateTime0.on().dateTime());
  }

  @Test
  void dateTimes() {
    /*
Expected :2021-01-15 21:17:13.568
Actual   :2021-01-15 21:17:13.569
     */
    assertTrue(String0.sameTotal(Date0.on().dateTimes(), LocalDateTime0.on().dateTimes()) > 20);
  }

  @Test
  void datetime() {
    assertEquals(Date0.on().datetime(), LocalDateTime0.on().datetime());
  }

  @Test
  void datetimes() {
    assertTrue(String0.sameTotal(Date0.on().datetimes(), LocalDateTime0.on().datetimes()) > 15);
  }

  @Test
  void format() {
    assertEquals(Date0.on().format(Date0.Y_M_D), LocalDateTime0.on().format(Date0.Y_M_D));
  }

  @Test
  void parse() {
    assertAll(
      () -> assertThrows(DateTimeParseException.class, () -> LocalDateTime0.on().parse(Date0.on().ySmSd())),
      () -> assertDoesNotThrow(() -> LocalDateTime0.on().parse(Date0.on().dateTime()))
    );
  }

  @Test
  void time() {
    assertEquals(Date0.on().time(), LocalDateTime0.on().time());
  }

  @Test
  void timeS() {
    /*
Expected :21:17:13.794
Actual   :21:17:13.796
     */
    assertTrue(String0.sameTotal(Date0.on().timeS(), LocalDateTime0.on().timeS()) > 10);
  }

  @Test
  void times() {
    /*
Expected :212051705
Actual   :212051706
     */
    assertTrue(String0.sameTotal(Date0.on().times(), LocalDateTime0.on().times()) > 6);
  }

  @Test
  void ymd() {
    assertEquals(Date0.on().ymd(), LocalDateTime0.on().ymd());
  }

  @Test
  void ySmSd() {
    assertEquals(Date0.on().ySmSd(), LocalDateTime0.on().ySmSd());
  }
}
