package sktest.ling.jackson.databind;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.jackson.filter.CtxIgnoredFilter;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.Object0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OM3Test extends SKUnit {

  @Test
  void aaa() {
    assertAll(
      () -> assertThrows(NullPointerException.class, () -> OM3.appendCtxIgnoredFilter(null)),
      () -> assertNotNull(OM3.appendCtxIgnoredFilter(new ObjectMapper().setFilterProvider(new FilterProvider() {
        @Override
        public BeanPropertyFilter findFilter(Object o) {
          return new CtxIgnoredFilter();
        }
      }))),
      () -> assertNotNull(new OM3()),
      () -> assertThrows(NullPointerException.class, () -> OM3.createObjectNode(null)),
      () -> assertThrows(NullPointerException.class, () -> OM3.om(null)),
      () -> assertThrows(NullPointerException.class, () -> OM3.omWithCtx(null)),
      () -> assertNull(OM3.readValue(null)),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue(null, String0.EMPTY, OM3.om().getTypeFactory().constructType(String.class))),
      () -> assertNull(OM3.readValue(OM3.om(), String0.EMPTY, OM3.om().getTypeFactory().constructType(String.class), true)),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue(null, String0.EMPTY, String.class)),
      () -> assertNull(OM3.readValue(OM3.om(), String0.EMPTY, String.class, true)),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue(null, String0.EMPTY, new TypeReference<String>() {
      })),
      () -> assertNull(OM3.readValue(OM3.om(), String0.EMPTY, new TypeReference<String>() {
      }, true)),
      () -> assertThrows(NullPointerException.class, () -> OM3.treeToValue(null, OM3.om().createObjectNode(), String.class)),
      () -> assertNull(OM3.treeToValue(OM3.om(), OM3.om().createObjectNode(), String.class, true)),
      () -> assertThrows(NullPointerException.class, () -> OM3.valueToTree(null, null)),
      () -> assertThrows(NullPointerException.class, () -> OM3.writeValueAsString(null, null)),
      () -> assertThrows(NullPointerException.class, () -> OM3.writeValueAsString(null, true)),
      () -> assertNotNull(String0.DOT)
    );
  }

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
      () -> assertEquals(new HelloReadValue().setStr("str").toString(), OM3.readValue("{\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(HelloReadValue.class)).toString()),
      () -> assertEquals(new HelloReadValue().setStr("str").toString(), OM3.readValue(OM3.om(), "{\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(HelloReadValue.class)).toString()),
      () -> assertEquals(new HelloReadValue().setStr("str").toString(), OM3.readValue("{\"str\":\"str\"}", HelloReadValue.class).toString()),
      () -> assertEquals(new HelloReadValue().setStr("str").toString(), OM3.readValue(OM3.om(), "{\"str\":\"str\"}", HelloReadValue.class).toString()),
      () -> assertLinesMatch(List0.newArrayList("a", "1", "2"), OM3.readValue(OM3.p("a", "1", "2"), new TypeReference<Map<String, List<String>>>() {
      }).get("p")),
      () -> assertLinesMatch(List0.newArrayList("a", "1", "2"), OM3.readValue(OM3.om(), OM3.p("a", "1", "2"), new TypeReference<Map<String, List<String>>>() {
      }).get("p")),

      () -> assertThrows(ZeroException.class, () -> OM3.readValue("[\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(HelloReadValue.class)).toString()),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue(OM3.om(), "[\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(HelloReadValue.class)).toString()),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue("[\"str\":\"str\"}", HelloReadValue.class).toString()),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue(OM3.om(), "[\"str\":\"str\"}", HelloReadValue.class).toString()),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue("[\"str\":\"str\"}", new TypeReference<Map<String, List<String>>>() {
      }).get("p")),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue("[\"str\":\"str\"}", HelloReadValue.class).toString()),

      () -> assertThrows(NullPointerException.class, () -> OM3.readValue("[\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(HelloReadValue.class), true).toString()),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue(OM3.om(), "[\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(HelloReadValue.class), true).toString()),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue("[\"str\":\"str\"}", HelloReadValue.class, true).toString()),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue(OM3.om(), "[\"str\":\"str\"}", HelloReadValue.class, true).toString()),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue("[\"str\":\"str\"}", new TypeReference<Map<String, List<String>>>() {
      }, true).get("p")),
      () -> assertThrows(NullPointerException.class, () -> OM3.readValue("[\"str\":\"str\"}", HelloReadValue.class, true).toString())
    );
  }

  @Test
  void treeToValue() {
    ObjectNode objectNode = OM3.om().createObjectNode();
    objectNode.put("str", "str");
    assertAll(
      () -> assertEquals(new HelloReadValue().setStr("str").toString(), OM3.treeToValue(objectNode, HelloReadValue.class).toString()),
      () -> assertNull(OM3.treeToValue(OM3.om(), objectNode, List.class, true)),
      () -> assertThrows(ZeroException.class, () -> OM3.treeToValue(OM3.om(), objectNode, List.class, false))
    );
  }

  @Test
  void valueToTree() {
    assertEquals(OM3.writeValueAsString(new HelloReadValue().setStr("str")), OM3.writeValueAsString(OM3.valueToTree(new HelloReadValue().setStr("str"))));
  }

  @Test
  void writeValueAsString() {
    assertAll(
      () -> assertEquals(Object0.NULL, OM3.writeValueAsString(null)),
      () -> assertEquals(OM3.OBJECT_ERROR_STRING, OM3.writeValueAsString(Map0.newHashMap())),
      () -> assertEquals("[]", OM3.writeValueAsString(List0.newArrayList())),
      () -> assertEquals("\"java.lang.Object\"", OM3.writeValueAsString(Object.class)),
      () -> assertEquals("\"java.lang.String\"", OM3.writeValueAsString(String.class)),
//      () -> assertNull(OM3.writeValueAsString(OM3.om(), null, true)),
//      () -> assertThrows(ZeroException.class, () -> OM3.writeValueAsString(OM3.om(), null, false)),
      () -> assertNotNull(String0.DOT)
    );
  }

  //must static for jackson
  @Accessors(chain = true)
  @ToString
  public static class HelloReadValue {
    @Getter
    @Setter
    private String str;
  }
}
