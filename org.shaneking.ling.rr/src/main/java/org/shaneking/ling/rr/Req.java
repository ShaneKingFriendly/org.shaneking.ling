package org.shaneking.ling.rr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Just reference for request and response, overwrite it in most scenarios
 * <p>
 * //C, E, O, R, P maybe fastjson,gson,jackson...
 */
@Accessors(chain = true)
@ToString
public class Req<C, E, O, R, P> {
  @Getter
  @Setter
  private C ctx;

  @Getter
  @Setter
  private String enc;//this is encode of pri

  @Getter
  @Setter
  private Pri<E, O, R> pri;//main object

  @Getter
  @Setter
  private P pub;//system properties;channelName,tenantName,encoded(if Y, parse enc to pri),priPath('res')

  public static <C, E, O, R, P> Req<C, E, O, R, P> build() {
    return new Req<C, E, O, R, P>();
  }

  public static <C, E, O, R, P> Req<C, E, O, R, P> build(Pri<E, O, R> pri) {
    return new Req<C, E, O, R, P>().setPri(pri);
  }

  public static <C, E, O, R, P> Req<C, E, O, R, P> build(P pub) {
    return new Req<C, E, O, R, P>().setPub(pub);
  }

  public static <C, E, O, R, P> Req<C, E, O, R, P> build(P pub, Pri<E, O, R> pri) {
    return new Req<C, E, O, R, P>().setPub(pub).setPri(pri);
  }

  public static <C, E, O, R, P> Req<C, E, O, R, P> build(P pub, String enc) {
    return new Req<C, E, O, R, P>().setPub(pub).setEnc(enc);
  }

  public C detach() {
    C rtn = ctx;
    ctx = null;
    return rtn;
  }

  public Req<C, E, O, R, P> attach(C c) {
    this.ctx = c;
    return this;
  }
}
