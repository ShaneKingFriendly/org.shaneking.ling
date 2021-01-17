package sktest.ling.persistence.sql;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.sql.Keyword;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KeywordTest {

  @Test
  void wrapBlack() {
    assertEquals(" and ", Keyword.wrapBlack(Keyword.AND));
  }
}
