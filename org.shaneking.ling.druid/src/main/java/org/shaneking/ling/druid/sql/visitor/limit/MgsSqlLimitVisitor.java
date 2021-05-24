package org.shaneking.ling.druid.sql.visitor.limit;

import com.alibaba.druid.sql.ast.SQLLimit;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
import com.alibaba.druid.sql.visitor.SQLASTVisitorAdapter;
import lombok.Getter;
import lombok.Setter;

public class MgsSqlLimitVisitor extends SQLASTVisitorAdapter {
  @Getter
  @Setter
  private Integer limitValue;

  public MgsSqlLimitVisitor(Integer limitValue) {
    this.limitValue = limitValue;
  }

  @Override
  public boolean visit(SQLSelectQueryBlock x) {
    SQLLimit limit = x.getLimit();
    if (limit == null) {
      //TODO
    }
    return false;
  }

  @Override
  public boolean visit(SQLUnionQuery x) {
    //TODO
    return false;
  }
}
