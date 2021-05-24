package sktest.ling.persistence.struct;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class DictTest {

  @Test
  void getDictType() {
    assertNull(new DictPrepare().getType());
  }

  @Test
  void getDictValue() {
    assertNull(new DictPrepare().getValue());
  }

  @Test
  void getDictDisplay() {
    assertNull(new DictPrepare().getDisplay());
  }

  @Test
  void getDictDescription() {
    assertNull(new DictPrepare().getDescription());
  }
}
