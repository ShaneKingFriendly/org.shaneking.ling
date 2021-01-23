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
      () -> assertEquals("Pri(ext=null, obj=null, rtn=null)", Test4Pri.build().toString()),
      () -> assertEquals("{}", OM3.writeValueAsString(Test4Pri.build())),
      () -> assertEquals("{\"rtn\":\"rtn\"}", OM3.writeValueAsString(Test4Pri.build("rtn"))),
      () -> assertEquals("{\"obj\":\"obj\",\"rtn\":\"rtn\"}", OM3.writeValueAsString(Test4Pri.build("rtn", "obj"))),
      () -> assertEquals("{\"ext\":{\"userId\":\"userId\"},\"obj\":\"obj\",\"rtn\":\"rtn\"}", OM3.writeValueAsString(Test4Pri.build("rtn", "obj", new Test4PriExt().setUserId("userId"))))
    );
  }

  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class Test4Pri<O, R> extends Pri<Test4PriExt, O, R> {
  }

  @Accessors(chain = true)
  @ToString
  public static class Test4PriExt {
    @Getter
    @Setter
    private String userId;
    //maybe some pagination here
  }
}
