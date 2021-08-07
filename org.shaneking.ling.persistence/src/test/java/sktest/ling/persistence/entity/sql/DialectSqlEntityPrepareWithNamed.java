package sktest.ling.persistence.entity.sql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.entity.sql.Named;
import org.shaneking.ling.persistence.entity.sql.sqllite.SqlliteSqlEntities;

import javax.persistence.Column;
import javax.persistence.Table;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class DialectSqlEntityPrepareWithNamed extends AbstractDialectSqlEntityPrepare implements Named, SqlliteSqlEntities {
  @Column(length = 30, columnDefinition = "default '' COMMENT ''")
  @Getter
  @Setter
  private String name;
}
