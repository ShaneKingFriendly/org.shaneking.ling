package sktest.ling.zero.time;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.time.LT0;
import org.shaneking.ling.zero.util.Date0;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class LT0Test {

  @Test
  void format() {
//    assertEquals(Date0.on().format(Date0.H_MI_S), LT0.on().format(Date0.H_MI_S));//LT0Test.format:16 expected: <22:21:19> but was: <22:21:20>
    assertTrue(String0.sameTotal(Date0.on().format(Date0.H_MI_S), LT0.on().format(Date0.H_MI_S)) > 6);
  }

  @Test
  void parse() {
    assertAll(
      () -> assertThrows(DateTimeParseException.class, () -> LT0.on().parse(Date0.on().times())),
      () -> assertDoesNotThrow(() -> LT0.on().parse(Date0.on().time()))
    );
  }

  @Test
  void time() {
    assertEquals(Date0.on().time(), LT0.on().time());
  }

  @Test
  void timeS() {
    /*
Expected :21:17:13.794
Actual   :21:17:13.796
     */
    assertTrue(String0.sameTotal(Date0.on().timeS(), LT0.on().timeS()) > 7);//maybe not same second
  }

  @Test
  void times() {
    /*
Expected :212051705
Actual   :212051706
     */
    assertTrue(String0.sameTotal(Date0.on().times(), LT0.on().times()) > 5);//maybe not same second
  }
}
