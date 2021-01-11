package org.shaneking.ling.rr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Pri is trans object wrapper
 * <p>
 * Mapping for: https://github.com/ShaneKing/sk-user
 */
@Accessors(chain = true)
@ToString
public class Pri<E, O, R> {
  @Getter
  @Setter
  private E ext;//extend settings like table config

  @Getter
  @Setter
  private O obj;//main object content

  @Getter
  @Setter
  private R rtn;//return, just response, don't request

  public static <E, O, R> Pri<E, O, R> build() {
    return new Pri<E, O, R>();
  }

  //some like no request parameter
  public static <E, O, R> Pri<E, O, R> build(R rtn) {
    return new Pri<E, O, R>().setRtn(rtn);
  }

  public static <E, O, R> Pri<E, O, R> build(R rtn, O obj) {
    return new Pri<E, O, R>().setRtn(rtn).setObj(obj);
  }

  public static <E, O, R> Pri<E, O, R> build(R rtn, O obj, E ext) {
    return new Pri<E, O, R>().setRtn(rtn).setObj(obj).setExt(ext);
  }
}
