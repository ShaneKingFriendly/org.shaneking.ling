package sktest.ling.jackson.databind;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.Object0;
import org.shaneking.ling.zero.lang.ZeroException;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OM3Test extends SKUnit {

  @Test
  void appendCtxIgnoredFilter() {
    assertAll(
      () -> assertNotNull(OM3.appendCtxIgnoredFilter(new ObjectMapper())),
      () -> assertNotNull(OM3.appendCtxIgnoredFilter(new ObjectMapper().setFilterProvider(new SimpleFilterProvider())))
    );
  }

  @Test
  void createObjectNode() {
    assertNotNull(OM3.createObjectNode());
  }

  @Test
  void lp() {
    assertEquals("{\"p\":[\"1\",2],\"l\":\"a\"}", OM3.lp("a", "1", 2));
  }

  @Test
  void om() {
    ObjectMapper objectMapper = new ObjectMapper();
    assertEquals(objectMapper, OM3.om(objectMapper));
  }

  @Test
  void omWithCtx() {
    assertNotNull(OM3.omWithCtx());
  }

  @Test
  void p() {
    assertEquals("{\"p\":[\"a\",\"1\",2]}", OM3.p("a", "1", 2));
  }

  @Test
  void readValue() {
    assertAll(
      () -> assertEquals(new Test4ReadValue().setStr("str").toString(), OM3.readValue("{\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(Test4ReadValue.class)).toString()),
      () -> assertEquals(new Test4ReadValue().setStr("str").toString(), OM3.readValue(OM3.om(), "{\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(Test4ReadValue.class)).toString()),
      () -> assertEquals(new Test4ReadValue().setStr("str").toString(), OM3.readValue("{\"str\":\"str\"}", Test4ReadValue.class).toString()),
      () -> assertEquals(new Test4ReadValue().setStr("str").toString(), OM3.readValue(OM3.om(), "{\"str\":\"str\"}", Test4ReadValue.class).toString()),
      () -> assertLinesMatch(List0.newArrayList("a", "1", "2"), OM3.readValue(OM3.p("a", "1", "2"), new TypeReference<Map<String, List<String>>>() {
      }).get("p")),
      () -> assertLinesMatch(List0.newArrayList("a", "1", "2"), OM3.readValue(OM3.om(), OM3.p("a", "1", "2"), new TypeReference<Map<String, List<String>>>() {
      }).get("p")),

      () -> assertThrows(ZeroException.class, () -> OM3.readValue("[\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(Test4ReadValue.class)).toString()),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue(OM3.om(), "[\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(Test4ReadValue.class)).toString()),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue("[\"str\":\"str\"}", Test4ReadValue.class).toString()),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue(OM3.om(), "[\"str\":\"str\"}", Test4ReadValue.class).toString()),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue("[\"str\":\"str\"}", new TypeReference<Map<String, List<String>>>() {
      }).get("p")),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue("[\"str\":\"str\"}", Test4ReadValue.class).toString()),

      () -> assertThrows(NullPointerException.class, () -> OM3.readValue("[\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(Test4ReadValue.class), true).toString()),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue(OM3.om(), "[\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(Test4ReadValue.class), true).toString()),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue("[\"str\":\"str\"}", Test4ReadValue.class, true).toString()),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue(OM3.om(), "[\"str\":\"str\"}", Test4ReadValue.class, true).toString()),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue("[\"str\":\"str\"}", new TypeReference<Map<String, List<String>>>() {
      }, true).get("p")),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue("[\"str\":\"str\"}", Test4ReadValue.class, true).toString())
    );
  }

  @Test
  void treeToValue() {
    ObjectNode objectNode = OM3.om().createObjectNode();
    objectNode.put("str", "str");
    assertEquals(new Test4ReadValue().setStr("str").toString(), OM3.treeToValue(objectNode, Test4ReadValue.class).toString());
  }

  @Test
  void valueToTree() {
    assertEquals(OM3.writeValueAsString(new Test4ReadValue().setStr("str")), OM3.writeValueAsString(OM3.valueToTree(new Test4ReadValue().setStr("str"))));
  }

  @Test
  void writeValueAsString() {
    assertAll(
      () -> assertEquals(Object0.NULL, OM3.writeValueAsString(null)),
      () -> assertEquals(OM3.OBJECT_ERROR_STRING, OM3.writeValueAsString(Map0.newHashMap())),
      () -> assertEquals("[]", OM3.writeValueAsString(List0.newArrayList()))
    );
  }

  //must static for jackson
  @Accessors(chain = true)
  @ToString
  public static class Test4ReadValue {
    @Getter
    @Setter
    private String str;
  }
}
