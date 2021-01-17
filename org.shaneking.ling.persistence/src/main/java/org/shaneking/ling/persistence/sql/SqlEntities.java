package org.shaneking.ling.persistence.sql;

import lombok.NonNull;
import org.shaneking.ling.persistence.Entities;

import java.util.List;

public interface SqlEntities extends Entities {
  void limitStatement(@NonNull List<String> limitList, @NonNull List<Object> objectList);
}
