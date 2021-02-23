package org.shaneking.ling.persistence.hello;

import org.shaneking.ling.persistence.Entities;

import java.util.Arrays;

public interface NullSetter {
  default NullSetter nullSetter() {
    nullSetter(this.getClass());
    return this;
  }

  default void nullSetter(Class<?> clazz) {
    if (Entities.class.isAssignableFrom(clazz.getSuperclass())) {
      nullSetter(clazz.getSuperclass());
    }
    Arrays.stream(clazz.getDeclaredMethods()).filter(m -> m.getName().startsWith("set")).filter(m -> m.getParameterTypes().length == 1).forEachOrdered(m -> {
      try {
        m.invoke(this, new Object[]{null});
      } catch (Exception e) {
        //ignore
      }
    });
  }
}
