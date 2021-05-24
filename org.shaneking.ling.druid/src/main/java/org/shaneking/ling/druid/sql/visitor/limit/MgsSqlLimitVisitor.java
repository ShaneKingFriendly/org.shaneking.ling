package org.shaneking.ling.druid.sql.visitor.limit;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLLimit;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.expr.SQLNumberExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
import com.alibaba.druid.sql.visitor.SQLASTVisitorAdapter;
import lombok.Getter;
import lombok.Setter;

public class MgsSqlLimitVisitor extends SQLASTVisitorAdapter {
  @Getter
  @Setter
  private Integer rowCount;

  public MgsSqlLimitVisitor(Integer rowCount) {
    this.rowCount = rowCount;
  }

  @Override
  public boolean visit(SQLSelectQueryBlock x) {
    SQLLimit limit = x.getLimit();
    if (limit == null) {
      x.setLimit(new SQLLimit(new SQLNumberExpr(getRowCount())));
    } else {
      SQLExpr rowCountSqlExpr = limit.getRowCount();
      if (rowCountSqlExpr instanceof SQLIntegerExpr) {
        Number number = ((SQLIntegerExpr) rowCountSqlExpr).getNumber();
        if (number != null && (number.longValue() > getRowCount() || number.longValue() < 0)) {
          limit.setRowCount(getRowCount());
        }
      }
    }
    return false;
  }

  @Override
  public boolean visit(SQLUnionQuery x) {
    SQLSelectQuery rightSqlSelectQuery = x.getRight();
    if (rightSqlSelectQuery instanceof SQLSelectQueryBlock) {
      return visit((SQLSelectQueryBlock) rightSqlSelectQuery);
    }
    return false;
  }
}
