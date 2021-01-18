package org.shaneking.ling.persistence.sql.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.lang.Integer0;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.persistence.Version;

@Accessors(chain = true)
@ToString(callSuper = true)
public abstract class IdAdtVerEntity<J> extends IdAdtEntity<J> {
  @Transient
  public static final String COLUMN__VERSION = "version";

  @Transient
  public static final String FIELD__VERSION = "version";

  @Column(nullable = false, columnDefinition = "default 0 COMMENT 'Version for optimistic locking'")
  @Getter
  @Setter
  @Version
  private Integer version;

  public IdAdtVerEntity<J> initVersion() {
    return this.setVersion(Integer0.null2Zero(this.getVersion()));
  }

  @Override
  public IdAdtVerEntity<J> initWithUserId(String userId) {
    super.initWithUserId(userId);
    return this.initVersion();
  }

  @Override
  public IdAdtVerEntity<J> initWithUserIdAndId(String userId, String id) {
    super.initWithUserIdAndId(userId, id);
    return this.initVersion();
  }
}
