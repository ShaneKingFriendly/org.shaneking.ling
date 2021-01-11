package org.shaneking.ling.entity.sql.mysql;

import lombok.NonNull;
import org.shaneking.ling.entity.AbstractEntity;
import org.shaneking.ling.entity.sql.Keyword;
import org.shaneking.ling.entity.sql.Pagination;
import org.shaneking.ling.zero.lang.Integer0;

import java.text.MessageFormat;
import java.util.List;

public abstract class AbstractMysqlEntity<J> extends AbstractEntity<J> {
  public void limitStatement(@NonNull List<String> limitList, @NonNull List<Object> objectList) {
    Pagination pageHelper = this.getPagination() == null ? new Pagination() : this.getPagination();
    limitList.add(MessageFormat.format("{0} {1}", Keyword.LIMIT, String.valueOf(Integer0.gt2d(Integer0.null2Default(pageHelper.getSize(), Pagination.DEFAULT_SIZE), Pagination.MAX_SIZE))));//add String.valueOf to fix 1000+ to 1,000+
    limitList.add(MessageFormat.format("{0} {1}", Keyword.OFFSET, String.valueOf(Integer0.lt2d(Integer0.null2Zero(pageHelper.getPage()), 0))));
  }
}
