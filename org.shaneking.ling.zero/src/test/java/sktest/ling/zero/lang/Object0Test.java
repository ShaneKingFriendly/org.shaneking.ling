package sktest.ling.zero.lang;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.Object0;
import org.shaneking.ling.zero.lang.String0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Object0Test {

  @Test
  void gs() {
    Test4Object3 to3 = new Test4Object3();
    assertEquals(String0.T, String.valueOf(Object0.gs(to3, "to2.to1.str")));
    assertTrue(Object0.gs(to3, "to2.to1.str", String0.F) instanceof Test4Object3);
    assertEquals(String0.F, String.valueOf(Object0.gs(to3, "to2.to1.str")));

    assertEquals(Object0.EXCEPTION, Object0.gs(to3, "to2.to1.abc"));
    assertEquals(Object0.EXCEPTION, Object0.gs(to3, "to2.to1.abc", String0.F));
  }

  //must be public classes
  public class Test4Object1 {
    @Getter
    @Setter
    private String str = String0.T;
  }

  public class Test4Object2 {
    @Getter
    @Setter
    private Test4Object1 to1 = new Test4Object1();
  }

  public class Test4Object3 {
    @Getter
    @Setter
    private Test4Object2 to2 = new Test4Object2();
  }
}
