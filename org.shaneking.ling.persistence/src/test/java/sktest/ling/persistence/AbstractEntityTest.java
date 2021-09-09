package sktest.ling.persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
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
import org.shaneking.ling.persistence.entity.sql.mysql.MysqlSqlEntities;
import org.shaneking.ling.persistence.entity.sql.sqllite.SqlliteSqlEntities;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;
import sktest.ling.persistence.entity.sql.AbstractDialectSqlEntityPrepare1;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AbstractEntityTest extends SKUnit {
  String dtz = "2021-01-16 14:49:25.123+0800";
  String id = "1610866165373_KbTy6GDVwpB5rAYJjJb";
  String userId = "1610866165373_eXabaDd3OiEyivRv1GI";
  AbstractEntityPrepare1 abstractEntityPrepare1 = new AbstractEntityPrepare1();
  @Mock
  private ResultSet resultSet;

  @BeforeEach
  void beforeEach() {
    abstractEntityPrepare1.setHasLength("hasLength").setNoGetMethod("noGetMethod").setNotNullCol("notNullCol").setUniqueCol("uniqueCol").setWithoutAnnotation("withoutAnnotation").setReName("reName").setLongText("longText");
//    mysqlIdAdtVerEntity.setVersion(1).setLastModifyDateTime(ZDT0.on().dateTimesZone()).setLastModifyUserId(userId).setInvalid(String0.N).setId(id);
    abstractEntityPrepare1.initWithUidAndId(userId, id);
    abstractEntityPrepare1.setLmDsz(dtz);
  }

  @Test
  void deletedFullTableName() {
    assertEquals("sktest1_schema.sktest1_table_d", abstractEntityPrepare1.deletedFullTableName());
  }

  @Test
  void deleteSql() throws IOException {
    Files.write(tstOFiles().toPath(), abstractEntityPrepare1.deleteSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), abstractEntityPrepare1.deleteSql().toString());
  }

  @Test
  void fieldNameValues() {
    AbstractEntityPrepare1 fieldNameValues = new AbstractEntityPrepare1();

    assertAll(
      () -> assertLinesMatch(List0.newArrayList(Deleted.FIELD__DD), List0.newArrayList(fieldNameValues.fieldNameValues().keySet())),
      () -> assertLinesMatch(List0.newArrayList(String0.N), fieldNameValues.fieldNameValues().values().stream().map(String::valueOf).collect(Collectors.toList()))
    );
  }

  @Test
  void fillOc() {
    AbstractEntityPrepare1 fillOc = new AbstractEntityPrepare1();
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
  void findHavingConditions() {
  }

  @Test
  void findWhereConditions() throws IOException {
    abstractEntityPrepare1.forceWhereCondition("notNullCol").setOp(Keyword.BETWEEN).setCl(List0.newArrayList(String0.Y, String0.N));
    abstractEntityPrepare1.forceWhereCondition("uniqueCol").setOp(Keyword.IN).setCl(List0.newArrayList(String0.Y, String0.N));
    abstractEntityPrepare1.forceWhereCondition("reName").setOp(Keyword.LIKE).setCs(String0.Y);
    Files.write(tstOFiles().toPath(), abstractEntityPrepare1.selectSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), abstractEntityPrepare1.selectSql().toString());
  }

  @Test
  void fromStatement() {
  }

  @Test
  void fullTableName() {
  }

  @Test
  void groupByStatement() {
  }

  @Test
  void havingStatement() throws IOException {
    abstractEntityPrepare1.setSelectList(List0.newArrayList("notNullCol", "uniqueCol", "reName"));
    abstractEntityPrepare1.setGroupByList(List0.newArrayList("notNullCol", "uniqueCol", "reName"));
    abstractEntityPrepare1.forceHavingCondition("notNullCol").setOp(Keyword.BETWEEN).setCl(List0.newArrayList(String0.Y, String0.N));
    abstractEntityPrepare1.forceHavingCondition("uniqueCol").setOp(Keyword.IN).setCl(List0.newArrayList(String0.Y, String0.N));
    abstractEntityPrepare1.forceHavingCondition("reName").setOp(Keyword.LIKE).setCs(String0.Y);
    Files.write(tstOFiles().toPath(), abstractEntityPrepare1.selectSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), abstractEntityPrepare1.selectSql().toString());
  }

  @Test
  void initColumnInfo() {
    AbstractEntityPrepare2 abstractEntityPrepare2 = new AbstractEntityPrepare2();
    abstractEntityPrepare2.initColumnInfo(abstractEntityPrepare2.getClass());
    assertNotNull(abstractEntityPrepare2);
  }

  @Test
  void initTableInfo() {
    AbstractEntityPrepare4 withoutTableName = new AbstractEntityPrepare4();
    withoutTableName.initTableInfo();
    withoutTableName.nullSetter();
    assertEquals("AbstractEntityTest.AbstractEntityPrepare4(super=AbstractDialectSqlEntityPrepare1(super=AbstractDialectSqlEntity(id=null, ver=null, dd=null, ivd=null, lmDsz=null, lmUid=null, no=null), hasLength=null, noGetMethod=null, notNullCol=null, uniqueCol=null, withoutAnnotation=null, reName=null, longText=null))", withoutTableName.toString());
    abstractEntityPrepare3 lowerClassName = new abstractEntityPrepare3();
    lowerClassName.initTableInfo();
  }

  @Test
  void insertSql() throws IOException {
    Files.write(tstOFiles().toPath(), abstractEntityPrepare1.insertSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), abstractEntityPrepare1.insertSql().toString());
  }

  @Test
  void insertStatement() {
  }

  @Test
  void lstSelectFiled() {
    AbstractEntityPrepare1 lstSelectFiled1 = new AbstractEntityPrepare1();
    AbstractEntityPrepare1 lstSelectFiled2 = new AbstractEntityPrepare1();
    lstSelectFiled2.srvSelectList(List0.newArrayList());
    AbstractEntityPrepare1 lstSelectFiled3 = new AbstractEntityPrepare1();
    lstSelectFiled3.srvSelectList(List0.newArrayList(Identified.FIELD__ID));
    assertAll(
      () -> assertLinesMatch(List0.newArrayList(lstSelectFiled1.getFieldNameList()), lstSelectFiled1.lstSelectFiled()),
      () -> assertLinesMatch(List0.newArrayList(lstSelectFiled2.getFieldNameList()), lstSelectFiled2.lstSelectFiled()),
      () -> assertLinesMatch(List0.newArrayList(Identified.FIELD__ID), lstSelectFiled3.lstSelectFiled())
    );
  }

  @Test
  void mapRow() throws SQLException {
    AbstractEntityPrepare4 abstractEntityPrepare4 = new AbstractEntityPrepare4();

    Mockito.when(resultSet.getString(Identified.FIELD__ID)).thenReturn(id);
    Mockito.when(resultSet.getString(Deleted.FIELD__DD)).thenReturn(null);
    Mockito.when(resultSet.getInt(Versioned.FIELD__VER)).thenReturn(1);
    abstractEntityPrepare4.setSelectList(List0.newArrayList(Identified.FIELD__ID, Deleted.FIELD__DD, Versioned.FIELD__VER, String0.ALPHABET));
    abstractEntityPrepare4.mapRow(resultSet);

    assertEquals("AbstractEntityTest.AbstractEntityPrepare4(super=AbstractDialectSqlEntityPrepare1(super=AbstractDialectSqlEntity(id=1610866165373_KbTy6GDVwpB5rAYJjJb, ver=1, dd=N, ivd=null, lmDsz=null, lmUid=null, no=null), hasLength=null, noGetMethod=null, notNullCol=null, uniqueCol=null, withoutAnnotation=null, reName=null, longText=null))", abstractEntityPrepare4.toString());
  }

  @Test
  void orderByStatement() throws IOException {
    abstractEntityPrepare1.setOrderByList(List0.newArrayList("notNullCol", "uniqueCol", "reName"));
    abstractEntityPrepare1.setPagination(new Pagination().setCount(100L).setPage(1).setSize(30));
    Files.write(tstOFiles().toPath(), abstractEntityPrepare1.selectSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), abstractEntityPrepare1.selectSql().toString());
  }

  @Test
  void selectCountSql() throws IOException {
    Files.write(tstOFiles().toPath(), abstractEntityPrepare1.selectCountSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), abstractEntityPrepare1.selectCountSql().toString());
  }

  @Test
  void selectIdsSql() throws IOException {
    Files.write(tstOFiles().toPath(), abstractEntityPrepare1.selectIdsSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), abstractEntityPrepare1.selectIdsSql().toString());
  }

  @Test
  void selectSql() throws IOException {
    Files.write(tstOFiles().toPath(), abstractEntityPrepare1.selectSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), abstractEntityPrepare1.selectSql().toString());
  }

  @Test
  void selectStatement() {
  }

  @Test
  void updateSql() throws IOException {
    Files.write(tstOFiles().toPath(), abstractEntityPrepare1.updateSql().toString().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), abstractEntityPrepare1.updateSql().toString());
  }

  @Test
  void updateStatement() {
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

  @Accessors(chain = true)
  @Table(schema = "sktest1_schema", name = "sktest1_table", uniqueConstraints = {@UniqueConstraint(columnNames = {"has_length", "not_null_col"})})
  @ToString(callSuper = true)
  public static class AbstractEntityPrepare1 extends AbstractDialectSqlEntityPrepare1 implements MysqlSqlEntities {
  }

  @Accessors(chain = true)
  @Table
  @ToString(callSuper = true)
  public static class AbstractEntityPrepare2 extends AbstractDialectSqlEntityPrepare1 implements SqlliteSqlEntities {

    @Column(nullable = false)
    @Getter
    @Setter
    private String notNullCol;
    @Column(nullable = false)
    @Getter
    @Id
    @Setter
    private String duplicateId;
    @Column(nullable = false)
    @Getter
    @Version
    @Setter
    private Integer duplicateVersion;
  }

  @Accessors(chain = true)
  @Table
  @ToString(callSuper = true)
  public static class abstractEntityPrepare3 extends AbstractDialectSqlEntityPrepare1 implements SqlliteSqlEntities {
  }

  @Accessors(chain = true)
  @Table
  @ToString(callSuper = true)
  public static class AbstractEntityPrepare4 extends AbstractDialectSqlEntityPrepare1 implements SqlliteSqlEntities {
  }
}
