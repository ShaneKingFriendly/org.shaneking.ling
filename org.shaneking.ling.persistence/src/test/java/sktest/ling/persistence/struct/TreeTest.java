package sktest.ling.persistence.struct;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.struct.Tree;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

  @Test
  void getNodeType() {
    assertAll(
      () -> assertNull(new TreePrepare().getNodeType()),
      () -> assertEquals(Tree.NODE_TYPE__ROOT, new TreePrepare().setNodeType(Tree.NODE_TYPE__ROOT).getNodeType()),
      () -> assertEquals(Tree.NODE_TYPE__BRANCH, new TreePrepare().setNodeType(Tree.NODE_TYPE__BRANCH).getNodeType()),
      () -> assertEquals(Tree.NODE_TYPE__LEAF, new TreePrepare().setNodeType(Tree.NODE_TYPE__LEAF).getNodeType())
    );
  }

  @Test
  void getNodePath() {
    assertNull(new TreePrepare().getNodePath());
  }

  @Test
  void getNodePid() {
    assertNull(new TreePrepare().getNodePid());
  }
}
