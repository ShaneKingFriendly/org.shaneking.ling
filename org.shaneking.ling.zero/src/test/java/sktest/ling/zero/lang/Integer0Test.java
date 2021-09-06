package sktest.ling.zero.lang;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.Integer0;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Integer0Test {

  @Test
  void gt2d() {
    assertAll(
      () -> assertEquals(1, Integer0.gt2d(1, 2)),
      () -> assertEquals(1, Integer0.gt2d(1, 2, 3)),
      () -> assertEquals(1, Integer0.gt2d(2, 1)),
      () -> assertEquals(2, Integer0.gt2d(2, 2)),
      () -> assertEquals(3, Integer0.gt2d(2, 1, 3)),
      () -> assertEquals(2, Integer0.gt2d(2, 2, 3))
    );
  }

  @Test
  void gte2d() {
    assertAll(
      () -> assertEquals(1, Integer0.gte2d(1, 2)),
      () -> assertEquals(1, Integer0.gte2d(1, 2, 3)),
      () -> assertEquals(1, Integer0.gte2d(2, 1)),
      () -> assertEquals(2, Integer0.gte2d(2, 2)),
      () -> assertEquals(3, Integer0.gte2d(2, 1, 3)),
      () -> assertEquals(3, Integer0.gte2d(2, 2, 3))
    );
  }

  @Test
  void lt2d() {
    assertAll(
      () -> assertEquals(2, Integer0.lt2d(1, 2)),
      () -> assertEquals(3, Integer0.lt2d(1, 2, 3)),
      () -> assertEquals(2, Integer0.lt2d(2, 1)),
      () -> assertEquals(2, Integer0.lt2d(2, 2)),
      () -> assertEquals(2, Integer0.lt2d(2, 1, 3)),
      () -> assertEquals(2, Integer0.lt2d(2, 2, 3))
    );
  }

  @Test
  void lte2d() {
    assertAll(
      () -> assertEquals(2, Integer0.lte2d(1, 2)),
      () -> assertEquals(3, Integer0.lte2d(1, 2, 3)),
      () -> assertEquals(2, Integer0.lte2d(2, 1)),
      () -> assertEquals(2, Integer0.lte2d(2, 2)),
      () -> assertEquals(2, Integer0.lte2d(2, 1, 3)),
      () -> assertEquals(3, Integer0.lte2d(2, 2, 3))
    );
  }

  @Test
  void null2Default() {
    assertAll(
      () -> assertEquals(1, Integer0.null2Default(1, 2)),
      () -> assertEquals(3, Integer0.null2Default(null, 3))
    );
  }

  @Test
  void null2Zero() {
    assertAll(
      () -> assertEquals(1, Integer0.null2Zero(1)),
      () -> assertEquals(0, Integer0.null2Zero(null))
    );
  }
}
