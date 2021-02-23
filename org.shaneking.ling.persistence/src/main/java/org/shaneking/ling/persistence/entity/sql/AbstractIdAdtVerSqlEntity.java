package org.shaneking.ling.persistence.entity.sql;

import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.entity.AbstractSqlEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Version;

@Accessors(chain = true)
@ToString
public abstract class AbstractIdAdtVerSqlEntity<J> extends AbstractSqlEntity<J> implements IdAdtVerSqlEntities {
  @Column(nullable = false, length = 40, updatable = false, columnDefinition = "COMMENT 'identifies'")
  @ExcelColumn
  @Getter
  @Id
  @Setter
  private String id;

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

  @Column(nullable = false, columnDefinition = "default 0 COMMENT 'Version for optimistic locking'")
  @ExcelColumn
  @Getter
  @Setter
  @Version
  private Integer version;
}
