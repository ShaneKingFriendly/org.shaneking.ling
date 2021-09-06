package sktest.ling.jackson.ctx;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.ctx.JacksonCtx;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JacksonCtxTest {

  @Test
  void constructor() {
    assertAll(
      () -> assertNotNull(new JacksonCtx())
    );
  }

}
