package sktest.ling.rr;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.rr.RbkRespException;
import org.shaneking.ling.rr.Req;
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
    assertEquals("{\"msg\":{\"body\":{\"code\":\"-1\",\"info\":\"msg\",\"data\":{}}}}", OM3.writeValueAsString(Resp.build(null, Resp.CODE_UNKNOWN_EXCEPTION, Req.build(), "msg")));
  }

  @Test
  void failed() {
    assertAll(
      () -> assertEquals("{\"msg\":{\"body\":{\"code\":\"-1\"}}}", OM3.writeValueAsString(Resp.failed())),
      () -> assertEquals("{\"msg\":{\"body\":{\"code\":\"-1\"}}}", OM3.writeValueAsString(Resp.failed(null, Resp.CODE_UNKNOWN_EXCEPTION))),
      () -> assertEquals("{\"msg\":{\"body\":{\"code\":\"-1\",\"info\":\"msg\"}}}", OM3.writeValueAsString(Resp.failed(null, Resp.CODE_UNKNOWN_EXCEPTION, "msg"))),
      () -> assertEquals("{\"msg\":{\"body\":{\"code\":\"-1\",\"info\":\"msg\",\"data\":{}}}}", OM3.writeValueAsString(Resp.failed(null, Resp.CODE_UNKNOWN_EXCEPTION, "msg", Req.build())))
    );
  }

  @Test
  void parseExp() {
    assertAll(
      () -> {
        try {
          throw new RespException(Resp.failed());
        } catch (RespException e) {
          assertEquals("{\"msg\":{\"body\":{\"code\":\"org.shaneking.ling.rr.RespException\",\"info\":\"-1\"}},\"rbk\":false}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      },
      () -> {
        try {
          throw new ZeroException();
        } catch (ZeroException e) {
          assertEquals("{\"msg\":{\"body\":{\"code\":\"org.shaneking.ling.zero.lang.ZeroException\",\"info\":\"org.shaneking.ling.zero.lang.ZeroException\"}}}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      },
      () -> {
        try {
          throw new RespException(Resp.failed(null, String0.ALPHABET), new ZeroException());
        } catch (RespException e) {
          assertEquals("{\"msg\":{\"body\":{\"code\":\"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"info\":\"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\"}},\"rbk\":false}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      },
      () -> {
        try {
          throw new RbkRespException(Resp.failed(null, String0.ALPHABET), new ZeroException());
        } catch (RespException e) {
          assertEquals("{\"msg\":{\"body\":{\"code\":\"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"info\":\"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\"}},\"rbk\":true}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      },
      () -> {
        try {
          RbkRespException rbkRespException = new RbkRespException(Resp.failed(), new ZeroException());
          throw rbkRespException.setResp(null);
        } catch (RespException e) {
          assertEquals("{\"msg\":{\"body\":{\"code\":\"org.shaneking.ling.rr.RbkRespException\",\"info\":\"-1\"}},\"rbk\":true}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      },
      () -> {
        try {
          throw new RbkRespException(Resp.failed(Resp.CODE_UNKNOWN_EXCEPTION), new ZeroException());
        } catch (RespException e) {
          assertEquals("{\"msg\":{\"body\":{\"code\":\"org.shaneking.ling.rr.RbkRespException\",\"info\":\"-1\"}},\"rbk\":true}", OM3.writeValueAsString(Resp.failed().parseExp(e)));
        }
      }
    );
  }

  @Test
  void success() {
    assertEquals("{\"msg\":{\"body\":{\"code\":\"0\",\"data\":{}}}}", OM3.writeValueAsString(Resp.success(Req.build())));
  }
}
