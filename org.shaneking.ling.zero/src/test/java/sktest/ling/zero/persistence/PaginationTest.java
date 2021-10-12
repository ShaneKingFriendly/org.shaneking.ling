package sktest.ling.zero.persistence;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.persistence.Pagination;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaginationTest {

  @Test
  void testToString() {
    assertEquals("Pagination(count=100, idx=1, size=30)", new Pagination().setCount(100L).setIdx(1).setSize(30).toString());
  }
}
