package org.shaneking.ling.rr.open;

import jdk.nashorn.internal.ir.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.persistence.Pagination;

//Cacheable
@Accessors(chain = true)
@ToString
public class RespMsgBody<O> {
  @Getter
  @Setter
  private String code;
  @Getter
  @Setter
  private String info;//information
  @Getter
  @Setter
  private Pagination page;
  @Getter
  @Setter
  private O data;//main object content
  @Getter
  @Setter
  private ObjectNode jsn;

  public static <O> RespMsgBody<O> build() {
    return new RespMsgBody<O>();
  }

  public static <O> RespMsgBody<O> build(String code, O data, String info) {
    return new RespMsgBody<O>().setCode(code).setData(data).setInfo(info);
  }
}
