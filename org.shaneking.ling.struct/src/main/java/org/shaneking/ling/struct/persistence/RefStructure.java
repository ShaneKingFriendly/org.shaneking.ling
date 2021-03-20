package org.shaneking.ling.struct.persistence;

/**
 * Maybe you want to store file path in single table.
 */
public interface RefStructure {
  String getRefType();

  String getRefId();
}
