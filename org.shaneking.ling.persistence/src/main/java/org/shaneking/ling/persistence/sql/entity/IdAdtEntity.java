package org.shaneking.ling.persistence.sql.entity;

import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Date0;

import javax.persistence.Column;
import javax.persistence.Transient;

@Accessors(chain = true)
@ToString(callSuper = true)
public abstract class IdAdtEntity<J> extends IdEntity<J> {
  @Transient
  public static final String COLUMN__INVALID = "invalid";
  @Transient
  public static final String COLUMN__LAST_MODIFY_DATE_TIME = "last_modify_date_time";
  @Transient
  public static final String COLUMN__LAST_MODIFY_USER_ID = "last_modify_user_id";

  @Transient
  public static final String FIELD__INVALID = "invalid";
  @Transient
  public static final String FIELD__LAST_MODIFY_DATE_TIME = "lastModifyDateTime";
  @Transient
  public static final String FIELD__LAST_MODIFY_USER_ID = "lastModifyUserId";

  @Column(length = 1, columnDefinition = "default 'N' COMMENT 'The invalid status of record {Y:invalid,N:valid(default)}'")
  @ExcelColumn
  @Getter
  @Setter
  private String invalid;

  /**
   * @see org.shaneking.ling.zero.util.Date0#DATE_TIME
   */
  @Column(length = 20, columnDefinition = "default '' COMMENT 'The last modification date time of record'")
  @ExcelColumn
  @Getter
  @Setter
  private String lastModifyDateTime;

  @Column(length = 40, columnDefinition = "default '' COMMENT 'The last modified user of record'")
  @ExcelColumn
  @Getter
  @Setter
  private String lastModifyUserId;

  public IdAdtEntity<J> initInvalid() {
    return this.setInvalid(String0.null2EmptyTo(this.getInvalid(), String0.N));
  }

  public IdAdtEntity<J> initWithUserId(String userId) {
    return this.initInvalid().setLastModifyDateTime(String0.null2EmptyTo(this.getLastModifyDateTime(), Date0.on().dateTime())).setLastModifyUserId(String0.null2EmptyTo(this.getLastModifyUserId(), userId));
  }

  public IdAdtEntity<J> initWithUserIdAndId(String userId, String id) {
    this.initId(id);
    return this.initWithUserId(userId);
  }
}
