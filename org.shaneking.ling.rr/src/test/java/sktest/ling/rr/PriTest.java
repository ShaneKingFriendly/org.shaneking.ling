package sktest.ling.rr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.rr.Pri;
import org.shaneking.ling.test.SKUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PriTest extends SKUnit {

  @Test
  void build() {
    assertAll(
      () -> assertEquals("Pri(ext=null, obj=null, rtn=null)", HelloPri.build().toString()),
      () -> assertEquals("{}", OM3.writeValueAsString(HelloPri.build())),
      () -> assertEquals("{\"rtn\":\"rtn\"}", OM3.writeValueAsString(HelloPri.build("rtn"))),
      () -> assertEquals("{\"obj\":\"obj\",\"rtn\":\"rtn\"}", OM3.writeValueAsString(HelloPri.build("rtn", "obj"))),
      () -> assertEquals("{\"ext\":{\"userNo\":\"userNo\"},\"obj\":\"obj\",\"rtn\":\"rtn\"}", OM3.writeValueAsString(HelloPri.build("rtn", "obj", new HelloPriExt().setUserNo("userNo"))))
    );
  }

  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class HelloPri<O, R> extends Pri<HelloPriExt, O, R> {
  }

  @Accessors(chain = true)
  @ToString
  public static class HelloPriExt {
    @Getter
    @Setter
    private String userNo;
    //maybe some pagination here
  }
}
