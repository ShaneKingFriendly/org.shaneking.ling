package sktest.ling.persistence.entity.sql;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractDialectSqlEntityTest {

  @Test
  void nameIn() {
    DialectSqlEntityPrepareWithNamed dialectSqlEntityPrepareWithNamed = new DialectSqlEntityPrepareWithNamed();
    DialectSqlEntityPrepareWithoutTableName dialectSqlEntityPrepareWithoutTableName = new DialectSqlEntityPrepareWithoutTableName();
    assertAll(
      () -> assertEquals("`null(null[null])`", dialectSqlEntityPrepareWithNamed.nameInMessage()),
      () -> assertEquals("nullnull", dialectSqlEntityPrepareWithNamed.nameInPage()),
      () -> assertNull(dialectSqlEntityPrepareWithNamed.nameInRaw()),
      () -> assertEquals("`null(null[null])`", dialectSqlEntityPrepareWithoutTableName.nameInMessage()),
      () -> assertEquals("nullnull", dialectSqlEntityPrepareWithoutTableName.nameInPage()),
      () -> assertNull(dialectSqlEntityPrepareWithoutTableName.nameInRaw())
    );
    dialectSqlEntityPrepareWithNamed.setName("TestName");
    assertAll(
      () -> assertEquals("`TestName(null[null])`", dialectSqlEntityPrepareWithNamed.nameInMessage()),
      () -> assertEquals("TestNamenull", dialectSqlEntityPrepareWithNamed.nameInPage()),
      () -> assertEquals("TestName", dialectSqlEntityPrepareWithNamed.nameInRaw())
    );
  }
}
