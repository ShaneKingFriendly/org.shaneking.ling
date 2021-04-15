package org.shaneking.ling.persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class Pagination {
  public static int DEFAULT_SIZE = 30;
  public static int MAX_SIZE = 1023;// table has 1013 column in some company. then set birthday 1023, haha. maybe next 1314...
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
