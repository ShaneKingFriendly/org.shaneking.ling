package sktest.ling.jackson.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.ling.jackson.annotation.CtxIgnored;
import org.shaneking.ling.jackson.filter.CtxIgnoredFilter;

@Accessors(chain = true)
@CtxIgnored("scenario3")
@JsonFilter(CtxIgnoredFilter.FILTER_NAME)
@ToString
public class CtxIgnoredFilterPrepare extends CtxIgnoredFilter {
  @CtxIgnored("scenario1")
  @Getter
  @Setter
  private String s1;
  @Getter
  @Setter
  private Integer i1;
  @CtxIgnored({"scenario1", "scenario2"})
  @Getter
  @Setter
  private String s2;
  @CtxIgnored
  @Getter
  @Setter
  private HelloCtxIgnoredFilter2 o1;

  @Override
  public boolean include(BeanPropertyWriter writer) {
    return super.include(writer);
  }

  @Accessors(chain = true)
  @CtxIgnored("scenario3")
  @JsonFilter(CtxIgnoredFilter.FILTER_NAME)
  @ToString
  public static class HelloCtxIgnoredFilter2 {
    @CtxIgnored("scenario1")
    @Getter
    @Setter
    private String s1;
    @CtxIgnored
    @Getter
    @Setter
    private Integer i1;
    @CtxIgnored({"scenario1", "scenario2"})
    @Getter
    @Setter
    private String s2;

  }
}
