package sktest.ling.persistence.entity;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;
import sktest.ling.persistence.entity.sql.mysql.MysqlSqlEntityTest;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlEntitiesTest extends SKUnit {
  @Test
  void genTableIdxMap() throws IOException {
    Files.write(tstOFiles().toPath(), new MysqlSqlEntityTest.SqlEntityPrepareMysql().createTableAndIndexIfNotExistSql().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), new MysqlSqlEntityTest.SqlEntityPrepareMysql().createTableAndIndexIfNotExistSql().trim());
  }
}
