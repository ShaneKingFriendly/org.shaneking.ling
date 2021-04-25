package sktest.ling.persistence.entity;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.entity.sql.sqllite.SqlliteSqlEntities;
import org.shaneking.ling.persistence.hello.NullSetter;

import javax.persistence.Table;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class HelloWithoutTableNameEntity extends HelloIdNoAdtVerSqlEntity implements SqlliteSqlEntities, NullSetter {
}
