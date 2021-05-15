package sktest.ling.persistence.struct;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class DictTest {

  @Test
  void getDictType() {
    assertNull(new HelloDict().getDictType());
  }

  @Test
  void getDictValue() {
    assertNull(new HelloDict().getDictValue());
  }

  @Test
  void getDictDisplay() {
    assertNull(new HelloDict().getDictDisplay());
  }

  @Test
  void getDictDescription() {
    assertNull(new HelloDict().getDictDescription());
  }
}
