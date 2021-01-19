package sktest.ling.jsqlparser.util.deparser;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.jsqlparser.util.deparser.ExpressionDeParser3;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionDeParser3Test {

  @Test
  void addSchemaAliasPrefix() {
    assertAll(
      () -> assertEquals("sa.some_col", ExpressionDeParser3.addSchemaAliasPrefix("some_col", "sa")),
      () -> assertEquals("sa.some_col", ExpressionDeParser3.addSchemaAliasPrefix("some_tbl.some_col", "sa")),
      () -> assertEquals("some_func()", ExpressionDeParser3.addSchemaAliasPrefix("some_func()", "sa")),
      () -> assertEquals("some_schema.some_func()", ExpressionDeParser3.addSchemaAliasPrefix("some_schema.some_func()", "sa")),
      () -> assertEquals("some_schema.some_func(sa.some_col, 'p1', 'p2')", ExpressionDeParser3.addSchemaAliasPrefix("some_schema.some_func(some_col, 'p1', 'p2')", "sa"))
    );
  }
}
