package org.shaneking.ling.rr.open;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.persistence.Condition;
import org.shaneking.ling.zero.persistence.Pagination;
import org.shaneking.ling.zero.persistence.Sorting;

import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@ToString
public class Tbl {
  @Getter
  @Setter
  private Pagination page;
  @Getter
  @Setter
  private List<Sorting> sorts;
  @Getter
  @Setter
  private Map<String, Condition> filters;

  public Pagination gnnPage() {
    if (getPage() == null) {
      setPage(new Pagination());
    }
    return getPage();
  }
}
