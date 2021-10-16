package sktest.ling.persistence.entity.sql.sqllite;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.entity.sql.sqllite.SqlliteSqlEntities;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.persistence.Pagination;
import org.shaneking.ling.zero.util.List0;
import sktest.ling.persistence.entity.sql.AbstractDialectSqlEntityPrepare1;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class SqlliteSqlEntityTest extends SKUnit {

  @Test
  void createTableAndIndexIfNotExistSql() throws IOException {
    Files.write(tstOFiles().toPath(), new SqlliteSqlEntityPrepare1().createTableAndIndexIfNotExistSql().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), new SqlliteSqlEntityPrepare1().createTableAndIndexIfNotExistSql().trim());
  }

  @Test
  void limitStatement() {
    SqlliteSqlEntityPrepare1 sqlliteSqlEntityPrepare1 = new SqlliteSqlEntityPrepare1();
    sqlliteSqlEntityPrepare1.setPagination(new Pagination().setRows(Pagination.MAX_SIZE));

    List<String> limit = List0.newArrayList();
    sqlliteSqlEntityPrepare1.limitStatement(limit, List0.newArrayList());
//    System.out.println(OM3.writeValueAsString(limit));
    assertLinesMatch(List0.newArrayList("limit 1023", "offset 0"), limit);
  }

  @Accessors(chain = true)
  @Table(schema = "sktest1_schema", name = "sktest1_table", uniqueConstraints = {@UniqueConstraint(columnNames = {"has_length", "not_null_col"})})
  @ToString(callSuper = true)
  public class SqlliteSqlEntityPrepare1 extends AbstractDialectSqlEntityPrepare1 implements SqlliteSqlEntities {
  }
}
