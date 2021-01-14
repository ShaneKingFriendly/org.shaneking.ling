package sktest.ling.zero.lang;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.ZeroException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ZeroExceptionTest {

  @Test
  void test() {
    assertAll(
      () -> assertThrows(ZeroException.class, () -> {
        throw new ZeroException();
      }),
      () -> assertThrows(ZeroException.class, () -> {
        throw new ZeroException("Message");
      }),
      () -> assertThrows(ZeroException.class, () -> {
        throw new ZeroException("Message", new RuntimeException());
      }),
      () -> assertThrows(ZeroException.class, () -> {
        throw new ZeroException(new RuntimeException());
      })
    );
  }
}
