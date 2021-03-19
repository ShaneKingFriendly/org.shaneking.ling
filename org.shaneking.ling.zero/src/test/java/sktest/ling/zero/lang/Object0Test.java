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
    ZzzObject zzz = new ZzzObject();
    assertEquals(String0.T, String.valueOf(Object0.gs(zzz, "world.hello.str")));
    assertTrue(Object0.gs(zzz, "world.hello.str", String0.F) instanceof ZzzObject);
    assertEquals(String0.F, String.valueOf(Object0.gs(zzz, "world.hello.str")));

    assertEquals(Object0.EXCEPTION, Object0.gs(zzz, "world.hello.abc"));
    assertThrows(ZeroException.class, () -> Object0.gs(zzz, "world.hello.abc", false));
    assertEquals(Object0.EXCEPTION, Object0.gs(zzz, "world.hello.abc", String0.F, true));
    assertThrows(ZeroException.class, () -> Object0.gs(zzz, "world.hello.abc", String0.F, false));
  }

  //must be public classes
  public class HelloObject {
    @Getter
    @Setter
    private String str = String0.T;
  }

  public class WorldObject {
    @Getter
    @Setter
    private HelloObject hello = new HelloObject();
  }

  public class ZzzObject {
    @Getter
    @Setter
    private WorldObject world = new WorldObject();
  }
}
