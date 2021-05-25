package sktest.ling.druid;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.parser.SQLType;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.druid.MgsUtils;
import org.shaneking.ling.zero.persistence.Tuple;
import org.shaneking.ling.zero.util.List0;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MgsUtilsTest {
  @Test
  void adjustQueryLimit() {
    assertAll(
      () -> assertEquals("SELECT *\nFROM tbl\nLIMIT 3, 6;SELECT *\nFROM tbl\nLIMIT 3, 6", MgsUtils.adjustQueryLimit(DbType.mysql, "select * from tbl limit 3,7;select * from tbl limit 3,7", 6)),
      () -> assertEquals("SELECT *\nFROM tbl\nLIMIT 3, 7;SELECT *\nFROM tbl\nLIMIT 3, 7", MgsUtils.adjustQueryLimit(DbType.mysql, "select * from tbl limit 3,7;select * from tbl limit 3,7", 9)),
      () -> assertEquals("SELECT *\nFROM tbl\nLIMIT 3, 6;SELECT *\nFROM tbl\nLIMIT 3, 6", MgsUtils.adjustQueryLimit(DbType.mysql, "select * from tbl limit 7 offset 3;select * from tbl limit 7 offset 3", 6)),
      () -> assertEquals("SELECT *\nFROM tbl\nLIMIT 3, 7;SELECT *\nFROM tbl\nLIMIT 3, 7", MgsUtils.adjustQueryLimit(DbType.mysql, "select * from tbl limit 7 offset 3;select * from tbl limit 7 offset 3", 9)),

      () -> assertEquals("SELECT *\nFROM tbl\nLIMIT 9", MgsUtils.adjustQueryLimit(DbType.mysql, "select * from tbl", 9)),

      () -> assertEquals("SELECT *\nFROM tbl\nWHERE rownum <= 9", MgsUtils.adjustQueryLimit(DbType.oracle, "select * from tbl", 9)),
      () -> assertEquals("SELECT *\nFROM tbl\nWHERE rownum <= 9;SELECT *\nFROM tbl\nWHERE rownum > 7", MgsUtils.adjustQueryLimit(DbType.oracle, "select * from tbl;select * from tbl where rownum > 7", 9))
    );
  }

  @Test
  void parseSqlType() {
    assertAll(
      () -> assertEquals(List0.newArrayList(SQLType.SELECT), MgsUtils.parseSqlType(DbType.mysql, "select * from tbl")),
      () -> assertEquals(List0.newArrayList(SQLType.UPDATE), MgsUtils.parseSqlType(DbType.mysql, "update tbl set col = 1")),
      () -> assertEquals(List0.newArrayList(SQLType.SELECT), MgsUtils.parseSqlType(DbType.oracle, "select * from tbl")),
      () -> assertEquals(List0.newArrayList(SQLType.UPDATE), MgsUtils.parseSqlType(DbType.oracle, "update tbl set col = 1"))
    );
  }

  @Test
  void parseTableOperation() {
    assertAll(
      () -> assertEquals(Tuple.of(List0.newArrayList(), List0.newArrayList()), MgsUtils.parseTableOperation(DbType.mysql, "select * from tbl")),
      () -> assertEquals(Tuple.of(List0.newArrayList(), List0.newArrayList("tbl")), MgsUtils.parseTableOperation(DbType.mysql, "drop table tbl")),
      () -> assertEquals(Tuple.of(List0.newArrayList(), List0.newArrayList()), MgsUtils.parseTableOperation(DbType.oracle, "select * from tbl")),
      () -> assertEquals(Tuple.of(List0.newArrayList(), List0.newArrayList("tbl")), MgsUtils.parseTableOperation(DbType.oracle, "drop table tbl"))
    );
  }

  @Test
  void parseTableStat() {
    String sameTableNameSql = "select * from S1.t1 union all select * from s1.T1 union all select * from c1.S1.T1";
    String starColumnSql = "select t.*, t.c from S1.t1 t union all select s.*, s.d from s1.T1 s";
    assertAll(
      () -> assertEquals(2, Tuple.getFirst(MgsUtils.parseTableStat(DbType.mysql, sameTableNameSql)).size()),
      () -> assertEquals("S1.t1", Tuple.getFirst(MgsUtils.parseTableStat(DbType.mysql, sameTableNameSql)).get(0).getRaw().toString()),
      () -> assertEquals("c1.S1.T1", Tuple.getFirst(MgsUtils.parseTableStat(DbType.mysql, sameTableNameSql)).get(1).getRaw().toString()),

      () -> assertEquals(3, Tuple.getSecond(MgsUtils.parseTableStat(DbType.mysql, starColumnSql)).size()),
      () -> assertEquals("MgsTableStatColumn(raw=S1.t1.*, table=s1.t1, name=*, fullName=s1.t1.*)", Tuple.getSecond(MgsUtils.parseTableStat(DbType.mysql, starColumnSql)).get(0).toString()),
      () -> assertEquals("MgsTableStatColumn(raw=S1.t1.c, table=s1.t1, name=c, fullName=s1.t1.c)", Tuple.getSecond(MgsUtils.parseTableStat(DbType.mysql, starColumnSql)).get(1).toString()),
      () -> assertEquals("MgsTableStatColumn(raw=s1.T1.d, table=s1.t1, name=d, fullName=s1.t1.d)", Tuple.getSecond(MgsUtils.parseTableStat(DbType.mysql, starColumnSql)).get(2).toString())
    );
  }
}
