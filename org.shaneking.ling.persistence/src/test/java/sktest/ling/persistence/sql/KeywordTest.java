package sktest.ling.persistence.sql;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.sql.Keyword;
import org.shaneking.ling.test.SKUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KeywordTest extends SKUnit {

  @Test
  void wrapBlack() {
    assertEquals(" and ", Keyword.wrapBlack(Keyword.AND));
  }
}
