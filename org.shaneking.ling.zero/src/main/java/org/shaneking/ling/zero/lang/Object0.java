package org.shaneking.ling.zero.lang;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.zero.util.Regex0;

import java.util.Arrays;

@Slf4j
public class Object0 {
  public static final Object EXCEPTION = new Object();
  public static final String NULL = "null";

  //return null is real null, EXCEPTION if Exception, else correct
  public static Object gs(Object o, @NonNull String fields) {
    return Object0.gs(o, fields.split(Regex0.DOT));
  }

  //return null if unInstance, EXCEPTION if Exception, else correct
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
        penultimate.getClass().getMethod("set" + String0.upperFirst(last), t.getClass()).invoke(penultimate, t);
        rtn = o;
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        rtn = EXCEPTION;
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
        rtn = Object0.gs(o.getClass().getMethod("get" + String0.upperFirst(fields[index])).invoke(o), fields, ++index);
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        rtn = EXCEPTION;
      }
    } else {
      rtn = o;
    }
    return rtn;
  }
}
