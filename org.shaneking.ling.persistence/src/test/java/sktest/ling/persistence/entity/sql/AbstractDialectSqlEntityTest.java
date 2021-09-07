package sktest.ling.persistence.entity.sql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.entity.sql.Named;
import org.shaneking.ling.persistence.entity.sql.sqllite.SqlliteSqlEntities;

import javax.persistence.Column;
import javax.persistence.Table;

import static org.junit.jupiter.api.Assertions.*;

class AbstractDialectSqlEntityTest {

  @Test
  void nameIn() {
    AbstractDialectSqlEntityPrepare1 abstractDialectSqlEntityPrepare1 = new AbstractDialectSqlEntityPrepare1();
    AbstractDialectSqlEntityPrepare2 abstractDialectSqlEntityPrepare2 = new AbstractDialectSqlEntityPrepare2();
    assertAll(
      () -> Assertions.assertEquals("`null(null[null])`", abstractDialectSqlEntityPrepare1.nameInMessage()),
      () -> assertEquals("`null(null[null])`", abstractDialectSqlEntityPrepare2.nameInMessage()),

      () -> Assertions.assertEquals("nullnull", abstractDialectSqlEntityPrepare1.nameInPage()),
      () -> assertEquals("nullnull", abstractDialectSqlEntityPrepare2.nameInPage()),

      () -> assertNull(abstractDialectSqlEntityPrepare1.nameInRaw()),
      () -> assertNull(abstractDialectSqlEntityPrepare2.nameInRaw())
    );
    abstractDialectSqlEntityPrepare1.setName("TestName");
    assertAll(
      () -> Assertions.assertEquals("`TestName(null[null])`", abstractDialectSqlEntityPrepare1.nameInMessage()),

      () -> Assertions.assertEquals("TestNamenull", abstractDialectSqlEntityPrepare1.nameInPage()),

      () -> Assertions.assertEquals("TestName", abstractDialectSqlEntityPrepare1.nameInRaw())
    );
  }

  @Accessors(chain = true)
  @Table
  @ToString(callSuper = true)
  public static class AbstractDialectSqlEntityPrepare1 extends sktest.ling.persistence.entity.sql.AbstractDialectSqlEntityPrepare1 implements Named, SqlliteSqlEntities {
    @Column(length = 30, columnDefinition = "default '' COMMENT ''")
    @Getter
    @Setter
    private String name;
  }

  @Accessors(chain = true)
  @Table
  @ToString(callSuper = true)
  public static class AbstractDialectSqlEntityPrepare2 extends sktest.ling.persistence.entity.sql.AbstractDialectSqlEntityPrepare1 implements SqlliteSqlEntities {
  }
}
