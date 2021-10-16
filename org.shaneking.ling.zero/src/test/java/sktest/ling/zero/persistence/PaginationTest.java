package sktest.ling.zero.persistence;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.persistence.Pagination;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaginationTest {

  @Test
  void testToString() {
    assertEquals("Pagination(idx=1, rows=30, count=100)", new Pagination().setIdx(1).setRows(30).setCount(100L).toString());
  }
}
