package org.shaneking.ling.jsqlparser.util.deparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;

public class ExpressionDeParser3 {
  public static String addSchemaAliasPrefix(String expr, String schemaAlias) throws JSQLParserException {
    Expression expression = CCJSqlParserUtil.parseExpression(expr);
    ExpressionDeParser expressionDeParser = new ExpressionDeParser() {
      @Override
      public void visit(Column tableColumn) {
        Table table = tableColumn.getTable();
        if (table == null) {
          table = new Table();
          tableColumn.setTable(table);
        }
        table.setAlias(new Alias(schemaAlias));
        super.visit(tableColumn);
      }
    };
    expression.accept(expressionDeParser);
    return expression.toString();
  }
}
