package sktest.ling.rr;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.rr.RbkRespException;
import org.shaneking.ling.rr.Resp;
import org.shaneking.ling.rr.RespException;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RespTest extends SKUnit {

  @Test
  void build() {
    assertEquals("{\"code\":\"-1\",\"data\":{},\"msg\":\"msg\"}", OM3.writeValueAsString(Resp.build(Resp.CODE_UNKNOWN_EXCEPTION, ReqTest.HelloReq.build(), "msg")));
  }

  @Test
  void failed() {
    assertAll(
      () -> assertEquals("{\"code\":\"-1\"}", OM3.writeValueAsString(Resp.failed())),
      () -> assertEquals("{\"code\":\"-1\"}", OM3.writeValueAsString(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION))),
      () -> assertEquals("{\"code\":\"-1\",\"msg\":\"msg\"}", OM3.writeValueAsString(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION, "msg"))),
      () -> assertEquals("{\"code\":\"-1\",\"data\":{},\"msg\":\"msg\"}", OM3.writeValueAsString(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION, "msg", ReqTest.HelloReq.build())))
    );
  }

  @Test
  void success() {
    assertEquals("{\"code\":\"0\",\"data\":{}}", OM3.writeValueAsString(Resp.success(ReqTest.HelloReq.build())));
  }

  @Test
  void parseExp() {
    assertAll(
      () -> {
        try {
          throw new RespException(Resp.failed());
        } catch (RespException e) {
          assertEquals("{\"code\":\"org.shaneking.ling.rr.RespException\",\"msg\":\"-1\",\"rbk\":false}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      },
      () -> {
        try {
          throw new ZeroException();
        } catch (ZeroException e) {
          assertEquals("{\"code\":\"org.shaneking.ling.zero.lang.ZeroException\",\"msg\":\"org.shaneking.ling.zero.lang.ZeroException\"}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      },
      () -> {
        try {
          throw new RespException(Resp.failed(String0.ALPHABET), new ZeroException());
        } catch (RespException e) {
          assertEquals("{\"code\":\"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"msg\":\"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"rbk\":false}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      },
      () -> {
        try {
          throw new RbkRespException(Resp.failed(String0.ALPHABET), new ZeroException());
        } catch (RespException e) {
          assertEquals("{\"code\":\"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"msg\":\"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"rbk\":true}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      },
      () -> {
        try {
          RbkRespException rbkRespException = new RbkRespException(Resp.failed(), new ZeroException());
          throw rbkRespException.setResp(null);
        } catch (RespException e) {
          assertEquals("{\"code\":\"org.shaneking.ling.rr.RbkRespException\",\"msg\":\"-1\",\"rbk\":true}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      },
      () -> {
        try {
          throw new RbkRespException(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION), new ZeroException());
        } catch (RespException e) {
          assertEquals("{\"code\":\"org.shaneking.ling.rr.RbkRespException\",\"msg\":\"-1\",\"rbk\":true}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      }
    );
  }
}
