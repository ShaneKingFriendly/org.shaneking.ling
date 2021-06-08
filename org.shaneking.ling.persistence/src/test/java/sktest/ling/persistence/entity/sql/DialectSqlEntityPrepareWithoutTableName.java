package sktest.ling.persistence.entity.sql;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.entity.sql.sqllite.SqlliteSqlEntities;

import javax.persistence.Table;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class DialectSqlEntityPrepareWithoutTableName extends AbstractDialectSqlEntityPrepare implements SqlliteSqlEntities {
}
