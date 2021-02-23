package org.shaneking.ling.persistence.sql.entity;

import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.entity.AbstractSqlEntity;
import org.shaneking.ling.persistence.entity.Identified;
import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Column;
import javax.persistence.Id;

@Accessors(chain = true)
@ToString
public abstract class IdEntity<J> extends AbstractSqlEntity<J> implements Identified {

  @Column(nullable = false, length = 40, updatable = false, columnDefinition = "COMMENT 'identifies'")
  @ExcelColumn
  @Getter
  @Id
  @Setter
  private String id;

  public IdEntity<J> initId(String id) {
    return this.setId(String0.null2EmptyTo(this.getId(), id));
  }
}
