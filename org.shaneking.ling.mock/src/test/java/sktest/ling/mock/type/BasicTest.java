package sktest.ling.mock.type;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.mock.type.Basic;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;

import static org.junit.jupiter.api.Assertions.*;

class BasicTest {

  @Test
  void l() {
    assertAll(
      () -> assertNull(Basic.list(null)),
      () -> assertNull(Basic.list(List0.newArrayList())),
      () -> assertTrue(Basic.NUMBER.contains(Basic.list(List0.newArrayList(Basic.NUMBER.split(String0.EMPTY)))))
    );
  }

  @Test
  void a() {
    assertAll(
      () -> assertNull(Basic.array(null)),
      () -> assertNull(Basic.array(new String[]{})),
      () -> assertTrue(Basic.NUMBER.contains(Basic.array(Basic.NUMBER.split(String0.EMPTY))))
    );
  }

  @Test
  void n() {
    assertTrue(Basic.natural(3, 7) >= 3);
    assertTrue(Basic.natural(3, 7) < 7);
  }

  @Test
  void c() {
    assertAll(
      () -> assertTrue(Basic.BETA.contains(String.valueOf(Basic.character()))),
      () -> assertTrue(Basic.ALPHA.contains(String.valueOf(Basic.character(Basic.ALPHA))))
    );
  }

  @Test
  void s() {
    assertAll(
      () -> assertTrue(Basic.string().length() >= 3),
      () -> assertTrue(Basic.string().length() < 7),

      () -> assertTrue(Basic.BETA.contains(Basic.string(1))),

      () -> assertTrue(Integer.valueOf("012") > 10),
      () -> assertTrue(Integer.valueOf(Basic.string(Basic.NUMBER)) > 10),

      () -> assertTrue(Basic.string(3, 7).length() >= 3),
      () -> assertTrue(Basic.string(3, 7).length() < 7),

      () -> assertTrue(Integer.valueOf(Basic.string(Basic.NUMBER, 1)) >= 0),

      () -> assertTrue(Integer.valueOf(Basic.string(Basic.NUMBER, 3, 7)) > 10)
    );
  }
}
