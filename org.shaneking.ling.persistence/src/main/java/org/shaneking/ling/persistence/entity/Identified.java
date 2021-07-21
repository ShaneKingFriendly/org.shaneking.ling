package org.shaneking.ling.persistence.entity;

import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Transient;

///self-system or platform primary key
//System.currentTimeMillis()_UUID0.l19()=13+1+19=33=UUID0.cUl33()
//Date.now()_SK.l22()=13+1+22=36=SK.cUl40()
//Date0.on().datetimes()_UUID0.l19()=17+1+19=37=UUID0.dUl37()
//Date0.on().datetimes()_SK.l22()=17+1+22=40=SK.cUl40()
//Date0.on().datetimes()_UUID.randomUUID()=17+1+36=54
public interface Identified {
  @Transient
  String ERR_CODE__REQUIRED = "IDENTIFIED__REQUIRED";

  @Transient
  String COLUMN__ID = "id";
  @Transient
  String FIELD__ID = "id";

  String getId();

  <T extends Identified> T setId(String id);

  //set if nullOrEmpty
  default <T extends Identified> T sinId(String id) {
    return setId(String0.nullOrEmptyTo(getId(), id));
  }
}
