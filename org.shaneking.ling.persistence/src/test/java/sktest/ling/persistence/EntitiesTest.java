package sktest.ling.persistence;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.Pagination;
import sktest.ling.persistence.entity.DialectSqlEntityPrepareWithoutSetter;

import static org.junit.jupiter.api.Assertions.*;

class EntitiesTest {
  DialectSqlEntityPrepareWithoutSetter dialectSqlEntityPrepareWithoutSetter = new DialectSqlEntityPrepareWithoutSetter();

  @Test
  void sroPagination() {
    assertAll(
      () -> assertNull(dialectSqlEntityPrepareWithoutSetter.sroPagination(new Pagination()))
    );
  }

  @Test
  void nullSetter() {
    assertAll(
      () -> assertNotNull(dialectSqlEntityPrepareWithoutSetter.nullSetter())
    );
  }
}
