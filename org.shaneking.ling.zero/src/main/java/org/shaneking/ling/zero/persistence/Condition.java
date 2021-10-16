package org.shaneking.ling.zero.persistence;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;

import java.util.List;

@Accessors(chain = true)
@ToString
public class Condition {
  //will trans in request and response, so abbreviation
  @Getter
  @Setter
  private String le;//left expr
  @Getter
  @Setter
  private String op;//between,in,like,>...
  @Getter
  @Setter
  private List<String> cl;//content List
  @Getter
  @Setter
  private String cs;//content String
  @Getter
  @Setter
  private String bw;//beginWith
  @Getter
  @Setter
  private String ew;//endWith
  @Getter
  @Setter
  private String sq;//sub query

  public Condition appendVal(@NonNull List<String> valList) {
    this.setOp(Keyword.IN);
    List<String> clValList = this.getCl();
    if (clValList == null) {
      clValList = List0.newArrayList();
      this.setCl(clValList);
    }
    for (String val : valList) {
      if (!this.getCl().contains(val)) {
        this.getCl().add(val);
      }
    }
    return this;
  }

  public Condition appendVal(@NonNull String val) {
    return appendVal(List0.newArrayList(val));
  }

  public Condition resetVal(@NonNull List<String> valList) {
    return this.setOp(Keyword.IN).setCl(valList);
  }

  public Condition resetVal(@NonNull String val) {
    return this.setOp(String0.EQUAL).setCs(val);
  }

  public Condition retainVal(@NonNull List<String> valList) {
    this.setOp(Keyword.IN);
    List<String> clValList = this.getCl();
    if (clValList == null || clValList.size() < 1) {
      this.setCl(valList);
    } else {
      clValList.retainAll(valList);
    }
    return this;
  }
}
