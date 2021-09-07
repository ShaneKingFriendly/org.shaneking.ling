package sktest.ling.zero.lang;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.Object0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;

import static org.junit.jupiter.api.Assertions.*;

class Object0Test {

  @Test
  void scenario1() {
    Object0Prepare3 object0Prepare3 = new Object0Prepare3();
    assertEquals(String0.T, String.valueOf(Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.str")));
    assertTrue(Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.str", String0.F) instanceof Object0Prepare3);
    assertEquals(String0.F, String.valueOf(Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.str")));
  }

  @Test
  void gs() {
    Object0Prepare3 object0Prepare3 = new Object0Prepare3();
    assertAll(
      () -> assertEquals(Object0.EXCEPTION, Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.abc")),

      () -> assertThrows(ZeroException.class, () -> Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.abc", false)),

      () -> assertThrows(ZeroException.class, () -> Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.abc", String0.F, false)),
      () -> assertEquals(Object0.EXCEPTION, Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.abc", String0.F, true))
    );
  }

  //must be public classes
  public class Object0Prepare1 {
    @Getter
    @Setter
    private String str = String0.T;
  }

  public class Object0Prepare2 {
    @Getter
    @Setter
    private Object0Prepare1 object0Prepare1 = new Object0Prepare1();
  }

  public class Object0Prepare3 {
    @Getter
    @Setter
    private Object0Prepare2 object0Prepare2 = new Object0Prepare2();
  }
}
