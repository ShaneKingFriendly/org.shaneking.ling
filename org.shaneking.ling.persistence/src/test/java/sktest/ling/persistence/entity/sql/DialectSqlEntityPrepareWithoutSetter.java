package sktest.ling.persistence.entity.sql;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.entity.sql.sqllite.SqlliteSqlEntities;

import javax.persistence.Column;
import javax.persistence.Table;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class DialectSqlEntityPrepareWithoutSetter extends AbstractDialectSqlEntityPrepare implements SqlliteSqlEntities {
  @Column(length = 10)
  @Getter
  private String withoutSetter;
}
