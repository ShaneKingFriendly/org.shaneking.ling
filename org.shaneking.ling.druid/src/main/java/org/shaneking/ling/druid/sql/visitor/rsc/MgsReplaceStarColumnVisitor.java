package org.shaneking.ling.druid.sql.visitor.rsc;

import com.alibaba.druid.sql.visitor.SQLASTVisitor;

import java.util.List;
import java.util.Map;

public interface MgsReplaceStarColumnVisitor extends SQLASTVisitor {
  Map<String, List<String>> getRawTable2ColumnsMap();

  MgsReplaceStarColumnVisitor setRawTable2ColumnsMap(Map<String, List<String>> rawTable2ColumnsMap);
}
