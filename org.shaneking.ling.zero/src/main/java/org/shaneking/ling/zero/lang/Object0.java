package org.shaneking.ling.zero.lang;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.zero.util.Regex0;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Object0 {
  public static final Object EXCEPTION = new Object();
  public static final String NULL = "null";

  public static String format(@NonNull String pattern, @NonNull Object object) {
    String rePattern = pattern;
    Matcher m = Pattern.compile("\\{([^}]+)}").matcher(rePattern);

    while (m.find()) {
      rePattern = rePattern.replace(m.group(), String.valueOf(Object0.gs(object, m.group(1))));
    }
    return rePattern;
  }

  //getter/setter
  //field1.field2.field3
  //field1.field2[1].field3
  public static Object gs(Object o, @NonNull String fields) {
    return gs(o, fields, true);
  }

  //return null is real null, EXCEPTION if Exception, else correct
  public static Object gs(Object o, @NonNull String fields, boolean quietly) {
    return Object0.gs(o, fields.replace(Char0.OPEN_BRACKET, Char0.DOT).split(Regex0.DOT), quietly);
  }

  public static <T> Object gs(Object o, @NonNull String fields, @NonNull T t) {
    return gs(o, fields, t, true);
  }

  //return null if tail object unInstance, EXCEPTION if Exception, else correct
  public static <T> Object gs(Object o, @NonNull String fields, @NonNull T t, boolean quietly) {
    Object rtn = o;
    Object penultimate = o;
    String[] fieldArray = fields.replace(Char0.OPEN_BRACKET, Char0.DOT).split(Regex0.DOT);
    if (fieldArray.length > 1) {
      penultimate = Object0.gs(o, Arrays.copyOf(fieldArray, fieldArray.length - 1), quietly);
      rtn = null;
    }
    if (penultimate != null) {
      String last = fieldArray[fieldArray.length - 1];
      try {
        if (penultimate.getClass().isArray() && last.endsWith(String0.CLOSE_BRACKET)) {
          Array.set(penultimate, Integer.parseInt(last.split(String0.CLOSE_BRACKET)[0]), t);
        } else if (penultimate instanceof Collection && last.endsWith(String0.CLOSE_BRACKET)) {
          if (penultimate instanceof List) {
            ((List<T>) penultimate).set(Integer.parseInt(last.split(String0.CLOSE_BRACKET)[0]), t);
          } else {
            Array.set(((Collection<?>) penultimate).toArray(), Integer.parseInt(last.split(String0.CLOSE_BRACKET)[0]), t);
          }
        } else if (penultimate instanceof Map && !last.endsWith(String0.CLOSE_BRACKET)) {
          ((Map<String, T>) penultimate).put(last, t);
        } else if (!last.endsWith(String0.CLOSE_BRACKET)) {
          penultimate.getClass().getMethod("set" + String0.upperFirst(last), t.getClass()).invoke(penultimate, t);
        } else {
          throw new IllegalArgumentException(last);
        }
        rtn = o;
      } catch (Exception e) {
        log.warn(e.getMessage(), e);
        if (quietly) {
          rtn = EXCEPTION;
        } else {
          throw new ZeroException(e);
        }
      }
    }
    return rtn;
  }

  private static Object gs(Object o, String[] fields, boolean quietly) {
    return Object0.gs(o, fields, 0, quietly);
  }

  private static Object gs(@NonNull Object o, @NonNull String[] fields, int index, boolean quietly) {
    Object rtn = null;
    if (fields.length > index) {
      try {
        String field = fields[index];
        if (o.getClass().isArray() && field.endsWith(String0.CLOSE_BRACKET)) {
          rtn = Object0.gs(Array.get(o, Integer.parseInt(field.split(String0.CLOSE_BRACKET)[0])), fields, ++index, quietly);
        } else if (o instanceof Collection && field.endsWith(String0.CLOSE_BRACKET)) {
          if (o instanceof List) {
            rtn = Object0.gs(((List<?>) o).get(Integer.parseInt(field.split(String0.CLOSE_BRACKET)[0])), fields, ++index, quietly);
          } else {
            rtn = Object0.gs(Array.get(((Collection<?>) o).toArray(), Integer.parseInt(field.split(String0.CLOSE_BRACKET)[0])), fields, ++index, quietly);
          }
        } else if (o instanceof Map && !field.endsWith(String0.CLOSE_BRACKET)) {
          rtn = Object0.gs(((Map<?, ?>) o).get(field), fields, ++index, quietly);
        } else if (!field.endsWith(String0.CLOSE_BRACKET)) {
          rtn = Object0.gs(o.getClass().getMethod("get" + String0.upperFirst(fields[index])).invoke(o), fields, ++index, quietly);
        } else {
          throw new IllegalArgumentException(field);
        }
      } catch (Exception e) {
        log.warn(e.getMessage(), e);
        if (quietly) {
          rtn = EXCEPTION;
        } else {
          throw new ZeroException(e);
        }
      }
    } else {
      rtn = o;
    }
    return rtn;
  }
}
