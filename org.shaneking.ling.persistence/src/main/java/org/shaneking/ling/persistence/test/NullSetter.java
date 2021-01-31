package org.shaneking.ling.persistence.test;

import java.util.Arrays;

public interface NullSetter {
  default NullSetter nullSetter() {
//    Arrays.stream(this.getClass().getDeclaredFields()).filter(f -> f.getAnnotation(Setter.class) != null).forEachOrdered(f -> {
//      try {
//        this.getClass().getMethod("set" + String0.upperFirst(f.getName()), this.getClass()).invoke(this, null);
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    });
    Arrays.stream(this.getClass().getDeclaredMethods()).filter(m -> m.getName().startsWith("set")).filter(m -> m.getParameterTypes().length == 1).forEachOrdered(m -> {
      try {
        m.invoke(this, new Object[]{null});
      } catch (Exception e) {
        //ignore
      }
    });
    Arrays.stream(this.getClass().getSuperclass().getDeclaredMethods()).filter(m -> m.getName().startsWith("set")).filter(m -> m.getParameterTypes().length == 1).forEachOrdered(m -> {
      try {
//        m.invoke(this, null);//java.lang.IllegalArgumentException: wrong number of arguments
        m.invoke(this, new Object[]{null});
      } catch (Exception e) {
        //ignore
      }
    });
    return this;
  }
}
