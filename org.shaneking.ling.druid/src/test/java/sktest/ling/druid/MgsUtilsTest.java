package sktest.ling.druid;

import com.alibaba.druid.DbType;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.druid.MgsUtils;
import org.shaneking.ling.zero.persistence.Tuple;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MgsUtilsTest {
  @Test
  void parseSqlLimit() {
    assertAll(
      () -> assertEquals(7, MgsUtils.parseSqlLimit(DbType.mysql, "select * from tbl limit 3,7")),
      () -> assertEquals(7, MgsUtils.parseSqlLimit(DbType.mysql, "select * from tbl limit 7 offset 3"))
    );
  }

  @Test
  void parseTables() {
    String sameTableNameSql = "select * from S1.t1 union all select * from s1.T1 union all select * from c1.S1.T1";
    String starColumnSql = "select t.*, t.c from S1.t1 t union all select s.*, s.d from s1.T1 s";
    assertAll(
      () -> assertEquals(2, Tuple.getFirst(MgsUtils.parseTables(DbType.mysql, sameTableNameSql)).size()),
      () -> assertEquals("S1.t1", Tuple.getFirst(MgsUtils.parseTables(DbType.mysql, sameTableNameSql)).get(0).getRaw().toString()),
      () -> assertEquals("c1.S1.T1", Tuple.getFirst(MgsUtils.parseTables(DbType.mysql, sameTableNameSql)).get(1).getRaw().toString()),

      () -> assertEquals(3, Tuple.getSecond(MgsUtils.parseTables(DbType.mysql, starColumnSql)).size()),
      () -> assertEquals("MgsTableStatColumn(raw=S1.t1.*, table=s1.t1, name=*, fullName=s1.t1.*)", Tuple.getSecond(MgsUtils.parseTables(DbType.mysql, starColumnSql)).get(0).toString()),
      () -> assertEquals("MgsTableStatColumn(raw=S1.t1.c, table=s1.t1, name=c, fullName=s1.t1.c)", Tuple.getSecond(MgsUtils.parseTables(DbType.mysql, starColumnSql)).get(1).toString()),
      () -> assertEquals("MgsTableStatColumn(raw=s1.T1.d, table=s1.t1, name=d, fullName=s1.t1.d)", Tuple.getSecond(MgsUtils.parseTables(DbType.mysql, starColumnSql)).get(2).toString())
    );
  }
}
