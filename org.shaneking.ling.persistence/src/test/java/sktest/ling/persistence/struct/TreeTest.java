package sktest.ling.persistence.struct;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.struct.Tree;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

  @Test
  void getNodePath() {
    assertNull(new TreePrepare1().getNodePath());
  }

  @Test
  void getNodePid() {
    assertNull(new TreePrepare1().getNodePid());
  }

  @Test
  void getNodeType() {
    assertAll(
      () -> assertNull(new TreePrepare1().getNodeType()),
      () -> assertEquals(Tree.NODE_TYPE__ROOT, new TreePrepare1().setNodeType(Tree.NODE_TYPE__ROOT).getNodeType()),
      () -> assertEquals(Tree.NODE_TYPE__BRANCH, new TreePrepare1().setNodeType(Tree.NODE_TYPE__BRANCH).getNodeType()),
      () -> assertEquals(Tree.NODE_TYPE__LEAF, new TreePrepare1().setNodeType(Tree.NODE_TYPE__LEAF).getNodeType())
    );
  }

  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class TreePrepare1 implements Tree {
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

}
