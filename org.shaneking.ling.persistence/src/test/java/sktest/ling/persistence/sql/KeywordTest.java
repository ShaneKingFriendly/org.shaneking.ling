package sktest.ling.persistence.sql;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.sql.Keyword;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KeywordTest extends SKUnit {

  @Test
  void wrapBlack() {
    assertEquals(" and ", String0.wrapBlack(Keyword.AND));
  }
}
