package org.shaneking.ling.persistence.entity;

import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.persistence.AbstractEntity;
import org.shaneking.ling.persistence.Condition;
import org.shaneking.ling.persistence.Keyword;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.persistence.Tuple;
import org.shaneking.ling.zero.util.List0;

import java.util.List;
import java.util.stream.Collectors;

@Accessors(chain = true)
@Slf4j
public abstract class AbstractSqlEntity<J> extends AbstractEntity<J> implements SqlEntities {
  //crud
  public Tuple.Pair<String, List<Object>> deleteSql() {
    List<Object> rtnObjectList = List0.newArrayList();

    List<String> sqlList = List0.newArrayList();
    sqlList.add(Keyword.DELETE_FROM);
    sqlList.add(this.fullTableName());

    List<String> whereList = List0.newArrayList();
    whereStatement(whereList, rtnObjectList);
    if (whereList.size() > 0) {
      sqlList.add(Keyword.WHERE);
      sqlList.add(String.join(String0.wrapBlack(Keyword.AND), whereList));
    }
    return Tuple.of(String.join(String0.BLANK, sqlList), rtnObjectList);
  }

  public Tuple.Pair<String, List<Object>> insertSql() {
    List<Object> rtnObjectList = List0.newArrayList();

    List<String> insertList = List0.newArrayList();
    insertStatement(insertList, rtnObjectList);

    List<String> sqlList = List0.newArrayList();
    sqlList.add(Keyword.INSERT_INFO);
    sqlList.add(this.fullTableName());
    sqlList.add(String0.OPEN_PARENTHESIS + String.join(String0.COMMA, insertList) + String0.CLOSE_PARENTHESIS);
    sqlList.add(Keyword.VALUES);
    sqlList.add(String0.OPEN_PARENTHESIS + String0.repeat(String0.COMMA + String0.QUESTION, insertList.size()).substring(1) + String0.CLOSE_PARENTHESIS);

    return Tuple.of(String.join(String0.BLANK, sqlList), rtnObjectList);
  }

  public void insertStatement(@NonNull List<String> insertList, @NonNull List<Object> objectList) {
    Object o;
    for (String fieldName : this.getFieldNameList()) {
      try {
        o = this.getClass().getMethod("get" + String0.upperFirst(fieldName)).invoke(this);
      } catch (Exception e) {
        o = null;
        ///ignore exception : config error, can't stop business, developer can be see and fixed by log
        log.warn(OM3.lp(o, fieldName, insertList, objectList), e);
      }
      if (!String0.isNullOrEmpty(String.valueOf(o))) {
        insertList.add(this.getDbColumnMap().get(fieldName));
        objectList.add(o);
      }
    }
  }

  public Tuple.Pair<String, List<Object>> selectCountSql() {
    Tuple.Pair<List<String>, List<Object>> pair = this.selectSql(List0.newArrayList(Keyword.parenthesis(Keyword.FN__COUNT, 1)), List0.newArrayList());
    return Tuple.of(String.join(String0.BLANK, Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<String, List<Object>> selectIdsSql() {
    Tuple.Pair<List<String>, List<Object>> pair = this.selectSql(List0.newArrayList(Keyword.parenthesis(Keyword.FN__GROUP_CONCAT, Identified.FIELD__ID)), List0.newArrayList());
    return Tuple.of(String.join(String0.BLANK, Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<String, List<Object>> selectSql() {
    List<Object> rtnObjectList = List0.newArrayList();

    List<String> selectList = List0.newArrayList();
    selectStatement(selectList, rtnObjectList);

    Tuple.Pair<List<String>, List<Object>> pair = this.selectSql(selectList, rtnObjectList);
    return Tuple.of(String.join(String0.BLANK, Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<List<String>, List<Object>> selectSql(@NonNull List<String> selectList, @NonNull List<Object> selectObjectList) {
    return selectSql(selectList, selectObjectList, false);
  }

  public Tuple.Pair<List<String>, List<Object>> selectSql(@NonNull List<String> selectList, @NonNull List<Object> selectObjectList, boolean withoutLimit) {
    List<Object> rtnObjectList = List0.newArrayList();
    rtnObjectList.addAll(selectObjectList);

    List<String> fromList = List0.newArrayList();
    fromStatement(fromList, rtnObjectList);

    List<String> whereList = List0.newArrayList();
    whereStatement(whereList, rtnObjectList);

    List<String> groupByList = List0.newArrayList();
    groupByStatement(groupByList, rtnObjectList);

    List<String> havingList = List0.newArrayList();
    havingStatement(havingList, rtnObjectList);

    List<String> orderByList = List0.newArrayList();
    orderByStatement(orderByList, rtnObjectList);

    return selectSql(rtnObjectList, selectList, fromList, whereList, groupByList, havingList, orderByList, withoutLimit);
  }

  public Tuple.Pair<List<String>, List<Object>> selectSql(@NonNull List<Object> objectList, @NonNull List<String> selectList, @NonNull List<String> fromList
    , List<String> whereList, List<String> groupByList, List<String> havingList, List<String> orderByList, boolean withoutLimit) {
    List<Object> rtnObjectList = List0.newArrayList();

    List<String> sqlList = List0.newArrayList();
    sqlList.add(Keyword.SELECT);
    sqlList.add(String.join(String0.COMMA, selectList));
    sqlList.add(Keyword.FROM);
    sqlList.add(String.join(String0.COMMA, fromList));
    if (whereList != null && whereList.size() > 0) {
      sqlList.add(Keyword.WHERE);
      sqlList.add(String.join(String0.wrapBlack(Keyword.AND), whereList));
    }
    if (groupByList != null && groupByList.size() > 0) {
      sqlList.add(Keyword.GROUP_BY);
      sqlList.add(String.join(String0.COMMA, groupByList));
    }
    if (havingList != null && havingList.size() > 0) {
      sqlList.add(Keyword.HAVING);
      sqlList.add(String.join(String0.wrapBlack(Keyword.AND), havingList));
    }
    if (orderByList != null && orderByList.size() > 0) {
      sqlList.add(Keyword.ORDER_BY);
      sqlList.add(String.join(String0.COMMA, orderByList));
    }
    rtnObjectList.addAll(objectList);

    if (!withoutLimit) {
      List<String> limitList = List0.newArrayList();
      limitStatement(limitList, rtnObjectList);
      if (limitList.size() > 0) {
        sqlList.add(String.join(String0.BLANK, limitList));
      }
    }
    return Tuple.of(sqlList, rtnObjectList);
  }

  public void selectStatement(@NonNull List<String> selectList, @NonNull List<Object> objectList) {
    //count(1)
    selectList.addAll(this.lstSelectFiled().stream().map((String fieldName) -> String0.null2EmptyTo(this.getDbColumnMap().get(fieldName), fieldName)).collect(Collectors.toList()));
  }

  public Tuple.Pair<String, List<Object>> updateSql() {
    List<Object> rtnObjectList = List0.newArrayList();

    List<String> updateList = List0.newArrayList();
    updateStatement(updateList, rtnObjectList);

    List<String> whereList = List0.newArrayList();
    whereEntityStatement(whereList, rtnObjectList, this.getIdFieldNameList());
    whereEntityStatement(whereList, rtnObjectList, this.getVerFieldNameList());
    whereConditionsStatement(whereList, rtnObjectList, this.getFieldNameList());

    List<String> sqlList = List0.newArrayList();
    sqlList.add(Keyword.UPDATE);
    sqlList.add(this.fullTableName());
    sqlList.add(Keyword.SET);
    sqlList.add(String.join(String0.COMMA, updateList));
    if (whereList.size() > 0) {
      sqlList.add(Keyword.WHERE);
      sqlList.add(String.join(String0.wrapBlack(Keyword.AND), whereList));
    }
    return Tuple.of(String.join(String0.BLANK, sqlList), rtnObjectList);
  }

  public void updateStatement(@NonNull List<String> updateList, @NonNull List<Object> objectList) {
    Object o;
    for (String fieldName : this.getFieldNameList().stream().filter(fieldName -> !this.getIdFieldNameList().contains(fieldName) && this.getColumnMap().get(fieldName).updatable()).collect(Collectors.toList())) {
      try {
        o = this.getClass().getMethod("get" + String0.upperFirst(fieldName)).invoke(this);
      } catch (Exception e) {
        o = null;
        ///ignore exception : config error, can't stop business, developer can be see and fixed by log
        log.warn(OM3.lp(o, fieldName, fieldName, objectList), e);
      }
      if (o != null) {//can update to null
        updateList.add(this.getDbColumnMap().get(fieldName) + String0.EQUAL + String0.QUESTION);
        if (!this.getVerFieldNameList().contains(fieldName)) {
          objectList.add(o);
        } else {
          objectList.add((Integer) o + 1);
        }
      }
    }
  }

  //other statements
  public void fromStatement(@NonNull List<String> fromList, @NonNull List<Object> objectList) {
    fromList.add(this.fullTableName());
  }

  public void groupByStatement(@NonNull List<String> groupByList, @NonNull List<Object> objectList) {
    if (this.getGroupByList() != null) {
      groupByList.addAll(this.getGroupByList());
    }
  }

  public void havingStatement(@NonNull List<String> havingList, @NonNull List<Object> objectList) {
    if (this.getHavingConditions() != null) {
      this.havingStatement(havingList, objectList, this.getFieldNameList());
    }
  }

  public void havingStatement(@NonNull List<String> havingList, @NonNull List<Object> objectList, @NonNull List<String> fieldNameList) {
    for (String fieldName : fieldNameList) {
      if (this.getColumnMap().get(fieldName) != null) {
        for (Condition cond : this.findHavingConditions(fieldName)) {
          this.fillOc(havingList, objectList, cond, String0.null2EmptyTo(cond.getLe(), this.getDbColumnMap().get(fieldName)));
        }
      }
    }
  }

  public void orderByStatement(@NonNull List<String> orderByList, @NonNull List<Object> objectList) {
    if (this.getOrderByList() != null) {
      orderByList.addAll(this.getOrderByList());
    }
  }

  public void whereEntityStatement(@NonNull List<String> whereList, @NonNull List<Object> objectList, @NonNull List<String> fieldNameList) {
    Object o;
    for (String fieldName : fieldNameList) {
      try {
        o = this.getClass().getMethod("get" + String0.upperFirst(fieldName)).invoke(this);
      } catch (Exception e) {
        o = null;
        ///ignore exception : config error, can't stop business, developer can be see and fixed by log
        log.warn(OM3.lp(o, fieldName, fieldName, objectList), e);
      }
      if (this.getColumnMap().get(fieldName) != null) {
        if (o != null) {//whereConditions support empty
          whereList.add(this.getDbColumnMap().get(fieldName) + String0.EQUAL + String0.QUESTION);
          objectList.add(o);
        }
      }
    }
  }

  public void whereConditionsStatement(@NonNull List<String> whereList, @NonNull List<Object> objectList, @NonNull List<String> fieldNameList) {
    for (String fieldName : fieldNameList) {
      for (Condition cond : this.findWhereConditions(fieldName)) {
        this.fillOc(whereList, objectList, cond, String0.null2EmptyTo(cond.getLe(), this.getDbColumnMap().get(fieldName)));
      }
    }
  }

  public void whereStatement(@NonNull List<String> whereList, @NonNull List<Object> objectList) {
    this.whereEntityStatement(whereList, objectList, this.getFieldNameList());
    this.whereConditionsStatement(whereList, objectList, this.getFieldNameList());
  }
}
