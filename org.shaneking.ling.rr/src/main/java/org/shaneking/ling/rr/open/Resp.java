package org.shaneking.ling.rr.open;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class Resp<O, I> {
  @Getter
  @Setter
  private String mvc;//Message Verification Code
  @Getter
  @Setter
  private String enc;//ciphertext of msg
  @Getter
  @Setter
  private RespMsg<O> msg;

  @Getter
  @Schema(hidden = true)
  @Setter
  private Boolean rbk;//if true, need rollback
  @Getter
  @Schema(hidden = true)
  @Setter
  private I req;
}
