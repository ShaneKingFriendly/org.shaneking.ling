package org.shaneking.ling.druid;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLDropTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.dialect.clickhouse.visitor.ClickSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.hive.visitor.HiveSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.postgresql.visitor.PGSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLType;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import lombok.NonNull;
import org.shaneking.ling.druid.sql.dialect.oracle.visitor.limit.MgsOracleSqlLimitVisitor;
import org.shaneking.ling.druid.sql.visitor.limit.MgsSqlLimitVisitor;
import org.shaneking.ling.druid.stat.MgsTableStatColumn;
import org.shaneking.ling.druid.stat.MgsTableStatName;
import org.shaneking.ling.zero.persistence.Tuple;
import org.shaneking.ling.zero.util.List0;

import java.util.List;
import java.util.stream.Collectors;

public class MgsUtils {
  public static String adjustSqlLimit(@NonNull String dbType, @NonNull String sql, int limit) {
    return adjustSqlLimit(DbType.of(dbType), sql, limit);
  }

  public static String adjustSqlLimit(@NonNull DbType dbType, @NonNull String sql, int limit) {
    MgsSqlLimitVisitor sqlLimitVisitor = sqlLimitVisitor(dbType, limit);
    List<SQLStatement> sqlStatementList = SQLUtils.parseStatements(sql, dbType);
    for (SQLStatement sqlStatement : sqlStatementList) {
      sqlStatement.accept(sqlLimitVisitor);
    }
    return sqlStatementList.stream().map(SQLStatement::toString).collect(Collectors.joining());
  }

  public static int parseSqlLimit(@NonNull String dbType, @NonNull String sql) {
    return parseSqlLimit(DbType.of(dbType), sql);
  }

  public static int parseSqlLimit(@NonNull DbType dbType, @NonNull String sql) {
    return PagerUtils.getLimit(sql, dbType);
  }

  public static List<SQLType> parseSqlTypes(@NonNull String dbType, @NonNull String sql) {
    return parseSqlTypes(DbType.of(dbType), sql);
  }

  public static List<SQLType> parseSqlTypes(@NonNull DbType dbType, @NonNull String sql) {
    return SQLUtils.parseStatements(sql, dbType).stream().map(s -> SQLParserUtils.getSQLTypeV2(s.toString(), dbType)).collect(Collectors.toList());
  }

  public static Tuple.Pair<List<String>, List<String>> parseTableResult(@NonNull String dbType, @NonNull String sql) {
    return parseTableResult(DbType.of(dbType), sql);
  }

  public static Tuple.Pair<List<String>, List<String>> parseTableResult(@NonNull DbType dbType, @NonNull String sql) {
    List<SQLStatement> sqlStatementList = SQLUtils.parseStatements(sql, dbType);
    List<String> cList = List0.newArrayList();
    List<String> dList = List0.newArrayList();
    for (SQLStatement sqlStatement : sqlStatementList) {
      if (sqlStatement instanceof SQLCreateTableStatement) {
        SQLExprTableSource sqlExprTableSource = ((SQLCreateTableStatement) sqlStatement).getTableSource();
        if (sqlExprTableSource != null) {
          String lowerCaseTableName = sqlExprTableSource.toString().toLowerCase();
          dList.remove(lowerCaseTableName);
          if (!cList.contains(lowerCaseTableName)) {
            cList.add(lowerCaseTableName);
          }
        }
      } else if (sqlStatement instanceof SQLDropTableStatement) {
        List<SQLExprTableSource> sqlExprTableSources = ((SQLDropTableStatement) sqlStatement).getTableSources();
        for (SQLExprTableSource sqlExprTableSource : sqlExprTableSources) {
          if (sqlExprTableSource != null) {
            String lowerCaseTableName = sqlExprTableSource.toString().toLowerCase();
            cList.remove(lowerCaseTableName);
            if (!dList.contains(lowerCaseTableName)) {
              dList.add(lowerCaseTableName);
            }
          }
        }
      }
    }
    return Tuple.of(cList, dList);
  }

  public static Tuple.Triple<List<MgsTableStatName>, List<MgsTableStatColumn>, List<String>> parseTables(@NonNull String dbType, @NonNull String sql) {
    return parseTables(DbType.of(dbType), sql);
  }

  public static Tuple.Triple<List<MgsTableStatName>, List<MgsTableStatColumn>, List<String>> parseTables(@NonNull DbType dbType, @NonNull String sql) {
    List<SQLStatement> sqlStatementList = SQLUtils.parseStatements(sql, dbType);
    SchemaStatVisitor schemaStatVisitor = schemaStatVisitor(dbType);
    for (SQLStatement sqlStatement : sqlStatementList) {
      sqlStatement.accept(schemaStatVisitor);
    }
    return Tuple.of(schemaStatVisitor.getTables().keySet().stream().map(MgsTableStatName::new).collect(Collectors.toList())
      , schemaStatVisitor.getColumns().stream().map(MgsTableStatColumn::new).collect(Collectors.toList())
      , sqlStatementList.stream().filter(ss -> ss instanceof SQLCreateTableStatement && ((SQLCreateTableStatement) ss).getType() != null && ((SQLCreateTableStatement) ss).getName() != null)
        .map(ss -> ((SQLCreateTableStatement) ss).getName().toString().toLowerCase()).distinct().collect(Collectors.toList()));
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
      case presto:
      default:
        break;
    }
    if (rtn == null) {
      throw new IllegalArgumentException("unsupport type: " + dbType);
    }
    return rtn;
  }

  public static MgsSqlLimitVisitor sqlLimitVisitor(@NonNull DbType dbType, int limit) {
    MgsSqlLimitVisitor rtn = null;
    switch (dbType) {
      case oracle:
        rtn = new MgsOracleSqlLimitVisitor(limit);
        break;
      case clickhouse:
      case hive:
      case mysql:
      case postgresql:
      case presto:
        rtn = new MgsSqlLimitVisitor(limit);
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
