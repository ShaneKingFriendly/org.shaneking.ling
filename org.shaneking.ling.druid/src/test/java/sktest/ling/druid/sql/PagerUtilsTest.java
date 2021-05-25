package sktest.ling.druid.sql;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.PagerUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagerUtilsTest {

  @Test
  void getLimit() {
    assertAll(
      () -> assertEquals(7, PagerUtils.getLimit("select * from tbl limit 3,7", DbType.mysql)),
      () -> assertEquals(7, PagerUtils.getLimit("select * from tbl limit 7 offset 3", DbType.mysql)),
      () -> assertEquals(-1, PagerUtils.getLimit("select * from tbl where rownum < 7", DbType.oracle)),
      () -> assertEquals(-1, PagerUtils.getLimit("select * from tbl where rownum > 3", DbType.oracle))
    );
  }
}
