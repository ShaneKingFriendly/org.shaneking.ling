package sktest.ling.persistence.struct;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.struct.Dict;

@Accessors(chain = true)
@ToString(callSuper = true)
public class DictPrepare implements Dict {
  @Getter
  @Setter
  private String dictType;
  @Getter
  @Setter
  private String dictValue;
  @Getter
  @Setter
  private String dictDisplay;
  @Getter
  @Setter
  private String dictDescription;
}
