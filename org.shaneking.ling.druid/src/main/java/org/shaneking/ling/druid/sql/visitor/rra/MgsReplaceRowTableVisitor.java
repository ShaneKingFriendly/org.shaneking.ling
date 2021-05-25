package org.shaneking.ling.druid.sql.visitor.rra;

import com.alibaba.druid.sql.visitor.SQLASTVisitor;

import java.util.Map;

public interface MgsReplaceRowTableVisitor extends SQLASTVisitor {
  Map<String, String> getRawTable2SqlMap();

  MgsReplaceRowTableVisitor setRawTable2SqlMap(Map<String, String> rawTable2SqlMap);
}
