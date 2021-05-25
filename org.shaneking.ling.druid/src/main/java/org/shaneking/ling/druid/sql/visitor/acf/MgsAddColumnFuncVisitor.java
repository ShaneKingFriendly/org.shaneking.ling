package org.shaneking.ling.druid.sql.visitor.acf;

import com.alibaba.druid.sql.visitor.SQLASTVisitor;

import java.util.Map;

public interface MgsAddColumnFuncVisitor extends SQLASTVisitor {
  Map<String, String> getRawColumn2FuncMap();

  MgsAddColumnFuncVisitor setRawColumn2FuncMap(Map<String, String> rawColumn2FuncMap);
}
