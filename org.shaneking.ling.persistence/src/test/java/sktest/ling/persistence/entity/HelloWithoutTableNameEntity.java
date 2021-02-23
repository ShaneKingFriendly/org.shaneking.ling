package sktest.ling.persistence.entity;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.entity.sql.sqllite.SqlliteEntities;

import javax.persistence.Table;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class HelloWithoutTableNameEntity extends AbstractIdAdtVerEntity implements SqlliteEntities {
}
