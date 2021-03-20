package sktest.ling.struct.persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.struct.persistence.RefStructure;

@Accessors(chain = true)
@ToString(callSuper = true)
public class HelloRef implements RefStructure {
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
