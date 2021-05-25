package org.shaneking.ling.druid;

import com.alibaba.druid.DbType;
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
import org.shaneking.ling.druid.sql.dialect.oracle.visitor.aql.MgsOracleAdjustQueryLimitVisitor;
import org.shaneking.ling.druid.sql.visitor.acf.MgsAddColumnFuncVisitor;
import org.shaneking.ling.druid.sql.visitor.aql.MgsAdjustQueryLimitVisitor;
import org.shaneking.ling.druid.sql.visitor.rra.MgsReplaceRowTableVisitor;
import org.shaneking.ling.druid.sql.visitor.rsc.MgsReplaceStarColumnVisitor;
import org.shaneking.ling.druid.stat.MgsTableStatColumn;
import org.shaneking.ling.druid.stat.MgsTableStatName;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.persistence.Tuple;
import org.shaneking.ling.zero.util.List0;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MgsUtils {
  public static String addColumnFunc(@NonNull String dbType, @NonNull String sql, @NonNull Map<String, String> rawColumn2FuncMap) {
    return addColumnFunc(DbType.of(dbType), sql, rawColumn2FuncMap);
  }

  public static String addColumnFunc(@NonNull DbType dbType, @NonNull String sql, @NonNull Map<String, String> rawColumn2FuncMap) {
    MgsAddColumnFuncVisitor addColumnFuncVisitor = mgsAddColumnFuncVisitor(dbType, rawColumn2FuncMap);
    List<SQLStatement> sqlStatementList = SQLUtils.parseStatements(sql, dbType);
    for (SQLStatement sqlStatement : sqlStatementList) {
      sqlStatement.accept(addColumnFuncVisitor);
    }
    return sqlStatementList.stream().map(SQLStatement::toString).collect(Collectors.joining(String0.BR_LINUX));
  }

  public static String adjustQueryLimit(@NonNull String dbType, @NonNull String sql, int limit) {
    return adjustQueryLimit(DbType.of(dbType), sql, limit);
  }

  public static String adjustQueryLimit(@NonNull DbType dbType, @NonNull String sql, int limit) {
    MgsAdjustQueryLimitVisitor sqlLimitVisitor = mgsAdjustSqlLimitVisitor(dbType, limit);
    List<SQLStatement> sqlStatementList = SQLUtils.parseStatements(sql, dbType);
    for (SQLStatement sqlStatement : sqlStatementList) {
      sqlStatement.accept(sqlLimitVisitor);
    }
    return sqlStatementList.stream().map(SQLStatement::toString).collect(Collectors.joining(String0.BR_LINUX));
  }

  public static MgsAddColumnFuncVisitor mgsAddColumnFuncVisitor(DbType dbType, Map<String, String> rawColumn2FuncMap) {
    //TODO
    return null;
  }

  public static MgsAdjustQueryLimitVisitor mgsAdjustSqlLimitVisitor(@NonNull DbType dbType, int limit) {
    MgsAdjustQueryLimitVisitor rtn = null;
    switch (dbType) {
      case oracle:
        rtn = new MgsOracleAdjustQueryLimitVisitor(limit);
        break;
      case clickhouse:
      case hive:
      case mysql:
      case postgresql:
      case presto:
        rtn = new MgsAdjustQueryLimitVisitor(limit);
        break;
      default:
        break;
    }
    if (rtn == null) {
      throw new IllegalArgumentException("unsupport type: " + dbType);
    }
    return rtn;
  }

  public static SchemaStatVisitor mgsParseTableStatVisitor(@NonNull DbType dbType) {
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

  public static MgsReplaceRowTableVisitor mgsReplaceRowTableVisitor(DbType dbType, Map<String, String> rawTable2SqlMap) {
    //TODO
    return null;
  }

  public static MgsReplaceStarColumnVisitor mgsReplaceStarColumnVisitor(DbType dbType, Map<String, List<String>> rawTable2ColumnsMap) {
    //TODO
    return null;
  }

  public static List<SQLType> parseSqlType(@NonNull String dbType, @NonNull String sql) {
    return parseSqlType(DbType.of(dbType), sql);
  }

  public static List<SQLType> parseSqlType(@NonNull DbType dbType, @NonNull String sql) {
    return SQLUtils.parseStatements(sql, dbType).stream().map(s -> SQLParserUtils.getSQLTypeV2(s.toString(), dbType)).collect(Collectors.toList());
  }

  public static Tuple.Pair<List<String>, List<String>> parseTableOperation(@NonNull String dbType, @NonNull String sql) {
    return parseTableOperation(DbType.of(dbType), sql);
  }

  public static Tuple.Pair<List<String>, List<String>> parseTableOperation(@NonNull DbType dbType, @NonNull String sql) {
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

  public static Tuple.Triple<List<MgsTableStatName>, List<MgsTableStatColumn>, List<String>> parseTableStat(@NonNull String dbType, @NonNull String sql) {
    return parseTableStat(DbType.of(dbType), sql);
  }

  public static Tuple.Triple<List<MgsTableStatName>, List<MgsTableStatColumn>, List<String>> parseTableStat(@NonNull DbType dbType, @NonNull String sql) {
    List<SQLStatement> sqlStatementList = SQLUtils.parseStatements(sql, dbType);
    SchemaStatVisitor schemaStatVisitor = mgsParseTableStatVisitor(dbType);
    for (SQLStatement sqlStatement : sqlStatementList) {
      sqlStatement.accept(schemaStatVisitor);
    }
    return Tuple.of(schemaStatVisitor.getTables().keySet().stream().map(MgsTableStatName::new).collect(Collectors.toList())
      , schemaStatVisitor.getColumns().stream().map(MgsTableStatColumn::new).collect(Collectors.toList())
      , sqlStatementList.stream().filter(ss -> ss instanceof SQLCreateTableStatement && ((SQLCreateTableStatement) ss).getType() != null && ((SQLCreateTableStatement) ss).getName() != null)
        .map(ss -> ((SQLCreateTableStatement) ss).getName().toString().toLowerCase()).distinct().collect(Collectors.toList()));
  }

  public static String replaceRowTable(@NonNull String dbType, @NonNull String sql, @NonNull Map<String, String> rawTable2SqlMap) {
    return replaceRowTable(DbType.of(dbType), sql, rawTable2SqlMap);
  }

  public static String replaceRowTable(@NonNull DbType dbType, @NonNull String sql, @NonNull Map<String, String> rawTable2SqlMap) {
    MgsReplaceRowTableVisitor replaceRowTableVisitor = mgsReplaceRowTableVisitor(dbType, rawTable2SqlMap);
    List<SQLStatement> sqlStatementList = SQLUtils.parseStatements(sql, dbType);
    for (SQLStatement sqlStatement : sqlStatementList) {
      sqlStatement.accept(replaceRowTableVisitor);
    }
    return sqlStatementList.stream().map(SQLStatement::toString).collect(Collectors.joining(String0.BR_LINUX));
  }

  public static String replaceStarColumn(@NonNull String dbType, @NonNull String sql, @NonNull Map<String, List<String>> rawTable2ColumnsMap) {
    return replaceStarColumn(DbType.of(dbType), sql, rawTable2ColumnsMap);
  }

  public static String replaceStarColumn(@NonNull DbType dbType, @NonNull String sql, @NonNull Map<String, List<String>> rawTable2ColumnsMap) {
    MgsReplaceStarColumnVisitor replaceStarColumnVisitor = mgsReplaceStarColumnVisitor(dbType, rawTable2ColumnsMap);
    List<SQLStatement> sqlStatementList = SQLUtils.parseStatements(sql, dbType);
    for (SQLStatement sqlStatement : sqlStatementList) {
      sqlStatement.accept(replaceStarColumnVisitor);
    }
    return sqlStatementList.stream().map(SQLStatement::toString).collect(Collectors.joining(String0.BR_LINUX));
  }
}
