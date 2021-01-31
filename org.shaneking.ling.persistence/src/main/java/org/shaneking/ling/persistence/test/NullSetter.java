package org.shaneking.ling.persistence.test;

import org.shaneking.ling.persistence.AbstractEntity;

import java.util.Arrays;

public interface NullSetter {
  default NullSetter nullSetter() {
    nullSetter(this.getClass());
    return this;
  }

  default void nullSetter(Class<?> clazz) {
    if (AbstractEntity.class.isAssignableFrom(clazz.getSuperclass())) {
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
