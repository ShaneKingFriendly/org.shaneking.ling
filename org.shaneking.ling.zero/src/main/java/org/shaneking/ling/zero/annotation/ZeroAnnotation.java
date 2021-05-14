package org.shaneking.ling.zero.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE, TYPE_PARAMETER, TYPE_USE})
public @interface ZeroAnnotation {
  String ERR_CODE__ANNOTATION_SETTING_ERROR = "ZERO_ANNOTATION__ANNOTATION_SETTING_ERROR";
}
