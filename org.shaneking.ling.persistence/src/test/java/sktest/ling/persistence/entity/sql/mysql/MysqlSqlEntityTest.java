package sktest.ling.persistence.entity.sql.mysql;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.entity.sql.mysql.MysqlSqlEntities;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Date0;
import org.shaneking.ling.zero.util.UUID0;
import sktest.ling.persistence.entity.AbstractSqlEntityPrepare;
import sktest.ling.persistence.entity.sql.AbstractDialectSqlEntityPrepare;

import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MysqlSqlEntityTest extends SKUnit {

  String id = UUID0.cUl33();
  String userId = UUID0.cUl33();
  String dateTime = Date0.on().dateTime();

  DialectSqlEntityPrepareMysql dialectSqlEntityPrepareMysql = new DialectSqlEntityPrepareMysql();

  @BeforeEach
  void beforeEach() {
    dialectSqlEntityPrepareMysql.setHasLength("hasLength").setNoGetMethod("noGetMethod").setNotNullCol("notNullCol").setUniqueCol("uniqueCol").setWithoutAnnotation("withoutAnnotation").setReName("reName").setLongText("longText");
//    mysqlIdAdtVerEntity.setVersion(1).setLastModifyDateTime(Date0.on().dateTime()).setLastModifyUserId(userId).setInvalid(String0.N).setId(id);
    dialectSqlEntityPrepareMysql.initWithUserIdAndId(userId, id);
    dialectSqlEntityPrepareMysql.setLastModifyDateTime(dateTime);
  }

  @Test
  void createTableAndIndexIfNotExistSql() throws IOException {
    Files.write(tstOFiles().toPath(), dialectSqlEntityPrepareMysql.createTableAndIndexIfNotExistSql().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), dialectSqlEntityPrepareMysql.createTableAndIndexIfNotExistSql().trim());
  }

  @Test
  void testToString() {
//    assertEquals("{\"id\":\"" + id + "\",\"invalid\":\"N\",\"lastModifyDateTime\":\"" + dateTime + "\",\"lastModifyUserId\":\"" + userId + "\",\"version\":0,\"hasLength\":\"hasLength\",\"notNullCol\":\"notNullCol\",\"uniqueCol\":\"uniqueCol\",\"withoutAnnotation\":\"withoutAnnotation\",\"reName\":\"reName\",\"longText\":\"longText\"}", OM3.writeValueAsString(mysqlIdAdtVerEntity));
    assertEquals("MysqlSqlEntityTest.DialectSqlEntityPrepareMysql(super=AbstractDialectSqlEntityPrepare(super=AbstractDialectSqlEntity(id=" + id + ", dd=N, no=" + id + ", invalid=N, lastModifyDateTime=" + dateTime + ", lastModifyUserId=" + userId + ", version=0), hasLength=hasLength, noGetMethod=noGetMethod, notNullCol=notNullCol, uniqueCol=uniqueCol, withoutAnnotation=withoutAnnotation, reName=reName, longText=longText))", dialectSqlEntityPrepareMysql.toString());
  }

  @Accessors(chain = true)
  @Table(schema = "sktest1_schema", name = "sktest1_table", uniqueConstraints = {@UniqueConstraint(columnNames = {"has_length", "not_null_col"})})
  @ToString(callSuper = true)
  public static class DialectSqlEntityPrepareMysql extends AbstractDialectSqlEntityPrepare implements MysqlSqlEntities {
  }

  @Accessors(chain = true)
  @Table(schema = "sktest1_schema", name = "sktest1_table", indexes = {@Index(columnList = "not_null_col")})
  @ToString(callSuper = true)
  public static class SqlEntityPrepareMysql extends AbstractSqlEntityPrepare implements MysqlSqlEntities {
  }
}
