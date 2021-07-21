package sktest.ling.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.shaneking.ling.persistence.Condition;
import org.shaneking.ling.persistence.Keyword;
import org.shaneking.ling.persistence.Pagination;
import org.shaneking.ling.persistence.entity.Deleted;
import org.shaneking.ling.persistence.entity.Identified;
import org.shaneking.ling.persistence.entity.Versioned;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;
import sktest.ling.persistence.entity.sql.DialectSqlEntityPrepareDuplicateColumn;
import sktest.ling.persistence.entity.sql.DialectSqlEntityPrepareWithoutTableName;
import sktest.ling.persistence.entity.sql.dialectSqlEntityPrepareWithoutTableName2;
import sktest.ling.persistence.entity.sql.mysql.MysqlSqlEntityTest;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AbstractEntityTest extends SKUnit {
  String id = "1610866165373_KbTy6GDVwpB5rAYJjJb";
  String userId = "1610866165373_eXabaDd3OiEyivRv1GI";
  String dateTime = "2021-01-16 14:49:25";
  MysqlSqlEntityTest.DialectSqlEntityPrepareMysql dialectSqlEntityPrepareMysql = new MysqlSqlEntityTest.DialectSqlEntityPrepareMysql();
  @Mock
  private ResultSet resultSet;

  @BeforeEach
  void beforeEach() {
    dialectSqlEntityPrepareMysql.setHasLength("hasLength").setNoGetMethod("noGetMethod").setNotNullCol("notNullCol").setUniqueCol("uniqueCol").setWithoutAnnotation("withoutAnnotation").setReName("reName").setLongText("longText");
//    mysqlIdAdtVerEntity.setVersion(1).setLastModifyDateTime(Date0.on().dateTime()).setLastModifyUserId(userId).setInvalid(String0.N).setId(id);
    dialectSqlEntityPrepareMysql.initWithUserIdAndId(userId, id);
    dialectSqlEntityPrepareMysql.setLastModifyDateTime(dateTime);
  }


  @Test
  void initTableInfo() {
    DialectSqlEntityPrepareWithoutTableName abstractEntity = new DialectSqlEntityPrepareWithoutTableName();
    abstractEntity.initTableInfo();
    abstractEntity.nullSetter();
    assertEquals("DialectSqlEntityPrepareWithoutTableName(super=AbstractDialectSqlEntityPrepare(super=AbstractDialectSqlEntity(id=null, version=null, dd=null, invalid=null, lastModifyDateTime=null, lastModifyUserId=null, no=null), hasLength=null, noGetMethod=null, notNullCol=null, uniqueCol=null, withoutAnnotation=null, reName=null, longText=null))", abstractEntity.toString());
    dialectSqlEntityPrepareWithoutTableName2 dialectSqlEntityPrepareWithoutTableName2 = new dialectSqlEntityPrepareWithoutTableName2();
    dialectSqlEntityPrepareWithoutTableName2.initTableInfo();
  }

  @Test
  void initColumnInfo() {
    DialectSqlEntityPrepareDuplicateColumn dialectSqlEntityPrepareDuplicateColumn = new DialectSqlEntityPrepareDuplicateColumn();
    dialectSqlEntityPrepareDuplicateColumn.initColumnInfo(dialectSqlEntityPrepareDuplicateColumn.getClass());
    assertNotNull(dialectSqlEntityPrepareDuplicateColumn);
  }

  @Test
  void findHavingConditions() {
  }

  @Test
  void findWhereConditions() throws IOException {
    dialectSqlEntityPrepareMysql.forceWhereCondition("notNullCol").setOp(Keyword.BETWEEN).setCl(List0.newArrayList(String0.Y, String0.N));
    dialectSqlEntityPrepareMysql.forceWhereCondition("uniqueCol").setOp(Keyword.IN).setCl(List0.newArrayList(String0.Y, String0.N));
    dialectSqlEntityPrepareMysql.forceWhereCondition("reName").setOp(Keyword.LIKE).setCs(String0.Y);
    Files.write(tstOFiles().toPath(), dialectSqlEntityPrepareMysql.selectSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), dialectSqlEntityPrepareMysql.selectSql().toString());
  }

  @Test
  void fillOc() {
    MysqlSqlEntityTest.DialectSqlEntityPrepareMysql fillOc = new MysqlSqlEntityTest.DialectSqlEntityPrepareMysql();
    List<String> list = List0.newArrayList();
    List<Object> objectList = List0.newArrayList();
    assertAll(
      () -> assertThrows(NullPointerException.class, () -> fillOc.fillOc(null, null, null, null)),
      () -> assertThrows(NullPointerException.class, () -> fillOc.fillOc(List0.newArrayList(), null, null, null)),
      () -> assertDoesNotThrow(() -> fillOc.fillOc(list, objectList, new Condition().setOp(Keyword.IN).setSq("select id from tbl"), "notNullCol")),
      () -> assertDoesNotThrow(() -> fillOc.fillOc(list, objectList, new Condition().setOp(Keyword.BETWEEN).setCl(List0.newArrayList()), "notNullCol")),
      () -> assertDoesNotThrow(() -> fillOc.fillOc(list, objectList, new Condition().setOp(Keyword.IN).setCl(List0.newArrayList()), "notNullCol")),
      () -> assertDoesNotThrow(() -> fillOc.fillOc(list, objectList, new Condition().setOp(Keyword.IN).setCl(List0.newArrayList("")), "uniqueCol")),
      () -> assertNotNull(String0.DOT)
    );
  }

  @Test
  void fullTableName() {
  }

  @Test
  void deletedFullTableName() {
    assertEquals("sktest1_schema.sktest1_table_d", dialectSqlEntityPrepareMysql.deletedFullTableName());
  }

  @Test
  void lstSelectFiled() {
    MysqlSqlEntityTest.DialectSqlEntityPrepareMysql lstSelectFiled1 = new MysqlSqlEntityTest.DialectSqlEntityPrepareMysql();
    MysqlSqlEntityTest.DialectSqlEntityPrepareMysql lstSelectFiled2 = new MysqlSqlEntityTest.DialectSqlEntityPrepareMysql();
    lstSelectFiled2.srvSelectList(List0.newArrayList());
    MysqlSqlEntityTest.DialectSqlEntityPrepareMysql lstSelectFiled3 = new MysqlSqlEntityTest.DialectSqlEntityPrepareMysql();
    lstSelectFiled3.srvSelectList(List0.newArrayList(Identified.FIELD__ID));
    assertAll(
      () -> assertLinesMatch(List0.newArrayList(lstSelectFiled1.getFieldNameList()), lstSelectFiled1.lstSelectFiled()),
      () -> assertLinesMatch(List0.newArrayList(lstSelectFiled2.getFieldNameList()), lstSelectFiled2.lstSelectFiled()),
      () -> assertLinesMatch(List0.newArrayList(Identified.FIELD__ID), lstSelectFiled3.lstSelectFiled())
    );
  }

  @Test
  void mapRow() throws SQLException {
    DialectSqlEntityPrepareWithoutTableName dialectSqlEntityPrepareWithoutTableName = new DialectSqlEntityPrepareWithoutTableName();

    Mockito.when(resultSet.getString(Identified.FIELD__ID)).thenReturn(id);
    Mockito.when(resultSet.getString(Deleted.FIELD__DD)).thenReturn(null);
    Mockito.when(resultSet.getInt(Versioned.FIELD__VERSION)).thenReturn(1);
    dialectSqlEntityPrepareWithoutTableName.setSelectList(List0.newArrayList(Identified.FIELD__ID, Deleted.FIELD__DD, Versioned.FIELD__VERSION, String0.ALPHABET));
    dialectSqlEntityPrepareWithoutTableName.mapRow(resultSet);

    assertEquals("DialectSqlEntityPrepareWithoutTableName(super=AbstractDialectSqlEntityPrepare(super=AbstractDialectSqlEntity(id=1610866165373_KbTy6GDVwpB5rAYJjJb, version=1, dd=N, invalid=null, lastModifyDateTime=null, lastModifyUserId=null, no=null), hasLength=null, noGetMethod=null, notNullCol=null, uniqueCol=null, withoutAnnotation=null, reName=null, longText=null))", dialectSqlEntityPrepareWithoutTableName.toString());
  }

  @Test
  void deleteSql() throws IOException {
    Files.write(tstOFiles().toPath(), dialectSqlEntityPrepareMysql.deleteSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), dialectSqlEntityPrepareMysql.deleteSql().toString());
  }

  @Test
  void insertSql() throws IOException {
    Files.write(tstOFiles().toPath(), dialectSqlEntityPrepareMysql.insertSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), dialectSqlEntityPrepareMysql.insertSql().toString());
  }

  @Test
  void insertStatement() {
  }

  @Test
  void selectCountSql() throws IOException {
    Files.write(tstOFiles().toPath(), dialectSqlEntityPrepareMysql.selectCountSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), dialectSqlEntityPrepareMysql.selectCountSql().toString());
  }

  @Test
  void selectIdsSql() throws IOException {
    Files.write(tstOFiles().toPath(), dialectSqlEntityPrepareMysql.selectIdsSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), dialectSqlEntityPrepareMysql.selectIdsSql().toString());
  }

  @Test
  void selectSql() throws IOException {
    Files.write(tstOFiles().toPath(), dialectSqlEntityPrepareMysql.selectSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), dialectSqlEntityPrepareMysql.selectSql().toString());
  }

  @Test
  void selectStatement() {
  }

  @Test
  void updateSql() throws IOException {
    Files.write(tstOFiles().toPath(), dialectSqlEntityPrepareMysql.updateSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), dialectSqlEntityPrepareMysql.updateSql().toString());
  }

  @Test
  void updateStatement() {
  }

  @Test
  void fromStatement() {
  }

  @Test
  void groupByStatement() {
  }

  @Test
  void havingStatement() throws IOException {
    dialectSqlEntityPrepareMysql.setSelectList(List0.newArrayList("notNullCol", "uniqueCol", "reName"));
    dialectSqlEntityPrepareMysql.setGroupByList(List0.newArrayList("notNullCol", "uniqueCol", "reName"));
    dialectSqlEntityPrepareMysql.forceHavingCondition("notNullCol").setOp(Keyword.BETWEEN).setCl(List0.newArrayList(String0.Y, String0.N));
    dialectSqlEntityPrepareMysql.forceHavingCondition("uniqueCol").setOp(Keyword.IN).setCl(List0.newArrayList(String0.Y, String0.N));
    dialectSqlEntityPrepareMysql.forceHavingCondition("reName").setOp(Keyword.LIKE).setCs(String0.Y);
    Files.write(tstOFiles().toPath(), dialectSqlEntityPrepareMysql.selectSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), dialectSqlEntityPrepareMysql.selectSql().toString());
  }

  @Test
  void orderByStatement() throws IOException {
    dialectSqlEntityPrepareMysql.setOrderByList(List0.newArrayList("notNullCol", "uniqueCol", "reName"));
    dialectSqlEntityPrepareMysql.setPagination(new Pagination().setCount(100L).setPage(1).setSize(30));
    Files.write(tstOFiles().toPath(), dialectSqlEntityPrepareMysql.selectSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), dialectSqlEntityPrepareMysql.selectSql().toString());
  }

  @Test
  void whereEntityStatement() {
  }

  @Test
  void whereConditionsStatement() {
  }

  @Test
  void whereStatement() {
  }

  @Test
  void fieldNameValues() {
    MysqlSqlEntityTest.DialectSqlEntityPrepareMysql fieldNameValues = new MysqlSqlEntityTest.DialectSqlEntityPrepareMysql();

    assertAll(
      () -> assertLinesMatch(List0.newArrayList(Deleted.FIELD__DD), List0.newArrayList(fieldNameValues.fieldNameValues().keySet())),
      () -> assertLinesMatch(List0.newArrayList(String0.N), fieldNameValues.fieldNameValues().values().stream().map(String::valueOf).collect(Collectors.toList()))
    );
  }
}
