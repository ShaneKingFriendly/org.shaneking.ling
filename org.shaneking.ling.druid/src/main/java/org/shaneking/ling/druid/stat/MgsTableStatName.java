package org.shaneking.ling.druid.stat;

import com.alibaba.druid.stat.TableStat;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.zero.util.Regex0;

@Accessors(chain = true)
@ToString
public class MgsTableStatName {
  @Getter
  private TableStat.Name raw;
  @Getter
  private String catalog;
  @Getter
  private String schema;
  @Getter
  private String name;

  public MgsTableStatName() {
  }

  public MgsTableStatName(TableStat.Name raw) {
    this.raw = raw;
    String[] names = raw.toString().split(Regex0.DOT);
    if (names.length > 0) {
      this.name = names[names.length - 1].toLowerCase();
    }
    if (names.length > 1) {
      this.schema = names[names.length - 2].toLowerCase();
    }
    if (names.length > 2) {
      this.catalog = names[names.length - 3].toLowerCase();
    }
  }
}
