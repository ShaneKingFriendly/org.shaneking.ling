package sktest.ling.zero.lang;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.Boolean0;
import org.shaneking.ling.zero.lang.String0;

import static org.junit.jupiter.api.Assertions.*;

class Boolean0Test {

  @Test
  void checkArgument() {
    assertAll(
      () -> assertDoesNotThrow(() -> Boolean0.checkArgument(true, null)),
      () -> assertThrows(IllegalArgumentException.class, () -> Boolean0.checkArgument(false, null))
    );
  }

  @Test
  void checkState() {
    assertAll(
      () -> assertDoesNotThrow(() -> Boolean0.checkState(true, null)),
      () -> assertThrows(IllegalStateException.class, () -> Boolean0.checkState(false, null))
    );
  }

  @Test
  void falseTo() {
    assertTrue(Boolean0.falseTo(false, () -> true));
  }

  @Test
  void sf() {
    assertAll(
      () -> assertTrue(Boolean0.sf(String0.S)),
      () -> assertFalse(Boolean0.sf(String0.F)),
      () -> assertFalse(Boolean0.sf(null)),
      () -> assertEquals(String0.S, Boolean0.sf(true)),
      () -> assertEquals(String0.F, Boolean0.sf(false))
    );
  }

  @Test
  void tf() {
    assertAll(
      () -> assertTrue(Boolean0.tf(String0.T)),
      () -> assertFalse(Boolean0.tf(String0.F)),
      () -> assertFalse(Boolean0.tf(null)),
      () -> assertEquals(String0.T, Boolean0.tf(true)),
      () -> assertEquals(String0.F, Boolean0.tf(false))
    );
  }

  @Test
  void yn() {
    assertAll(
      () -> assertTrue(Boolean0.yn(String0.Y)),
      () -> assertFalse(Boolean0.yn(String0.N)),
      () -> assertFalse(Boolean0.yn(null)),
      () -> assertEquals(String0.Y, Boolean0.yn(true)),
      () -> assertEquals(String0.N, Boolean0.yn(false))
    );
  }
}
