package org.shaneking.ling.persistence.struct;

/**
 * Maybe you want to store file path in single table.
 */
public interface Ref {
  String getRefType();
  String getRefId();
}
