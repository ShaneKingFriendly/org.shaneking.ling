package org.shaneking.ling.rr;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class Req<I, C> {
  @Getter
  @Setter
  private String cno;//ChannelNo
  @Getter
  @Setter
  private String tkn;//Token, for one request on secret key scenario.
  @Getter
  @Setter
  private String mvc;//Message Verification Code
  @Getter
  @Setter
  private String enc;//ciphertext of msg
  @Getter
  @Setter
  private ReqMsg<I> msg;

  @Getter
  @Schema(hidden = true)
  @Setter
  private C ctx;

  public static <I, C> Req<I, C> build() {
    return new Req<I, C>();
  }
}
