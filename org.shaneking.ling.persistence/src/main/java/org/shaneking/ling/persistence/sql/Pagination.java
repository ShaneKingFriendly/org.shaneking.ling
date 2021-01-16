package org.shaneking.ling.persistence.sql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class Pagination {
  public static int DEFAULT_SIZE = 30;
  public static int MAX_SIZE = 1300;// table has 1013 column in some company
  @Getter
  @Setter
  private Long count;
  @Getter
  @Setter
  private Integer page;
  @Getter
  @Setter
  private Integer size;
}
