package org.shaneking.ling.persistence.sql.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.AbstractEntity;
import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

@Accessors(chain = true)
@ToString
public abstract class IdEntity<J> extends AbstractEntity<J> {
  @Transient
  public static final String COLUMN__SK_ID = "sk_id";

  @Transient
  public static final String FIELD__SK_ID = "skId";

  //System.currentTimeMillis()_UUID0.l19()=13+1+19=33=UUID0.cUl33()
  //Date.now()_SK.l22()=13+1+22=36=SK.cUl40()
  //Date0.on().datetimes()_UUID0.l19()=17+1+19=37=UUID0.dUl37()
  //Date0.on().datetimes()_SK.l22()=17+1+22=40=SK.cUl40()
  //Date0.on().datetimes()_UUID.randomUUID()=17+1+36=54
  @Column(length = 40, updatable = false, columnDefinition = "COMMENT 'ShaneKing Uniquely identifies'")
  @Getter
  @Id
  @Setter
  private String skId;

  public IdEntity<J> initSkId(@NonNull String skId) {
    return this.setSkId(String0.null2EmptyTo(this.getSkId(), skId));
  }
}
