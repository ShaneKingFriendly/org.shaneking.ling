package sktest.ling.zero.lang;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.Boolean0;
import org.shaneking.ling.zero.lang.String0;

import static org.junit.jupiter.api.Assertions.*;

class Boolean0Test {

  @Test
  void checkArgument() {
    assertAll(
      () -> assertThrows(IllegalArgumentException.class, () -> Boolean0.checkArgument(false, null)),
      () -> assertDoesNotThrow(() -> Boolean0.checkArgument(true, null))
    );
  }

  @Test
  void checkState() {
    assertAll(
      () -> assertThrows(IllegalStateException.class, () -> Boolean0.checkState(false, null)),
      () -> assertDoesNotThrow(() -> Boolean0.checkState(true, null))
    );
  }

  @Test
  void falseTo() {
    assertTrue(Boolean0.falseTo(false, () -> true));
  }

  @Test
  void sf() {
    assertAll(
      () -> assertEquals(String0.F, Boolean0.sf(false)),
      () -> assertEquals(String0.S, Boolean0.sf(true)),
      () -> assertFalse(Boolean0.sf(null)),
      () -> assertFalse(Boolean0.sf(String0.F)),
      () -> assertTrue(Boolean0.sf(String0.S))
    );
  }

  @Test
  void tf() {
    assertAll(
      () -> assertEquals(String0.F, Boolean0.tf(false)),
      () -> assertEquals(String0.T, Boolean0.tf(true)),
      () -> assertFalse(Boolean0.tf(null)),
      () -> assertFalse(Boolean0.tf(String0.F)),
      () -> assertTrue(Boolean0.tf(String0.T))
    );
  }

  @Test
  void yn() {
    assertAll(
      () -> assertEquals(String0.N, Boolean0.yn(false)),
      () -> assertEquals(String0.Y, Boolean0.yn(true)),
      () -> assertFalse(Boolean0.yn(null)),
      () -> assertFalse(Boolean0.yn(String0.N)),
      () -> assertTrue(Boolean0.yn(String0.Y))
    );
  }
}
