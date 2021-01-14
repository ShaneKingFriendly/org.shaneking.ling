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
    TestObject3 to3 = new TestObject3();
    assertEquals(String0.T, String.valueOf(Object0.gs(to3, "to2.to1.str")));
    assertTrue(Object0.gs(to3, "to2.to1.str", String0.F) instanceof TestObject3);
    assertEquals(String0.F, String.valueOf(Object0.gs(to3, "to2.to1.str")));

    assertEquals(Object0.EXCEPTION, Object0.gs(to3, "to2.to1.abc"));
    assertEquals(Object0.EXCEPTION, Object0.gs(to3, "to2.to1.abc", String0.F));
  }

  //must be public classes
  public class TestObject1 {
    @Getter
    @Setter
    private String str = String0.T;
  }

  public class TestObject2 {
    @Getter
    @Setter
    private TestObject1 to1 = new TestObject1();
  }

  public class TestObject3 {
    @Getter
    @Setter
    private TestObject2 to2 = new TestObject2();
  }
}
