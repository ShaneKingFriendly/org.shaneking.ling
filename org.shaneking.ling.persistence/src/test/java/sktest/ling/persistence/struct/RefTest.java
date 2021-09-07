package sktest.ling.persistence.struct;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.struct.Ref;

import static org.junit.jupiter.api.Assertions.assertNull;

class RefTest {

  @Test
  void getRefId() {
    assertNull(new RefPrepare1().getRefId());
  }

  @Test
  void getRefType() {
    assertNull(new RefPrepare1().getRefType());
  }

  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class RefPrepare1 implements Ref {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String refType;
    @Getter
    @Setter
    private String refId;
  }

}
