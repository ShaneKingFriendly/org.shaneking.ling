package sktest.ling.persistence.struct;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.struct.Tree;

@Accessors(chain = true)
@ToString(callSuper = true)
public class HelloTree implements Tree {
  @Getter
  @Setter
  private String id;
  @Getter
  @Setter
  private String nodeName;
  @Getter
  @Setter
  private String nodeDesc;
  @Getter
  @Setter
  private String nodeType;
  @Getter
  @Setter
  private String nodePath;
  @Getter
  @Setter
  private String nodePid;
}
