package sktest.ling.zero.time;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.time.LDT0;
import org.shaneking.ling.zero.util.Date0;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class LDT0Test {

  @Test
  void d() {
    assertEquals(Date0.on().date(), LDT0.on().d());
  }

  @Test
  void dT() {
    assertEquals(Date0.on().dateTime(), LDT0.on().dT());
  }

  @Test
  void dTS() {
    /*
Expected :2021-01-15 21:17:13.568
Actual   :2021-01-15 21:17:13.569
     */
    assertTrue(String0.sameTotal(Date0.on().dateTimes(), LDT0.on().dTS()) > 18);//maybe not same second
  }

  @Test
  void dt() {
    assertEquals(Date0.on().datetime(), LDT0.on().dt());
  }

  @Test
  void dts() {
    /*
Expected :20210115211713568
Actual   :20210115211713569
     */
    assertTrue(String0.sameTotal(Date0.on().datetimes(), LDT0.on().dts()) > 13);//maybe not same second
  }

  @Test
  void f() {
    assertEquals(Date0.on().format(Date0.Y_M_D), LDT0.on().f(Date0.Y_M_D));
  }

  @Test
  void p() {
    assertAll(
      () -> assertDoesNotThrow(() -> LDT0.on().p(Date0.on().dateTime())),
      () -> assertThrows(DateTimeParseException.class, () -> LDT0.on().p(Date0.on().ySmSd()))
    );
  }

  @Test
  void t() {
    assertEquals(Date0.on().time(), LDT0.on().t());
  }

  @Test
  void tS() {
    /*
Expected :21:17:13.794
Actual   :21:17:13.796
     */
    assertTrue(String0.sameTotal(Date0.on().timeS(), LDT0.on().tS()) > 7);//maybe not same second
  }

  @Test
  void ts() {
    /*
Expected :212051705
Actual   :212051706
     */
    assertTrue(String0.sameTotal(Date0.on().times(), LDT0.on().ts()) > 5);//maybe not same second
  }

  @Test
  void ymd() {
    assertEquals(Date0.on().ymd(), LDT0.on().ymd());
  }

  @Test
  void ySmSd() {
    assertEquals(Date0.on().ySmSd(), LDT0.on().ySmSd());
  }
}
