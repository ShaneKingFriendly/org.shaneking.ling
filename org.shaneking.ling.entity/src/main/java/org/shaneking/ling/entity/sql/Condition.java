package org.shaneking.ling.entity.sql;

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

  public Condition appendId(@NonNull String id) {
    return appendIds(List0.newArrayList(id));
  }

  public Condition appendIds(@NonNull List<String> ids) {
    this.setOp(Keyword.IN);
    List<String> idList = this.getCl();
    if (idList == null) {
      idList = List0.newArrayList();
      this.setCl(idList);
    }
    for (String id : ids) {
      if (!this.getCl().contains(id)) {
        this.getCl().add(id);
      }
    }
    return this;
  }

  public Condition resetId(@NonNull String id) {
    return this.setOp(String0.EQUAL).setCs(id);
  }

  public Condition resetIds(@NonNull List<String> ids) {
    return this.setOp(Keyword.IN).setCl(ids);
  }

  public Condition retainIds(@NonNull List<String> ids) {
    this.setOp(Keyword.IN);
    List<String> idList = this.getCl();
    if (idList == null || idList.size() < 1) {
      this.setCl(ids);
    } else {
      idList.retainAll(ids);
    }
    return this;
  }
}
