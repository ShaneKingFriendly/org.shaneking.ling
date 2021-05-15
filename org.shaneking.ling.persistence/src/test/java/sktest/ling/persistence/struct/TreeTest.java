package sktest.ling.persistence.struct;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.struct.Tree;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

  @Test
  void getNodeType() {
    assertAll(
      () -> assertNull(new HelloTree().getNodeType()),
      () -> assertEquals(Tree.NODE_TYPE__ROOT, new HelloTree().setNodeType(Tree.NODE_TYPE__ROOT).getNodeType()),
      () -> assertEquals(Tree.NODE_TYPE__BRANCH, new HelloTree().setNodeType(Tree.NODE_TYPE__BRANCH).getNodeType()),
      () -> assertEquals(Tree.NODE_TYPE__LEAF, new HelloTree().setNodeType(Tree.NODE_TYPE__LEAF).getNodeType())
    );
  }

  @Test
  void getNodePath() {
    assertNull(new HelloTree().getNodePath());
  }

  @Test
  void getNodePid() {
    assertNull(new HelloTree().getNodePid());
  }
}
