package sktest.ling.rr;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.rr.Resp;
import org.shaneking.ling.rr.RespException;
import org.shaneking.ling.zero.lang.ZeroException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RespTest {

  @Test
  void build() {
    assertEquals("{\"code\":\"-1\",\"data\":{},\"mesg\":\"mesg\"}", OM3.writeValueAsString(Resp.build(Resp.CODE_UNKNOWN_EXCEPTION, ReqTest.Test4Req.build(), "mesg")));
  }

  @Test
  void failed() {
    assertAll(
      () -> assertEquals("{\"code\":\"-1\"}", OM3.writeValueAsString(Resp.failed())),
      () -> assertEquals("{\"code\":\"-1\"}", OM3.writeValueAsString(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION))),
      () -> assertEquals("{\"code\":\"-1\",\"mesg\":\"mesg\"}", OM3.writeValueAsString(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION, "mesg"))),
      () -> assertEquals("{\"code\":\"-1\",\"data\":{},\"mesg\":\"mesg\"}", OM3.writeValueAsString(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION, "mesg", ReqTest.Test4Req.build())))
    );
  }

  @Test
  void success() {
    assertEquals("{\"code\":\"0\",\"data\":{}}", OM3.writeValueAsString(Resp.success(ReqTest.Test4Req.build())));
  }

  @Test
  void parseExp() {
    assertAll(
      () -> {
        try {
          throw new RespException(Resp.failed());
        } catch (RespException e) {
          assertEquals("{\"code\":\"org.shaneking.ling.rr.RespException\",\"mesg\":\"-1\"}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      },
      () -> {
        try {
          throw new ZeroException();
        } catch (ZeroException e) {
          assertEquals("{\"code\":\"org.shaneking.ling.zero.lang.ZeroException\",\"mesg\":\"org.shaneking.ling.zero.lang.ZeroException\"}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      }
    );
  }
}
