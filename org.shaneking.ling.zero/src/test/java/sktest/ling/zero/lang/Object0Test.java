package sktest.ling.zero.lang;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.Object0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Object0Test {

  @Test
  void format() {
    assertEquals("I Love You", Object0.format("{a.1} {a.2} {a.3}"
      , Map0.newHashMap("a", Map0.newHashMap(List0.newArrayList("1", "2", "3")
        , List0.newArrayList("I", "Love", "You")))));
    assertEquals("[0][1][2][3][4][5][6][7][8][9][10][11][12][13]"
      , Object0.format("[{a.0}][{a.1}][{a.2}][{a.3}][{a.4}][{a.5}][{a.6}][{a.7}][{a.8}][{a.9}][{a.10}][{a.11}][{a.12}][{a.13}]"
        , Map0.newHashMap("a", Map0.newHashMap(List0.newArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13")
          , List0.newArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13")))));
  }

  @Test
  void gs() {
    Object0Prepare3 object0Prepare3 = new Object0Prepare3();
    assertEquals(String0.T, String.valueOf(Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.str")));
    assertTrue(Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.str", String0.F) instanceof Object0Prepare3);
    assertEquals(String0.F, String.valueOf(Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.str")));

    assertArrayEquals(new int[]{1, 2, 3}, (int[]) Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.a1"));
    assertTrue(Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.a1[1]", 4) instanceof Object0Prepare3);
    assertArrayEquals(new int[]{1, 4, 3}, (int[]) Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.a1"));

    assertArrayEquals(new String[]{"a", "b", "c"}, (String[]) Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.a2"));
    assertTrue(Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.a2[1]", "d") instanceof Object0Prepare3);
    assertArrayEquals(new String[]{"a", "d", "c"}, (String[]) Object0.gs(object0Prepare3, "object0Prepare2.object0Prepare1.a2"));

    assertEquals(String0.T, String.valueOf(Object0.gs(object0Prepare3, "mapString.b")));
    assertTrue(Object0.gs(object0Prepare3, "mapString.b", String0.F) instanceof Object0Prepare3);
    assertEquals(String0.F, String.valueOf(Object0.gs(object0Prepare3, "mapString.b")));

    assertEquals(String0.T, String.valueOf(Object0.gs(object0Prepare3, "mapObject.b.list[1].str")));
    assertTrue(Object0.gs(object0Prepare3, "mapObject.b.list[1].str", String0.F) instanceof Object0Prepare3);
    assertEquals(String0.F, String.valueOf(Object0.gs(object0Prepare3, "mapObject.b.list[1].str")));

    assertEquals(String0.T, String.valueOf(Object0.gs(object0Prepare3, "mapList.b[1].list[1].str")));
    assertTrue(Object0.gs(object0Prepare3, "mapList.b[1].list[1].str", String0.F) instanceof Object0Prepare3);
    assertEquals(String0.F, String.valueOf(Object0.gs(object0Prepare3, "mapList.b[1].list[1].str")));
  }

  @Test
  void exceptions() {
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
    @Getter
    @Setter
    private int[] a1 = new int[]{1, 2, 3};
    @Getter
    @Setter
    private String[] a2 = new String[]{"a", "b", "c"};
  }

  public class Object0Prepare2 {
    @Getter
    @Setter
    private Object0Prepare1 object0Prepare1 = new Object0Prepare1();
    @Getter
    @Setter
    private List<Object0Prepare1> list = List0.newArrayList(new Object0Prepare1(), new Object0Prepare1(), new Object0Prepare1());
    @Getter
    @Setter
    private Object0Prepare1[] array = new Object0Prepare1[]{new Object0Prepare1(), new Object0Prepare1(), new Object0Prepare1()};
  }

  public class Object0Prepare3 {
    @Getter
    @Setter
    private Object0Prepare2 object0Prepare2 = new Object0Prepare2();
    @Getter
    @Setter
    private Map<String, String> mapString = Map0.newHashMap(List0.newArrayList("a", "b", "c"), List0.newArrayList(String0.T, String0.T, String0.T));
    @Getter
    @Setter
    private Map<String, Object0Prepare2> mapObject = Map0.newHashMap(List0.newArrayList("a", "b", "c"), List0.nCopies(3, Object0Prepare2::new));
    @Getter
    @Setter
    private Map<String, List<Object0Prepare2>> mapList = Map0.newHashMap(List0.newArrayList("a", "b", "c"), List0.nCopies(3, () -> List0.newArrayList(new Object0Prepare2(), new Object0Prepare2(), new Object0Prepare2())));
  }
}
