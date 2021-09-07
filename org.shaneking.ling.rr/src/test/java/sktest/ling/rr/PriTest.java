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
      () -> assertEquals("Pri(ext=null, obj=null, rtn=null)", PriPrepare1.build().toString()),
      () -> assertEquals("{}", OM3.writeValueAsString(PriPrepare1.build())),
      () -> assertEquals("{\"rtn\":\"rtn\"}", OM3.writeValueAsString(PriPrepare1.build("rtn"))),
      () -> assertEquals("{\"obj\":\"obj\",\"rtn\":\"rtn\"}", OM3.writeValueAsString(PriPrepare1.build("rtn", "obj"))),
      () -> assertEquals("{\"ext\":{\"userNo\":\"userNo\"},\"obj\":\"obj\",\"rtn\":\"rtn\"}", OM3.writeValueAsString(PriPrepare1.build("rtn", "obj", new PriExtPrepare1().setUserNo("userNo"))))
    );
  }

  @Accessors(chain = true)
  @ToString
  public static class PriExtPrepare1 {
    @Getter
    @Setter
    private String userNo;
    //maybe some pagination here
  }

  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class PriPrepare1<O, R> extends Pri<PriExtPrepare1, O, R> {
  }
}
