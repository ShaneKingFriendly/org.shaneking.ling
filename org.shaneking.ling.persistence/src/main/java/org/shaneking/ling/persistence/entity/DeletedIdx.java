package org.shaneking.ling.persistence.entity;

public interface DeletedIdx extends Deleted {
  default boolean ddNeedJoinUniIdx() {
    return true;
  }

  default boolean ddNeedCreateIdx() {
    return true;
  }
}
