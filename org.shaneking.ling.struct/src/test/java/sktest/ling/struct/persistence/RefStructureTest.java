package sktest.ling.struct.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class RefStructureTest {

  @Test
  void getRefType() {
    assertNull(new HelloRef().getRefType());
  }

  @Test
  void getRefId() {
    assertNull(new HelloRef().getRefId());
  }
}
