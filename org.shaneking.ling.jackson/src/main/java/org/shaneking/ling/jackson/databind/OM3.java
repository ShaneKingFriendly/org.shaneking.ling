package org.shaneking.ling.jackson.databind;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.jackson.filter.CtxIgnoredFilter;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;
import org.shaneking.ling.zero.util.Regex0;

import java.util.Map;

@Slf4j
public class OM3 {
  public static final String OBJECT_EMPTY_STRING = "{}";

  private static ObjectMapper OM = null;

  /*
  ThreadLocal is not perfect in spring mvc env.
   */
  @Deprecated
  public static ObjectMapper appendCtxIgnoredFilter(@NonNull ObjectMapper objectMapper) {
    FilterProvider filterProvider = objectMapper.getSerializationConfig().getFilterProvider();
    if (filterProvider == null) {
      objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter(CtxIgnoredFilter.FILTER_NAME, new CtxIgnoredFilter()));
    } else if (filterProvider instanceof SimpleFilterProvider) {
      ((SimpleFilterProvider) filterProvider).addFilter(CtxIgnoredFilter.FILTER_NAME, new CtxIgnoredFilter());
    }
    return objectMapper;
  }

  public static ObjectNode createObjectNode() {
    return createObjectNode(om());
  }

  public static ObjectNode createObjectNode(@NonNull ObjectMapper objectMapper) {
    return objectMapper.createObjectNode();
  }

  //last status or result, parameters
  public static String lp(Object object, Object... objects) {
    Map<String, Object> rtnMap = Map0.newHashMap();
    rtnMap.put("l", object);
    rtnMap.put("p", List0.newArrayList(objects));
    return writeValueAsString(rtnMap);
  }

  public static ObjectMapper om() {
    if (OM == null) {
      OM = new ObjectMapper();
      OM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      OM.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
      OM.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    return OM;
  }

  public static ObjectMapper om(@NonNull ObjectMapper objectMapper) {
    OM = objectMapper;
    return OM;
  }

  /*
  ThreadLocal is not perfect in spring mvc env.
   */
  @Deprecated
  public static ObjectMapper omWithCtx() {
    return omWithCtx(om());
  }

  /*
  ThreadLocal is not perfect in spring mvc env.
   */
  @Deprecated
  public static ObjectMapper omWithCtx(@NonNull ObjectMapper objectMapper) {
    return appendCtxIgnoredFilter(om(objectMapper));
  }

  //parameters
  public static String p(Object... objects) {
    Map<String, Object> rtnMap = Map0.newHashMap();
    rtnMap.put("p", List0.newArrayList(objects));
    return writeValueAsString(rtnMap);
  }

  public static JsonNode path(@NonNull JsonNode jsonNode, String path) {
    JsonNode rtnNode = jsonNode;
    for (String p : path.split(Regex0.DOT)) {
      if (p.endsWith(String0.CLOSE_BRACKET)) {
        String[] a = p.split(Regex0.OPEN_BRACKET);
        rtnNode = rtnNode.path(a[0]);
        rtnNode = rtnNode.path(Integer.parseInt(a[1].substring(0, a[1].length() - 1)));
      } else {
        rtnNode = rtnNode.path(p);
      }
    }
    return rtnNode;
  }

  public static JsonNode path(@NonNull ObjectMapper objectMapper, String jsonString, String path, boolean rtnNullIfException) {
    try {
      return path(objectMapper.readTree(jsonString), path);
    } catch (Exception e) {
      if (rtnNullIfException) {
        return null;
      } else {
        throw new ZeroException(e);
      }
    }
  }

  public static JsonNode path(String jsonString, String path) {
    return path(jsonString, path, false);
  }

  public static JsonNode path(String jsonString, String path, boolean rtnNullIfException) {
    return path(om(), jsonString, path, rtnNullIfException);
  }

  public static <T> T path(String jsonString, String path, Class<T> valueType) {
    return path(jsonString, path, valueType, false);
  }

  public static <T> T path(String jsonString, String path, Class<T> valueType, boolean rtnNullIfException) {
    return treeToValue(path(om(), jsonString, path, rtnNullIfException), valueType, rtnNullIfException);
  }

  public static <T> T readValue(@NonNull ObjectMapper objectMapper, String content, Class<T> valueType) {
    return readValue(objectMapper, content, valueType, false);
  }

  public static <T> T readValue(@NonNull ObjectMapper objectMapper, String content, Class<T> valueType, boolean rtnNullIfException) {
    try {
      return readValue(objectMapper.readValue(content, valueType));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      if (rtnNullIfException) {
        return null;
      } else {
        throw new ZeroException(e);
      }
    }
  }

  public static <T> T readValue(@NonNull ObjectMapper objectMapper, String content, JavaType javaType) {
    return readValue(objectMapper, content, javaType, false);
  }

  public static <T> T readValue(@NonNull ObjectMapper objectMapper, String content, JavaType javaType, boolean rtnNullIfException) {
    try {
      return readValue(objectMapper.readValue(content, javaType));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      if (rtnNullIfException) {
        return null;
      } else {
        throw new ZeroException(e);
      }
    }
  }

  public static <T> T readValue(@NonNull ObjectMapper objectMapper, String content, TypeReference<T> valueTypeRef) {
    return readValue(objectMapper, content, valueTypeRef, false);
  }

  public static <T> T readValue(@NonNull ObjectMapper objectMapper, String content, TypeReference<T> valueTypeRef, boolean rtnNullIfException) {
    try {
      return readValue(objectMapper.readValue(content, valueTypeRef));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      if (rtnNullIfException) {
        return null;
      } else {
        throw new ZeroException(e);
      }
    }
  }

  public static <T> T readValue(String content, Class<T> valueType) {
    return readValue(content, valueType, false);
  }

  public static <T> T readValue(String content, Class<T> valueType, boolean rtnNullIfException) {
    return readValue(om(), content, valueType, rtnNullIfException);
  }

  public static <T> T readValue(String content, JavaType javaType) {
    return readValue(content, javaType, false);
  }

  public static <T> T readValue(String content, JavaType javaType, boolean rtnNullIfException) {
    return readValue(om(), content, javaType, rtnNullIfException);
  }

  public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
    return readValue(content, valueTypeRef, false);
  }

  public static <T> T readValue(String content, TypeReference<T> valueTypeRef, boolean rtnNullIfException) {
    return readValue(om(), content, valueTypeRef, rtnNullIfException);
  }

  public static <T> T readValue(T t) {
    return OBJECT_EMPTY_STRING.equals(writeValueAsString(t)) ? null : t;
  }

  public static <T> T treeToValue(@NonNull ObjectMapper objectMapper, TreeNode n, Class<T> valueType) {
    return treeToValue(objectMapper, n, valueType, false);
  }

  public static <T> T treeToValue(@NonNull ObjectMapper objectMapper, TreeNode n, Class<T> valueType, boolean rtnNullIfException) {
    try {
      return objectMapper.treeToValue(n, valueType);
    } catch (Exception e) {
      if (rtnNullIfException) {
        return null;
      } else {
        throw new ZeroException(e);
      }
    }
  }

  public static <T> T treeToValue(TreeNode n, Class<T> valueType) {
    return treeToValue(om(), n, valueType);
  }

  public static <T> T treeToValue(TreeNode n, Class<T> valueType, boolean rtnNullIfException) {
    return treeToValue(om(), n, valueType, rtnNullIfException);
  }

  public static <T extends JsonNode> T valueToTree(Object fromValue) {
    return valueToTree(om(), fromValue);
  }

  public static <T extends JsonNode> T valueToTree(@NonNull ObjectMapper objectMapper, Object fromValue) {
    return objectMapper.valueToTree(fromValue);
  }

  public static String writeValueAsString(Object value) {
    return writeValueAsString(om(), value);
  }

  public static String writeValueAsString(@NonNull ObjectMapper objectMapper, Object value) {
    return writeValueAsString(objectMapper, value, false);
  }

  public static String writeValueAsString(@NonNull ObjectMapper objectMapper, Object value, boolean rtnNullIfException) {
    try {
      return objectMapper.writeValueAsString(value);
    } catch (Exception e) {
      if (rtnNullIfException) {
        return null;
      } else {
        throw new ZeroException(e);
      }
    }
  }
}
