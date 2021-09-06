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
  void gs() {
    Object0Prepare3 object0Prepare3 = new Object0Prepare3();
    assertEquals(String0.T, String.valueOf(Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare.str")));
    assertTrue(Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare.str", String0.F) instanceof Object0Prepare3);
    assertEquals(String0.F, String.valueOf(Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare.str")));

    assertEquals(Object0.EXCEPTION, Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare.abc"));
    assertThrows(ZeroException.class, () -> Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare.abc", false));
    assertEquals(Object0.EXCEPTION, Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare.abc", String0.F, true));
    assertThrows(ZeroException.class, () -> Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare.abc", String0.F, false));
  }

  //must be public classes
  public class Object0Prepare {
    @Getter
    @Setter
    private String str = String0.T;
  }

  public class Object0Prepare2 {
    @Getter
    @Setter
    private Object0Prepare object0Prepare = new Object0Prepare();
  }

  public class Object0Prepare3 {
    @Getter
    @Setter
    private Object0Prepare2 object0Prepare2 = new Object0Prepare2();
  }
}
