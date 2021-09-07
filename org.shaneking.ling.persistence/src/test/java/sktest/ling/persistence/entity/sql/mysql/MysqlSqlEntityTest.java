package sktest.ling.persistence.entity.sql.mysql;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.entity.sql.mysql.MysqlSqlEntities;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.time.ZDT0;
import org.shaneking.ling.zero.util.UUID0;
import sktest.ling.persistence.entity.sql.AbstractDialectSqlEntityPrepare1;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MysqlSqlEntityTest extends SKUnit {

  String id = UUID0.cUl33();
  String userId = UUID0.cUl33();
  String dateTimeSssZone = ZDT0.on().dTSZ();

  MysqlSqlEntityPrepare1 mysqlSqlEntityPrepare1 = new MysqlSqlEntityPrepare1();

  @BeforeEach
  void beforeEach() {
    mysqlSqlEntityPrepare1.setHasLength("hasLength").setNoGetMethod("noGetMethod").setNotNullCol("notNullCol").setUniqueCol("uniqueCol").setWithoutAnnotation("withoutAnnotation").setReName("reName").setLongText("longText");
//    mysqlIdAdtVerEntity.setVersion(1).setLastModifyDateTime(ZDT0.on().dateTimesZone()).setLastModifyUserId(userId).setInvalid(String0.N).setId(id);
    mysqlSqlEntityPrepare1.initWithUidAndId(userId, id);
    mysqlSqlEntityPrepare1.setLmDsz(dateTimeSssZone);
  }

  @Test
  void createTableAndIndexIfNotExistSql() throws IOException {
    Files.write(tstOFiles().toPath(), mysqlSqlEntityPrepare1.createTableAndIndexIfNotExistSql().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), mysqlSqlEntityPrepare1.createTableAndIndexIfNotExistSql().trim());
  }

  @Test
  void testToString() {
//    assertEquals("{\"id\":\"" + id + "\",\"ivd\":\"N\",\"lmDsz\":\"" + dateTime + "\",\"lmUid\":\"" + userId + "\",\"ver\":0,\"hasLength\":\"hasLength\",\"notNullCol\":\"notNullCol\",\"uniqueCol\":\"uniqueCol\",\"withoutAnnotation\":\"withoutAnnotation\",\"reName\":\"reName\",\"longText\":\"longText\"}", OM3.writeValueAsString(mysqlIdAdtVerEntity));
    assertEquals("MysqlSqlEntityTest.MysqlSqlEntityPrepare1(super=AbstractDialectSqlEntityPrepare1(super=AbstractDialectSqlEntity(id=" + id + ", ver=0, dd=N, ivd=N, lmDsz=" + dateTimeSssZone + ", lmUid=" + userId + ", no=" + id + "), hasLength=hasLength, noGetMethod=noGetMethod, notNullCol=notNullCol, uniqueCol=uniqueCol, withoutAnnotation=withoutAnnotation, reName=reName, longText=longText))", mysqlSqlEntityPrepare1.toString());
  }

  @Accessors(chain = true)
  @Table(schema = "sktest1_schema", name = "sktest1_table", uniqueConstraints = {@UniqueConstraint(columnNames = {"has_length", "not_null_col"})})
  @ToString(callSuper = true)
  public static class MysqlSqlEntityPrepare1 extends AbstractDialectSqlEntityPrepare1 implements MysqlSqlEntities {
  }
}
