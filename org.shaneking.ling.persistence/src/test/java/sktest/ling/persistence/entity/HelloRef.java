package sktest.ling.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.entity.Ref;

@Accessors(chain = true)
@ToString(callSuper = true)
public class HelloRef implements Ref {
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
