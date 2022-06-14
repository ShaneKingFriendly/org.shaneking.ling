package sktest.ling.rr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.rr.Req;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.util.UUID0;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReqTest extends SKUnit {

  @Test
  void build() {
    String cul33 = UUID0.cUl33();
    assertAll(
      () -> assertEquals("Req(cno=null, tkn=null, mvc=null, enc=null, msg=null, ctx=null)", ReqPrepare1.build().toString()),

      () -> assertEquals("{}", OM3.writeValueAsString(ReqPrepare1.build())),

      () -> assertEquals("{\"ctx\":{\"clientIp\":\"clientIp\",\"language\":\"zh_CN\"}}", OM3.writeValueAsString(ReqPrepare1.build().setCtx(new ReqCtxPrepare1().setClientIp("clientIp").setLanguage("zh_CN"))))
    );
  }

  @Accessors(chain = true)
  @ToString
  public static class ReqCtxPrepare1 {
    @Getter
    @Setter
    private String clientIp;
    @Getter
    @Setter
    private String language;//default zh-CN, ref: http://www.rfc-editor.org/rfc/bcp/bcp47.txt
    //maybe some UserEntity here
  }

  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class ReqPrepare1<I> extends Req<I, ReqCtxPrepare1> {

  }
}
