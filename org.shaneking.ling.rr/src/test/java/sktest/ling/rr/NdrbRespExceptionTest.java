package sktest.ling.rr;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.rr.NdrbRespException;
import org.shaneking.ling.rr.Resp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NdrbRespExceptionTest {

  @Test
  void test() {
    assertAll(
      () -> assertThrows(NdrbRespException.class, () -> {
        throw new NdrbRespException(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION));
      }),
      () -> assertThrows(NdrbRespException.class, () -> {
        throw new NdrbRespException(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION), "Message");
      }),
      () -> assertThrows(NdrbRespException.class, () -> {
        throw new NdrbRespException(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION), "Message", new RuntimeException());
      }),
      () -> assertThrows(NdrbRespException.class, () -> {
        throw new NdrbRespException(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION), new RuntimeException());
      })
    );
  }
}
