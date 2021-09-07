package sktest.ling.persistence.entity;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.entity.sql.mysql.MysqlSqlEntities;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;

import javax.persistence.Index;
import javax.persistence.Table;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlEntitiesTest extends SKUnit {
  @Test
  void genTableIdxMap() throws IOException {
    Files.write(tstOFiles().toPath(), new SqlEntitiesPrepare1().createTableAndIndexIfNotExistSql().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), new SqlEntitiesPrepare1().createTableAndIndexIfNotExistSql().trim());
  }

  @Accessors(chain = true)
  @Table(schema = "sktest1_schema", name = "sktest1_table", indexes = {@Index(columnList = "not_null_col")})
  @ToString(callSuper = true)
  public static class SqlEntitiesPrepare1 extends AbstractSqlEntityPrepare1 implements MysqlSqlEntities {
  }
}
