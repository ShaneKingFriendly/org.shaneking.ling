package sktest.ling.persistence.sql.entity.mysql;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.sql.Condition;
import org.shaneking.ling.persistence.sql.entity.IdAdtVerEntity;
import org.shaneking.ling.persistence.sql.entity.mysql.MySqler;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Date0;
import org.shaneking.ling.zero.util.Map0;
import org.shaneking.ling.zero.util.UUID0;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MysqlIdAdtVerEntityTest extends SKUnit {

  String id = UUID0.cUl33();
  String userId = UUID0.cUl33();
  String dateTime = Date0.on().dateTime();

  Test4MysqlIdAdtVerEntity mysqlIdAdtVerEntity = new Test4MysqlIdAdtVerEntity();

  @BeforeEach
  void beforeEach() {
    mysqlIdAdtVerEntity.setHasLength("hasLength").setNoGetMethod("noGetMethod").setNotNullCol("notNullCol").setUniqueCol("uniqueCol").setWithoutAnnotation("withoutAnnotation").setReName("reName").setLongText("longText");
//    mysqlIdAdtVerEntity.setVersion(1).setLastModifyDateTime(Date0.on().dateTime()).setLastModifyUserId(userId).setInvalid(String0.N).setId(id);
    mysqlIdAdtVerEntity.initWithUserIdAndId(userId, id);
    mysqlIdAdtVerEntity.setLastModifyDateTime(dateTime);
  }

  @Test
  void createTableIfNotExistSql() throws IOException {
//    Files.write(tstOFiles().toPath(), mysqlIdAdtVerEntity.createTableIfNotExistSql().getBytes());
    assertEquals(String.join(String0.BR_LINUX, Files.readAllLines(tstOFiles().toPath())), mysqlIdAdtVerEntity.createTableIfNotExistSql().trim());
  }

  @Test
  void testToString() {
//    assertEquals("{\"id\":\"" + id + "\",\"invalid\":\"N\",\"lastModifyDateTime\":\"" + dateTime + "\",\"lastModifyUserId\":\"" + userId + "\",\"version\":0,\"hasLength\":\"hasLength\",\"notNullCol\":\"notNullCol\",\"uniqueCol\":\"uniqueCol\",\"withoutAnnotation\":\"withoutAnnotation\",\"reName\":\"reName\",\"longText\":\"longText\"}", OM3.writeValueAsString(mysqlIdAdtVerEntity));
    assertEquals("MysqlIdAdtVerEntityTest.Test4MysqlIdAdtVerEntity(super=IdAdtVerEntity(super=IdAdtEntity(super=IdEntity(id=" + id + "), invalid=N, lastModifyDateTime=" + dateTime + ", lastModifyUserId=" + userId + "), version=0), hasLength=hasLength, noGetMethod=noGetMethod, notNullCol=notNullCol, uniqueCol=uniqueCol, withoutAnnotation=withoutAnnotation, reName=reName, longText=longText)", mysqlIdAdtVerEntity.toString());
  }

  @Accessors(chain = true)
  @Table(schema = "sktest1_schema", name = "sktest1_table", uniqueConstraints = {@UniqueConstraint(columnNames = {"has_length", "not_null_col"})})
  @ToString(callSuper = true)
  public static class Test4MysqlIdAdtVerEntity extends IdAdtVerEntity<Map<String, Condition>> implements MySqler {
    @Column(length = 10)
    @Getter
    @Setter
    private String hasLength;

    @Column
    @Setter
    private String noGetMethod;

    @Column(nullable = false)
    @Getter
    @Setter
    private String notNullCol;

    @Column(unique = true)
    @Getter
    @Setter
    private String uniqueCol;

    @Getter
    @Setter
    private String withoutAnnotation;

    @Column(name = "re_name_col")
    @Getter
    @Setter
    private String reName;

    @Column
    @Getter
    @Lob
    @Setter
    private String longText;

    @Override
    public @NonNull List<Condition> findHavingConditions(@NonNull String fieldName) {
      Map<String, Condition> ocMap = this.getHavingConditions();
      if (ocMap == null) {
        ocMap = Map0.newHashMap();
        this.setHavingConditions(ocMap);
      }
      return ocMap.keySet().parallelStream().filter(Objects::nonNull).filter(s -> s.equals(fieldName) || s.startsWith(fieldName + String0.UNDERLINE + String0.UNDERLINE)).map(s -> this.getHavingConditions().get(s)).collect(Collectors.toList());
    }

    @Override
    public @NonNull List<Condition> findWhereConditions(@NonNull String fieldName) {
      Map<String, Condition> ocMap = this.getWhereConditions();
      if (ocMap == null) {
        ocMap = Map0.newHashMap();
        this.setWhereConditions(ocMap);
      }
      return ocMap.keySet().parallelStream().filter(Objects::nonNull).filter(s -> s.equals(fieldName) || s.startsWith(fieldName + String0.UNDERLINE + String0.UNDERLINE)).map(s -> this.getWhereConditions().get(s)).collect(Collectors.toList());
    }

    public Condition forceHavingCondition(@NonNull String field) {
      Map<String, Condition> conditionMap = this.getHavingConditions();
      if (conditionMap == null) {
        conditionMap = Map0.newHashMap();
        this.setHavingConditions(conditionMap);
      }
      return forceCondition(conditionMap, field);
    }

    public Condition forceCondition(@NonNull Map<String, Condition> conditionMap, @NonNull String field) {
      Condition condition = conditionMap.get(field);
      if (condition == null) {
        condition = new Condition();
        conditionMap.put(field, condition);
      }
      return condition;
    }

    public Condition forceWhereCondition(@NonNull String field) {
      Map<String, Condition> conditionMap = this.getWhereConditions();
      if (conditionMap == null) {
        conditionMap = Map0.newHashMap();
        this.setWhereConditions(conditionMap);
      }
      return forceCondition(conditionMap, field);
    }
  }
}
