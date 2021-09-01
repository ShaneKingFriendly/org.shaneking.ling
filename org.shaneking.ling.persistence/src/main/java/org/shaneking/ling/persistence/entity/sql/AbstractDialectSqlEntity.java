package org.shaneking.ling.persistence.entity.sql;

import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.entity.AbstractSqlEntity;
import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Version;

/*
 * ToString not callSuper
 */
@Accessors(chain = true)
@ToString
public abstract class AbstractDialectSqlEntity<J> extends AbstractSqlEntity<J> implements DialectSqlEntities {
  ///self-system or platform primary key
  @Column(nullable = false, length = 40, updatable = false, columnDefinition = "COMMENT 'Identifies'")
  @ExcelColumn(style = {"title->background-color:red"})
  @Getter
  @Id
  @Setter
  private String id;//this is tech column for db increase sequence, it is not open for biz user

  @Column(nullable = false, columnDefinition = "default 0 COMMENT 'Version for optimistic locking'")
  @ExcelColumn
  @Getter
  @Setter
  @Version
  private Integer ver;

  @Column(length = 40, columnDefinition = "default 'N' COMMENT 'Deleted'")
  @ExcelColumn
  @Getter
  @Setter
  private String dd = String0.N;//this is tech column for db delete operation optimizing, it is always `N` for biz user

  @Column(length = 1, columnDefinition = "default 'N' COMMENT 'The invalid status of record {Y:invalid,N:valid(default)}'")
  @ExcelColumn
  @Getter
  @Setter
  private String ivd;

  /**
   * @see org.shaneking.ling.zero.util.Date0#DATE_TIME_SSS_ZONE
   */
  @Column(length = 30, columnDefinition = "default '' COMMENT 'The last modification date times zoned of record'")
  @ExcelColumn
  @Getter
  @Setter
  private String lmDsz;

  @Column(length = 40, columnDefinition = "default '' COMMENT 'The last modified user of record'")
  @ExcelColumn
  @Getter
  @Setter
  private String lmUid;

  ///business or third-party system primary key
  @Column(length = 40, columnDefinition = "default '' COMMENT 'Serial number'")
  @ExcelColumn(style = {"title->color:red"})
  @Getter
  @Setter
  private String no;

  ///user primary key
//  @Column(columnDefinition = "default '' COMMENT ''")
//  @ExcelColumn
//  @Getter
//  @Setter
//  private String name;

  //`NAME(NO[ID])`
  public String nameInMessage() {
    return nameInMessage(nameInRaw());
  }

  public String nameInMessage(String name) {
    return String0.wrap(name
        + String0.wrap(this.getNo()
        + String0.wrap(this.getId()
      , String0.OPEN_BRACKET, String0.CLOSE_BRACKET)
      , String0.OPEN_PARENTHESIS, String0.CLOSE_PARENTHESIS)
      , String0.GRAVE_ACCENT);
  }

  //NAME(NO)
  public String nameInPage() {
    return nameInRaw() + String0.wrap(this.getNo(), String0.OPEN_PARENTHESIS, String0.CLOSE_PARENTHESIS, true);
  }

  public String nameInRaw() {
    return this instanceof Named ? ((Named) this).getName() : null;
  }
}
