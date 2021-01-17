package org.shaneking.ling.persistence.sql.entity.mysql;

import lombok.NonNull;
import org.shaneking.ling.persistence.Sqler;
import org.shaneking.ling.persistence.sql.Keyword;
import org.shaneking.ling.persistence.sql.Pagination;
import org.shaneking.ling.zero.lang.Integer0;
import org.shaneking.ling.zero.lang.String0;

import java.text.MessageFormat;
import java.util.List;

public interface MySqler extends Sqler {
  default String createTableIfNotExistSql() {
    String idxSql = createTableIndexSql();
    idxSql = String0.isNull2Empty(idxSql) ? String0.EMPTY : (idxSql + String0.BR_LINUX + String0.BR_LINUX);
    return "drop procedure if exists p_" + this.getDbTableName() + "_create;" + String0.BR_LINUX +
      "delimiter $$" + String0.BR_LINUX +
      "create procedure p_" + this.getDbTableName() + "_create() begin" + String0.BR_LINUX +
      "if not exists (select * from information_schema.tables where table_schema = '" + this.getJavaTable().schema() + "' and table_name = '" + this.getDbTableName() + "')" + String0.BR_LINUX +
      "then" + String0.BR_LINUX + String0.BR_LINUX +
      createTableSql() + String0.BR_LINUX + String0.BR_LINUX +
      idxSql +
      "end if;" + String0.BR_LINUX +
      "end;" + String0.BR_LINUX +
      "$$" + String0.BR_LINUX +
      "delimiter ;" + String0.BR_LINUX +
      "call p_" + this.getDbTableName() + "_create();" + String0.BR_LINUX +
      "drop procedure if exists p_" + this.getDbTableName() + "_create;" + String0.BR_LINUX;
  }

  default void limitStatement(@NonNull List<String> limitList, @NonNull List<Object> objectList) {
    Pagination pagination = this.getPagination() == null ? new Pagination() : this.getPagination();
    limitList.add(MessageFormat.format("{0} {1}", Keyword.LIMIT, String.valueOf(Integer0.gt2d(Integer0.null2Default(pagination.getSize(), Pagination.DEFAULT_SIZE), Pagination.MAX_SIZE))));//add String.valueOf to fix 1000+ to 1,000+
    limitList.add(MessageFormat.format("{0} {1}", Keyword.OFFSET, String.valueOf(Integer0.lt2d(Integer0.null2Zero(pagination.getPage()), 0))));
  }
}
