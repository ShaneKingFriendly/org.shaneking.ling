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
  void constructor() {
    assertAll(
      () -> assertNotNull(new OM3())
    );
  }

  @Test
  void appendCtxIgnoredFilter() {
    assertAll(
      () -> assertNotNull(OM3.appendCtxIgnoredFilter(new ObjectMapper())),
      () -> assertNotNull(OM3.appendCtxIgnoredFilter(new ObjectMapper().setFilterProvider(new SimpleFilterProvider()))),
      () -> assertNotNull(OM3.appendCtxIgnoredFilter(new ObjectMapper().setFilterProvider(new FilterProvider() {
        @Override
        public BeanPropertyFilter findFilter(Object o) {
          return new CtxIgnoredFilter();
        }
      }))),
      () -> assertThrows(NullPointerException.class, () -> OM3.appendCtxIgnoredFilter(null))
    );
  }

  @Test
  void createObjectNode() {
    assertAll(
      () -> assertNotNull(OM3.createObjectNode()),

      () -> assertThrows(NullPointerException.class, () -> OM3.createObjectNode(null))
    );
  }

  @Test
  void lp() {
    assertAll(
      () -> assertEquals("{\"p\":[\"1\",2],\"l\":\"a\"}", OM3.lp("a", "1", 2))
    );
  }

  @Test
  void om() {
    ObjectMapper objectMapper = OM3.om();
    assertAll(
      () -> assertThrows(NullPointerException.class, () -> OM3.om(null)),
      () -> assertEquals(objectMapper, OM3.om(objectMapper))
    );
  }

  @Test
  void omWithCtx() {
    assertAll(
      () -> assertNotNull(OM3.omWithCtx()),

      () -> assertThrows(NullPointerException.class, () -> OM3.omWithCtx(null))
    );
  }

  @Test
  void p() {
    assertAll(
      () -> assertEquals("{\"p\":[\"a\",\"1\",2]}", OM3.p("a", "1", 2))
    );
  }

  @Test
  void readValue() {
    assertAll(
      () -> assertNull(OM3.readValue(Map0.newHashMap())),
      () -> assertNull(OM3.readValue(null)),

      () -> assertThrows(NullPointerException.class, () -> OM3.readValue(null, String0.EMPTY, String.class)),
      () -> assertEquals(new OM3PrepareReadValue().setStr("str").toString(), OM3.readValue(OM3.om(), "{\"str\":\"str\"}", OM3PrepareReadValue.class).toString()),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue(OM3.om(), "[\"str\":\"str\"}", OM3PrepareReadValue.class).toString()),

      () -> assertThrows(NullPointerException.class, () -> OM3.readValue(OM3.om(), "[\"str\":\"str\"}", OM3PrepareReadValue.class, true).toString()),
      () -> assertNull(OM3.readValue(OM3.om(), String0.EMPTY, String.class, true)),

      () -> assertThrows(NullPointerException.class, () -> OM3.readValue(null, String0.EMPTY, OM3.om().getTypeFactory().constructType(String.class))),
      () -> assertEquals(new OM3PrepareReadValue().setStr("str").toString(), OM3.readValue(OM3.om(), "{\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(OM3PrepareReadValue.class)).toString()),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue(OM3.om(), "[\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(OM3PrepareReadValue.class)).toString()),

      () -> assertThrows(NullPointerException.class, () -> OM3.readValue(OM3.om(), "[\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(OM3PrepareReadValue.class), true).toString()),
      () -> assertNull(OM3.readValue(OM3.om(), String0.EMPTY, OM3.om().getTypeFactory().constructType(String.class), true)),

      () -> assertThrows(NullPointerException.class, () -> OM3.readValue(null, String0.EMPTY, new TypeReference<String>() {
      })),
      () -> assertLinesMatch(List0.newArrayList("a", "1", "2"), OM3.readValue(OM3.om(), OM3.p("a", "1", "2"), new TypeReference<Map<String, List<String>>>() {
      }).get("p")),

      () -> assertNull(OM3.readValue(OM3.om(), String0.EMPTY, new TypeReference<String>() {
      }, true)),

      () -> assertEquals(new OM3PrepareReadValue().setStr("str").toString(), OM3.readValue("{\"str\":\"str\"}", OM3PrepareReadValue.class).toString()),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue("[\"str\":\"str\"}", OM3PrepareReadValue.class).toString()),

      () -> assertNull(OM3.readValue("[\"str\":\"str\"}", OM3PrepareReadValue.class, true)),

      () -> assertEquals(new OM3PrepareReadValue().setStr("str").toString(), OM3.readValue("{\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(OM3PrepareReadValue.class)).toString()),
      () -> assertThrows(ZeroException.class, () -> OM3.readValue("[\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(OM3PrepareReadValue.class)).toString()),

      () -> assertThrows(NullPointerException.class, () -> OM3.readValue("[\"str\":\"str\"}", OM3.om().getTypeFactory().constructType(OM3PrepareReadValue.class), true).toString()),

      () -> assertThrows(ZeroException.class, () -> OM3.readValue("[\"str\":\"str\"}", new TypeReference<Map<String, List<String>>>() {
      }).get("p")),
      () -> assertLinesMatch(List0.newArrayList("a", "1", "2"), OM3.readValue(OM3.p("a", "1", "2"), new TypeReference<Map<String, List<String>>>() {
      }).get("p")),

      () -> assertThrows(NullPointerException.class, () -> OM3.readValue("[\"str\":\"str\"}", new TypeReference<Map<String, List<String>>>() {
      }, true).get("p"))
    );
  }

  @Test
  void treeToValue() {
    ObjectNode objectNode = OM3.om().createObjectNode();
    objectNode.put("str", "str");
    assertAll(
      () -> assertEquals(new OM3PrepareReadValue().setStr("str").toString(), OM3.treeToValue(objectNode, OM3PrepareReadValue.class).toString()),

      () -> assertThrows(NullPointerException.class, () -> OM3.treeToValue(null, OM3.om().createObjectNode(), String.class)),

      () -> assertNull(OM3.treeToValue(OM3.om(), objectNode, List.class, true)),
      () -> assertThrows(ZeroException.class, () -> OM3.treeToValue(OM3.om(), objectNode, List.class, false)),
      () -> assertNull(OM3.treeToValue(OM3.om(), OM3.om().createObjectNode(), String.class, true))
    );
  }

  @Test
  void valueToTree() {
    assertAll(
      () -> assertEquals(OM3.writeValueAsString(new OM3PrepareReadValue().setStr("str")), OM3.writeValueAsString(OM3.valueToTree(new OM3PrepareReadValue().setStr("str")))),

      () -> assertThrows(NullPointerException.class, () -> OM3.valueToTree(null, null))
    );
  }

  @Test
  void writeValueAsString() {
    assertAll(
      () -> assertEquals("1", OM3.writeValueAsString(1)),
      () -> assertEquals("\"1\"", OM3.writeValueAsString("1")),
      () -> assertEquals(OM3.OBJECT_ERROR_STRING, OM3.writeValueAsString(Map0.newHashMap())),
      () -> assertEquals(Object0.NULL, OM3.writeValueAsString(null)),
      () -> assertThrows(ZeroException.class, () -> OM3.writeValueAsString(new OM3PrepareWriteValueAsString())),
      () -> assertEquals("[]", OM3.writeValueAsString(List0.newArrayList())),
      () -> assertEquals("\"java.lang.Object\"", OM3.writeValueAsString(Object.class)),
      () -> assertEquals("\"java.lang.String\"", OM3.writeValueAsString(String.class)),
      () -> assertEquals("\"[Ljava.lang.String;\"", OM3.writeValueAsString(String[].class)),

      () -> assertThrows(NullPointerException.class, () -> OM3.writeValueAsString(null, null)),
      () -> assertThrows(NullPointerException.class, () -> OM3.writeValueAsString(null, true)),

      () -> assertNull(OM3.writeValueAsString(OM3.om(), new OM3PrepareWriteValueAsString(), true))
    );
  }

  //must static for jackson
  @Accessors(chain = true)
  @ToString
  public static class OM3PrepareReadValue {
    @Getter
    @Setter
    private String str;
  }

  public static class OM3PrepareWriteValueAsString {
    private String str;
  }
}
