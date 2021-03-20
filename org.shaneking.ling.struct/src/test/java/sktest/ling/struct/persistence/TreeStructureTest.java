package sktest.ling.struct.persistence;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.struct.persistence.TreeStructure;

import static org.junit.jupiter.api.Assertions.*;

class TreeStructureTest {

  @Test
  void getNodeType() {
    assertAll(
      () -> assertNull(new HelloTree().getNodeType()),
      () -> assertEquals(TreeStructure.NODE_TYPE__ROOT, new HelloTree().setNodeType(TreeStructure.NODE_TYPE__ROOT).getNodeType()),
      () -> assertEquals(TreeStructure.NODE_TYPE__BRANCH, new HelloTree().setNodeType(TreeStructure.NODE_TYPE__BRANCH).getNodeType()),
      () -> assertEquals(TreeStructure.NODE_TYPE__LEAF, new HelloTree().setNodeType(TreeStructure.NODE_TYPE__LEAF).getNodeType())
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
