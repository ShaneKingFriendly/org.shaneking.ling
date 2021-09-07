package org.shaneking.ling.persistence.entity;

import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Transient;

/*
 * advantage
 * 1. no real delete
 * 2. no sql injection. all like `where dd = 'uuid0.cul33'`, not generate by entity
 * <p>
 * when req rmv entity
 * 1.uuid0.cul33
 * 2.update table set dd = 'uuid0.cul33', lm_dsz = ..., lm_uid = ... where ...
 * 3.delete relTable where relId in (select id from table where dd = 'uuid0.cul33')
 * 4.update relTable set relCol = '' where relId in (select id from table where dd = 'uuid0.cul33')
 * 5.insert into table_d (cols) select cols from table where dd = 'uuid0.cul33'
 * 6.delete table where dd = 'uuid0.cul33'
 * <p>
 * when req del entity
 * 1.uuid0.cul33
 * 2.update table set dd = 'uuid0.cul33', lm_dsz = ..., lm_uid = ... where ...
 * 3.delete relTable where relId in (select id from table where dd = 'uuid0.cul33')
 * 4.update relTable set relCol = '' where relId in (select id from table where dd = 'uuid0.cul33')
 */
public interface Deleted {
  @Transient
  String COLUMN__DD = "dd";
  @Transient
  String FIELD__DD = "dd";

  String getDd();

  <T extends Deleted> T setDd(String dd);

  //set if nullOrEmpty
  default <T extends Deleted> T sinDd(String dd) {
    return setDd(String0.nullOrEmptyTo(getDd(), dd));
  }
}
