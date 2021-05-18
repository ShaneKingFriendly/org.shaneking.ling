package sktest.ling.persistence.struct;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class RefTest {

  @Test
  void getRefType() {
    assertNull(new RefPrepare().getRefType());
  }

  @Test
  void getRefId() {
    assertNull(new RefPrepare().getRefId());
  }
}
