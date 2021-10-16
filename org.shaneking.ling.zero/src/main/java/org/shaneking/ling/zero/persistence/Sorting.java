package org.shaneking.ling.zero.persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class Sorting {
  @Getter
  @Setter
  private String field;
  @Getter
  @Setter
  private String sort;
}
