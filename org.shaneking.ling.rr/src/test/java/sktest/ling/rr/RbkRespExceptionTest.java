package sktest.ling.rr;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.rr.RbkRespException;
import org.shaneking.ling.rr.Resp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RbkRespExceptionTest {

  @Test
  void test() {
    assertAll(
      () -> assertThrows(RbkRespException.class, () -> {
        throw new RbkRespException(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION));
      }),
      () -> assertThrows(RbkRespException.class, () -> {
        throw new RbkRespException(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION), "Message");
      }),
      () -> assertThrows(RbkRespException.class, () -> {
        throw new RbkRespException(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION), "Message", new RuntimeException());
      }),
      () -> assertThrows(RbkRespException.class, () -> {
        throw new RbkRespException(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION), new RuntimeException());
      })
    );
  }
}
