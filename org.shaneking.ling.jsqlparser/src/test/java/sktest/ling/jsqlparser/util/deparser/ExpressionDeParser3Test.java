package sktest.ling.jsqlparser.util.deparser;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.jsqlparser.util.deparser.ExpressionDeParser3;
import org.shaneking.ling.test.SKUnit;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionDeParser3Test extends SKUnit {

  @Test
  void constructor() {
    assertAll(
      () -> assertNotNull(new ExpressionDeParser3())
    );
  }

  @Test
  void addSchemaAliasPrefix() {
    assertAll(
      () -> assertEquals("sa.some_col", ExpressionDeParser3.addSchemaAliasPrefix("some_col", "sa")),
      () -> assertEquals("some_func()", ExpressionDeParser3.addSchemaAliasPrefix("some_func()", "sa")),
      () -> assertEquals("some_schema.some_func()", ExpressionDeParser3.addSchemaAliasPrefix("some_schema.some_func()", "sa")),
      () -> assertEquals("some_schema.some_func(sa.some_col, 'p1', 'p2')", ExpressionDeParser3.addSchemaAliasPrefix("some_schema.some_func(some_col, 'p1', 'p2')", "sa")),
      () -> assertEquals("sa.some_col", ExpressionDeParser3.addSchemaAliasPrefix("some_tbl.some_col", "sa"))
    );
  }
}
