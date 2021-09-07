package org.shaneking.ling.persistence.entity;

public interface DeletedIdx extends Deleted, Idxed {
  default boolean ddNeedCreateIdx() {
    return true;
  }

  default boolean ddNeedJoinUniIdx() {
    return true;
  }
}
