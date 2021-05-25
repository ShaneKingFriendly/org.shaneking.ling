package org.shaneking.ling.druid.sql.dialect.oracle.visitor.aql;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
import org.shaneking.ling.druid.sql.visitor.aql.MgsAdjustQueryLimitVisitor;
import org.shaneking.ling.zero.util.List0;

import java.util.List;

public class MgsOracleAdjustQueryLimitVisitor extends MgsAdjustQueryLimitVisitor {
  public static final String ROWNUM_LOWERCASE = "rownum";

  public MgsOracleAdjustQueryLimitVisitor(Integer rowCount) {
    super(rowCount);
  }

  @Override
  public boolean visit(SQLSelectQueryBlock x) {
    if (x.getOrderBy() != null) {//order by must wrap
      SQLObject parent = x.getParent();
      if (parent instanceof SQLSelect) {
        wrapRownumSelect((SQLSelect) parent);
      }
    } else {
      SQLExpr where = x.getWhere();
      if (where == null) {
        x.setWhere(new SQLBinaryOpExpr(new SQLIdentifierExpr(ROWNUM_LOWERCASE), SQLBinaryOperator.LessThanOrEqual, new SQLIntegerExpr(getRowCount())));
      } else {
        if (where instanceof SQLBinaryOpExpr) {
          SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) where;
          List<SQLBinaryOpExpr> rownumExprList = searchRownumExpr(sqlBinaryOpExpr, List0.newArrayList());
          if (rownumExprList.isEmpty()) {
            x.setWhere(new SQLBinaryOpExpr(new SQLIdentifierExpr(ROWNUM_LOWERCASE), SQLBinaryOperator.LessThanOrEqual, new SQLIntegerExpr(getRowCount())));
          } else {
            for (SQLBinaryOpExpr rownumExpr : rownumExprList) {
              if (rownumExpr.getLeft() instanceof SQLIntegerExpr) {
                Number number = ((SQLIntegerExpr) rownumExpr.getLeft()).getNumber();
                if (number != null && (number.longValue() > getRowCount() || number.longValue() < 0)) {
                  rownumExpr.setLeft(new SQLIntegerExpr(getRowCount()));
                }
              }
              if (rownumExpr.getRight() instanceof SQLIntegerExpr) {
                Number number = ((SQLIntegerExpr) rownumExpr.getRight()).getNumber();
                if (number != null && (number.longValue() > getRowCount() || number.longValue() < 0)) {
                  rownumExpr.setRight(new SQLIntegerExpr(getRowCount()));
                }
              }
            }
          }
        }
      }
    }
    return false;
  }

  private List<SQLBinaryOpExpr> searchRownumExpr(SQLBinaryOpExpr sqlBinaryOpExpr, List<SQLBinaryOpExpr> rownumExprList) {
    if (whetherRownumExpr(sqlBinaryOpExpr.getLeft()) || whetherRownumExpr(sqlBinaryOpExpr.getRight())) {
      rownumExprList.add(sqlBinaryOpExpr);
    } else {
      if (sqlBinaryOpExpr.getLeft() instanceof SQLBinaryOpExpr) {
        searchRownumExpr((SQLBinaryOpExpr) sqlBinaryOpExpr.getLeft(), rownumExprList);
      }
      if (sqlBinaryOpExpr.getRight() instanceof SQLBinaryOpExpr) {
        searchRownumExpr((SQLBinaryOpExpr) sqlBinaryOpExpr.getRight(), rownumExprList);
      }
    }
    return rownumExprList;
  }

  private boolean whetherRownumExpr(SQLExpr sqlExpr) {
    return sqlExpr instanceof SQLIdentifierExpr && ROWNUM_LOWERCASE.equals(((SQLIdentifierExpr) sqlExpr).getLowerName());
  }

  @Override
  public boolean visit(SQLUnionQuery x) {
    SQLObject parent = x.getParent();
    if (parent instanceof SQLSelect) {
      wrapRownumSelect((SQLSelect) parent);
    }
    return false;
  }

  private void wrapRownumSelect(SQLSelect sqlSelect) {
    SQLObject parentSelect = sqlSelect.getParent();
    if (parentSelect instanceof SQLSelectStatement) {
      SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) parentSelect;
      SQLSelectQueryBlock sqlSelectQueryBlock = new SQLSelectQueryBlock();
      sqlSelectQueryBlock.addSelectItem(new SQLAllColumnExpr());
      sqlSelectQueryBlock.setFrom(sqlSelect, "t");
      sqlSelectQueryBlock.setWhere(new SQLBinaryOpExpr(new SQLIdentifierExpr(ROWNUM_LOWERCASE), SQLBinaryOperator.LessThanOrEqual, new SQLIntegerExpr(getRowCount())));
      sqlSelectStatement.setSelect(new SQLSelect(sqlSelectQueryBlock));
    }
  }
}
