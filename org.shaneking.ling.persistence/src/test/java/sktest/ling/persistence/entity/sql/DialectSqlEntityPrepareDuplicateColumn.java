package sktest.ling.persistence.entity.sql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.entity.sql.sqllite.SqlliteSqlEntities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class DialectSqlEntityPrepareDuplicateColumn extends AbstractDialectSqlEntityPrepare implements SqlliteSqlEntities {

  @Column(nullable = false)
  @Getter
  @Setter
  private String notNullCol;
  @Column(nullable = false)
  @Getter
  @Id
  @Setter
  private String duplicateId;
  @Column(nullable = false)
  @Getter
  @Version
  @Setter
  private Integer duplicateVersion;
}
