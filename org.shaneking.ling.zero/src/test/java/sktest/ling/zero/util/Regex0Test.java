package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.Regex0;

import static org.junit.jupiter.api.Assertions.*;

class Regex0Test {

  @Test
  void constructor() {
    assertAll(
      () -> assertNotNull(new Regex0())
    );
  }

  @Test
  void test() {
    assertEquals(2, "select * from table1;\nselect * from table2;select * from table3;".split(Regex0.SQL_SPLIT).length);
  }
}
