package org.shaneking.ling.druid.stat;

import com.alibaba.druid.stat.TableStat;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class TableStatColumn {
  @Getter
  private TableStat.Column raw;
  @Getter
  private String table;
  @Getter
  private String name;
  @Getter
  private String fullName;

  public TableStatColumn() {
  }

  public TableStatColumn(TableStat.Column raw) {
    this.raw = raw;
    this.table = raw.getTable() == null ? null : raw.getTable().toLowerCase();
    this.name = raw.getName().toLowerCase();
    this.fullName = raw.getFullName().toLowerCase();
  }
}
