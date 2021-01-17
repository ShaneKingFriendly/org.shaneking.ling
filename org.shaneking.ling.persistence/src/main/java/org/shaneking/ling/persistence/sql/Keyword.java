package org.shaneking.ling.persistence.sql;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.String0;

public class Keyword {
  public static final String COMMENT4ANNOTATION = "COMMENT";

  public static final String ALTER_TABLE = "alter table";
  public static final String AND = "and";
  public static final String ADD_UNIQUE_INDEX = "add unique index";
  public static final String BETWEEN = "between";
  public static final String COMMENT = "comment";
  public static final String COUNT_1_ = "count(1)";
  public static final String CREATE_TABLE = "create table";
  public static final String DELETE_FROM = "delete from";
  public static final String FROM = "from";
  public static final String GROUP_BY = "group by";
  public static final String GROUP__CONCAT_ID_ = "group_concat(id)";
  public static final String HAVING = "having";
  public static final String IN = "in";
  public static final String INSERT_INFO = "insert into";
  public static final String LIKE = "like";
  public static final String LIMIT = "limit";
  public static final String NOT_NULL = "not null";
  public static final String NOT_NULL_WITH_BLACK_PREFIX = String0.BLANK + NOT_NULL;
  public static final String OFFSET = "offset";
  public static final String ORDER_BY = "order by";
  public static final String PRIMARY_KEY = "primary key";
  public static final String SELECT = "select";
  public static final String SET = "set";
  public static final String UPDATE = "update";
  public static final String VALUES = "values";
  public static final String WHERE = "where";

  public static final String TYPE_INT = "int";
  public static final String TYPE_LONGTEXT = "longtext";
  public static final String TYPE_VARCHAR = "varchar";

  public static String wrapBlack(@NonNull String keyword) {
    return String0.BLANK + keyword + String0.BLANK;
  }
}
