package org.shaneking.ling.persistence;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.String0;

import java.util.List;

public class Keyword {
  public static final String ADD_INDEX = "add index";
  public static final String ADD_UNIQUE_INDEX = "add unique index";
  public static final String ALTER_TABLE = "alter table";
  public static final String AND = "and";
  public static final String BETWEEN = "between";
  public static final String COMMENT = "comment";
  public static final String CREATE_TABLE = "create table";
  public static final String CREATE_INDEX = "create index";
  public static final String CREATE_UNIQUE_INDEX = "create unique index";
  public static final String DELETE_FROM = "delete from";
  public static final String FN__CONCAT = "concat";
  public static final String FN__COUNT = "count";
  public static final String FN__GROUP_CONCAT = "group_concat";
  public static final String FROM = "from";
  public static final String GROUP_BY = "group by";
  public static final String HAVING = "having";
  public static final String IF_NOT_EXISTS = "if not exists";
  public static final String IF_EXISTS = "if exists";
  public static final String IN = "in";
  public static final String INSERT_INFO = "insert into";
  public static final String LIKE = "like";
  public static final String LIMIT = "limit";
  public static final String NOT_NULL = "not null";
  public static final String NOT_NULL_WITH_BLACK_PREFIX = String0.BLANK + NOT_NULL;
  public static final String OFFSET = "offset";
  public static final String OR = "or";
  public static final String ORDER_BY = "order by";
  public static final String PRIMARY_KEY = "primary key";
  public static final String SELECT = "select";
  public static final String SET = "set";
  public static final String TYPE_CHAR = "char";
  public static final String TYPE_INT = "int";
  public static final String TYPE_LONGTEXT = "longtext";
  public static final String TYPE_TEXT = "text";
  public static final String TYPE_VARCHAR = "varchar";
  public static final String UPDATE = "update";
  public static final String VALUES = "values";
  public static final String WHERE = "where";

  public static final String parenthesis(@NonNull String name, @NonNull Integer value) {
    return name + String0.OPEN_PARENTHESIS + value + String0.CLOSE_PARENTHESIS;
  }

  public static final String parenthesis(@NonNull String name, @NonNull List<String> value) {
    return parenthesis(name, String.join(String0.COMMA, value));
  }

  public static final String parenthesis(@NonNull String name, @NonNull String value) {
    return name + String0.OPEN_PARENTHESIS + value + String0.CLOSE_PARENTHESIS;
  }

  public static final String parenthesis(@NonNull String name, @NonNull String... values) {
    return parenthesis(name, String.join(String0.COMMA, values));
  }
}
