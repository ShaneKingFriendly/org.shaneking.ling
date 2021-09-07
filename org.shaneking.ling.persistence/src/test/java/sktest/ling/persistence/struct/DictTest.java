package sktest.ling.persistence.struct;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.struct.Dict;

import static org.junit.jupiter.api.Assertions.assertNull;

class DictTest {

  @Test
  void getDictDescription() {
    assertNull(new DictPrepare1().getDescription());
  }

  @Test
  void getDictDisplay() {
    assertNull(new DictPrepare1().getDisplay());
  }

  @Test
  void getDictType() {
    assertNull(new DictPrepare1().getType());
  }

  @Test
  void getDictValue() {
    assertNull(new DictPrepare1().getValue());
  }

  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class DictPrepare1 implements Dict {
    @Getter
    @Setter
    private String type;
    @Getter
    @Setter
    private String value;
    @Getter
    @Setter
    private String display;
    @Getter
    @Setter
    private String description;
  }
}
