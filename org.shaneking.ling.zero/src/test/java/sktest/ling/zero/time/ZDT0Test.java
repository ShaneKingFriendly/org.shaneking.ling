package sktest.ling.zero.time;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.time.ZDT0;
import org.shaneking.ling.zero.util.Date0;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class ZDT0Test {

  @Test
  void d() {
    assertEquals(Date0.on().date(), ZDT0.on().d());
  }

  @Test
  void dT() {
    assertEquals(Date0.on().dateTime(), ZDT0.on().dT());
  }

  @Test
  void dTZ() {
    assertEquals(Date0.on().dateTimeZone(), ZDT0.on().dTZ());
  }

  @Test
  void dTS() {
    /*
Expected :2021-01-15 21:17:13.568
Actual   :2021-01-15 21:17:13.569
     */
    assertTrue(String0.sameTotal(Date0.on().dateTimes(), ZDT0.on().dTS()) > 18);//maybe not same second
  }

  @Test
  void dTSZ() {
    /*
Expected :2021-01-15 21:17:13.568+0800
Actual   :2021-01-15 21:17:13.569+0800
     */
    assertTrue(String0.sameTotal(Date0.on().dateTimesZone(), ZDT0.on().dTSZ()) > 18);//maybe not same second
  }

  @Test
  void dt() {
    assertEquals(Date0.on().datetime(), ZDT0.on().dt());
  }

  @Test
  void dts() {
    /*
Expected :20210115211713568
Actual   :20210115211713569
     */
    assertTrue(String0.sameTotal(Date0.on().datetimes(), ZDT0.on().dts()) > 13);//maybe not same second
  }

  @Test
  void f() {
    assertEquals(Date0.on().format(Date0.Y_M_D), ZDT0.on().f(Date0.Y_M_D));
  }

  @Test
  void p() {
    assertAll(
      () -> assertDoesNotThrow(() -> ZDT0.on().dTZ(Date0.on().dateTimeZone())),
      () -> assertThrows(DateTimeParseException.class, () -> ZDT0.on().ySmSd(Date0.on().ymd()))
    );
  }

  @Test
  void t() {
    assertEquals(Date0.on().time(), ZDT0.on().t());
  }

  @Test
  void tZ() {
    assertEquals(Date0.on().timeZone(), ZDT0.on().tZ());
  }

  @Test
  void tS() {
    /*
Expected :21:17:13.794
Actual   :21:17:13.796
     */
    assertTrue(String0.sameTotal(Date0.on().timeS(), ZDT0.on().tS()) > 7);//maybe not same second
  }

  @Test
  void tSZ() {
    /*
Expected :21:17:13.794+0800
Actual   :21:17:13.796+0800
     */
    assertTrue(String0.sameTotal(Date0.on().timeSZone(), ZDT0.on().tSZ()) > 7);//maybe not same second
  }

  @Test
  void ts() {
    /*
Expected :212051705
Actual   :212051706
     */
    assertTrue(String0.sameTotal(Date0.on().times(), ZDT0.on().ts()) > 5);//maybe not same second
  }

  @Test
  void ymd() {
    assertEquals(Date0.on().ymd(), ZDT0.on().ymd());
  }

  @Test
  void ySmSd() {
    assertEquals(Date0.on().ySmSd(), ZDT0.on().ySmSd());
  }

  @Test
  void zone() {
    assertEquals(Date0.on().zone(), ZDT0.on().z());
  }
}
