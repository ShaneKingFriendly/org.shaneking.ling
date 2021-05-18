package sktest.ling.persistence.struct;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class DictTest {

  @Test
  void getDictType() {
    assertNull(new DictPrepare().getDictType());
  }

  @Test
  void getDictValue() {
    assertNull(new DictPrepare().getDictValue());
  }

  @Test
  void getDictDisplay() {
    assertNull(new DictPrepare().getDictDisplay());
  }

  @Test
  void getDictDescription() {
    assertNull(new DictPrepare().getDictDescription());
  }
}
