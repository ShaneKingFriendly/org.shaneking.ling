package sktest.ling.persistence;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.entity.sql.sqllite.SqlliteSqlEntities;
import org.shaneking.ling.zero.persistence.Pagination;
import sktest.ling.persistence.entity.sql.AbstractDialectSqlEntityPrepare1;

import javax.persistence.Column;
import javax.persistence.Table;

import static org.junit.jupiter.api.Assertions.*;

class EntitiesTest {
  EntitiesPrepare1 entitiesPrepare1 = new EntitiesPrepare1();

  @Test
  void nullSetter() {
    assertAll(
      () -> assertNotNull(entitiesPrepare1.nullSetter())
    );
  }

  @Test
  void sroPagination() {
    assertAll(
      () -> assertNull(entitiesPrepare1.sroPagination(new Pagination()))
    );
  }

  @Accessors(chain = true)
  @Table
  @ToString(callSuper = true)
  public class EntitiesPrepare1 extends AbstractDialectSqlEntityPrepare1 implements SqlliteSqlEntities {
    @Column(length = 10)
    @Getter
    private String withoutSetter;
  }
}
