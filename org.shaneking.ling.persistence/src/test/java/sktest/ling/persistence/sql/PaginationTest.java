package sktest.ling.persistence.sql;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.sql.Pagination;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaginationTest {

  @Test
  void testToString() {
    assertEquals("Pagination(count=100, page=1, size=30)", new Pagination().setCount(100L).setPage(1).setSize(30).toString());
  }
}
