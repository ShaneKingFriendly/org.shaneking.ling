package org.shaneking.ling.druid.sql.visiter;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.clickhouse.visitor.ClickSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.hive.visitor.HiveSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.postgresql.visitor.PGSchemaStatVisitor;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import lombok.NonNull;
import org.shaneking.ling.druid.stat.TableStatColumn;
import org.shaneking.ling.druid.stat.TableStatName;
import org.shaneking.ling.zero.persistence.Tuple;

import java.util.List;
import java.util.stream.Collectors;

public class SchemaStatVisitorUtils {
  public static Tuple.Pair<List<TableStatName>, List<TableStatColumn>> parseTable(@NonNull String dbType, @NonNull String sql) {
    return parseTable(DbType.of(dbType), sql);
  }

  public static Tuple.Pair<List<TableStatName>, List<TableStatColumn>> parseTable(@NonNull DbType dbType, @NonNull String sql) {
    List<SQLStatement> sqlStatementList = SQLUtils.parseStatements(sql, dbType);
    SchemaStatVisitor schemaStatVisitor = schemaStatVisitor(dbType);
    for (SQLStatement sqlStatement : sqlStatementList) {
      sqlStatement.accept(schemaStatVisitor);
    }
    return Tuple.of(schemaStatVisitor.getTables().keySet().stream().map(TableStatName::new).collect(Collectors.toList())
      , schemaStatVisitor.getColumns().stream().map(TableStatColumn::new).collect(Collectors.toList()));
  }

  public static SchemaStatVisitor schemaStatVisitor(@NonNull DbType dbType) {
    SchemaStatVisitor rtn = null;
    switch (dbType) {
      case clickhouse:
        rtn = new ClickSchemaStatVisitor();
        break;
      case hive:
        rtn = new HiveSchemaStatVisitor();
        break;
      case mysql:
        rtn = new MySqlSchemaStatVisitor();
        break;
      case oracle:
        rtn = new OracleSchemaStatVisitor();
        break;
      case postgresql:
        rtn = new PGSchemaStatVisitor();
        break;
      default:
        break;
    }
    if (rtn == null) {
      throw new IllegalArgumentException("unsupport type: " + dbType);
    }
    return rtn;
  }
}
