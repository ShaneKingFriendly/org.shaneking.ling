package org.shaneking.ling.persistence.struct;

/**
 * Most hierarchy has right of inheritance
 */
public interface Tree {
  String NODE_TYPE__BRANCH = "B";
  String NODE_TYPE__LEAF = "L";
  String NODE_TYPE__ROOT = "R";

  //  String getNodeDesc();
  //  String getNodeName();
  String getNodePath();// like `/root/xxx/yyy/zzz/pId/id/`

  String getNodePid();

  String getNodeType();// like `R(Root)|B(Branch)|L(Leaf)`
}
