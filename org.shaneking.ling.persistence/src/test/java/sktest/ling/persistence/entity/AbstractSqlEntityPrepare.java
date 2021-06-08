package sktest.ling.persistence.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.persistence.Condition;
import org.shaneking.ling.persistence.entity.AbstractSqlEntity;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Accessors(chain = true)
@ToString(callSuper = true)
public abstract class AbstractSqlEntityPrepare extends AbstractSqlEntity<Map<String, Condition>> {
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

  public Map<String, String> genTableIdxMapExt() {
    return Map0.newHashMap("not_null_col", "not_null_col");
  }

  public Map<String, List<String>> genTableUniIdxMapExt() {
    return Map0.newHashMap("unique_col", List0.newArrayList("unique_col"));
  }

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
