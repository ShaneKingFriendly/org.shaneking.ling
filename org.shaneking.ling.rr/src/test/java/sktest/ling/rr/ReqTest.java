package sktest.ling.rr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.rr.Req;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.UUID0;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReqTest {

  @Test
  void build() {
    String cul33 = UUID0.cUl33();
    assertAll(
      () -> assertEquals("{}", OM3.writeValueAsString(Test4Req.build())),

      () -> assertEquals("{\"pri\":{}}", OM3.writeValueAsString(Test4Req.build(PriTest.Test4Pri.build()))),
      () -> assertEquals("{\"pri\":{\"rtn\":\"rtn\"}}", OM3.writeValueAsString(Test4Req.build(PriTest.Test4Pri.build("rtn")))),
      () -> assertEquals("{\"pri\":{\"obj\":\"obj\",\"rtn\":\"rtn\"}}", OM3.writeValueAsString(Test4Req.build(PriTest.Test4Pri.build("rtn", "obj")))),
      () -> assertEquals("{\"pri\":{\"ext\":{\"userId\":\"userId\"},\"obj\":\"obj\",\"rtn\":\"rtn\"}}", OM3.writeValueAsString(Test4Req.build(PriTest.Test4Pri.build("rtn", "obj", new PriTest.Test4PriExt().setUserId("userId"))))),

      () -> assertEquals("{\"pub\":{\"channelName\":\"channelName\",\"encoded\":\"N\",\"tenantName\":\"tenantName\",\"tracingId\":\"" + cul33 + "\"}}", OM3.writeValueAsString(Test4Req.build(new Test4ReqPub().setChannelName("channelName").setEncoded(String0.N).setTenantName("tenantName").setTracingId(cul33)))),

      () -> assertEquals("{\"pri\":{},\"pub\":{\"channelName\":\"channelName\",\"encoded\":\"N\",\"tenantName\":\"tenantName\",\"tracingId\":\"" + cul33 + "\"}}", OM3.writeValueAsString(Test4Req.build(new Test4ReqPub().setChannelName("channelName").setEncoded(String0.N).setTenantName("tenantName").setTracingId(cul33), PriTest.Test4Pri.build()))),
      () -> assertEquals("{\"pri\":{\"rtn\":\"rtn\"},\"pub\":{\"channelName\":\"channelName\",\"encoded\":\"N\",\"tenantName\":\"tenantName\",\"tracingId\":\"" + cul33 + "\"}}", OM3.writeValueAsString(Test4Req.build(new Test4ReqPub().setChannelName("channelName").setEncoded(String0.N).setTenantName("tenantName").setTracingId(cul33), PriTest.Test4Pri.build("rtn")))),
      () -> assertEquals("{\"pri\":{\"obj\":\"obj\",\"rtn\":\"rtn\"},\"pub\":{\"channelName\":\"channelName\",\"encoded\":\"N\",\"tenantName\":\"tenantName\",\"tracingId\":\"" + cul33 + "\"}}", OM3.writeValueAsString(Test4Req.build(new Test4ReqPub().setChannelName("channelName").setEncoded(String0.N).setTenantName("tenantName").setTracingId(cul33), PriTest.Test4Pri.build("rtn", "obj")))),
      () -> assertEquals("{\"pri\":{\"ext\":{\"userId\":\"userId\"},\"obj\":\"obj\",\"rtn\":\"rtn\"},\"pub\":{\"channelName\":\"channelName\",\"encoded\":\"N\",\"tenantName\":\"tenantName\",\"tracingId\":\"" + cul33 + "\"}}", OM3.writeValueAsString(Test4Req.build(new Test4ReqPub().setChannelName("channelName").setEncoded(String0.N).setTenantName("tenantName").setTracingId(cul33), PriTest.Test4Pri.build("rtn", "obj", new PriTest.Test4PriExt().setUserId("userId"))))),

      () -> assertEquals("{\"enc\":\"enc\",\"pub\":{\"channelName\":\"channelName\",\"encoded\":\"N\",\"tenantName\":\"tenantName\",\"tracingId\":\"" + cul33 + "\"}}", OM3.writeValueAsString(Test4Req.build(new Test4ReqPub().setChannelName("channelName").setEncoded(String0.N).setTenantName("tenantName").setTracingId(cul33), "enc"))),

      () -> assertEquals("{\"ctx\":{\"clientIp\":\"clientIp\",\"language\":\"zh_CN\"}}", OM3.writeValueAsString(Test4Req.build().setCtx(new Test4ReqCtx().setClientIp("clientIp").setLanguage("zh_CN"))))
    );
  }

  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class Test4Req<O, R> extends Req<Test4ReqCtx, PriTest.Test4PriExt, O, R, Test4ReqPub> {

  }

  @Accessors(chain = true)
  @ToString
  public static class Test4ReqCtx {
    @Getter
    @Setter
    private String clientIp;
    @Getter
    @Setter
    private String language;//default zh-CN, ref: http://www.rfc-editor.org/rfc/bcp/bcp47.txt
    //maybe some UserEntity here
  }

  @Accessors(chain = true)
  @ToString
  public static class Test4ReqPub {
    @Getter
    @Setter
    private String channelName;
    @Getter
    @Setter
    private String encoded;//Y|N(default)
    @Getter
    @Setter
    private String tenantName;//if null same as channelName
    @Getter
    @Setter
    private String tracingId;
    //some other open properties
  }
}
