package org.shaneking.ling.zero.lang;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.zero.util.Regex0;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@Slf4j
public class Object0 {
  public static final String NULL = "null";

  //if return null, maybe real null or exception
  public static Object gs(Object o, @NonNull String fields) {
    return Object0.gs(o, fields.split(Regex0.DOT));
  }

  //if return null, maybe some field unInstance or exception
  public static <T> Object gs(Object o, @NonNull String fields, @NonNull T t) {
    Object rtn = o;
    Object penultimate = o;
    String[] fieldArray = fields.split(Regex0.DOT);
    if (fieldArray.length > 1) {
      penultimate = Object0.gs(o, Arrays.copyOf(fieldArray, fieldArray.length - 1));
      rtn = null;
    }
    if (penultimate != null) {
      String last = fieldArray[fieldArray.length - 1];
      try {
        penultimate.getClass().getMethod("set" + last.substring(0, 1).toUpperCase() + last.substring(1), t.getClass()).invoke(penultimate, t);
        rtn = o;
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
        log.error(e.getMessage(), e);
      }
    }
    return rtn;
  }

  private static Object gs(Object o, String[] fields) {
    return Object0.gs(o, fields, 0);
  }

  private static Object gs(@NonNull Object o, @NonNull String[] fields, int index) {
    Object rtn = null;
    if (fields.length > index) {
      try {
        rtn = Object0.gs(o.getClass().getMethod("get" + fields[index].substring(0, 1).toUpperCase() + fields[index].substring(1)).invoke(o), fields, ++index);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
        log.error(e.getMessage(), e);
      }
    } else {
      rtn = o;
    }
    return rtn;
  }
}
