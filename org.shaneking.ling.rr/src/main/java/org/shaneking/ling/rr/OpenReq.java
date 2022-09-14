package org.shaneking.ling.rr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class OpenReq<I> {
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
}
