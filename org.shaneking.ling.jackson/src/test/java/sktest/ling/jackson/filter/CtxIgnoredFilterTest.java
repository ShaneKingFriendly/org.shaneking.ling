package sktest.ling.jackson.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.annotation.CtxIgnored;
import org.shaneking.ling.jackson.ctx.JacksonCtx;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.jackson.filter.CtxIgnoredFilter;
import org.shaneking.ling.test.SKUnit;

import static org.junit.jupiter.api.Assertions.*;

class CtxIgnoredFilterTest extends SKUnit {

  @Test
  void filter() {
    SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
    simpleFilterProvider.addFilter(CtxIgnoredFilter.FILTER_NAME, new CtxIgnoredFilter());
    OM3.om().setFilterProvider(simpleFilterProvider);
    assertAll(
      () -> {
        CtxIgnoredFilterPrepare1 ctxIgnoredFilterPrepare1 = new CtxIgnoredFilterPrepare1().setI1(1).setS1("s11").setS2("s12");
        JacksonCtx.scenario.set(null);
        assertEquals("{\"s1\":\"s11\",\"i1\":1,\"s2\":\"s12\"}", OM3.writeValueAsString(ctxIgnoredFilterPrepare1));
        JacksonCtx.scenario.set("scenario2");
        assertEquals("{\"s1\":\"s11\",\"i1\":1}", OM3.writeValueAsString(ctxIgnoredFilterPrepare1));
      },
      () -> {
        CtxIgnoredFilterPrepare1 ctxIgnoredFilterPrepare1 = new CtxIgnoredFilterPrepare1().setI1(1).setS1("s11").setS2("s12").setO11(new CtxIgnoredFilterPrepare1.CtxIgnoredFilterPrepare11().setI1(2).setS1("s21").setS2("s22"));
        JacksonCtx.scenario.set(null);
        assertEquals("{\"s1\":\"s11\",\"i1\":1,\"s2\":\"s12\",\"o11\":{\"s1\":\"s21\",\"i1\":2,\"s2\":\"s22\"}}", OM3.writeValueAsString(ctxIgnoredFilterPrepare1));
        JacksonCtx.scenario.set("scenario2");
        assertEquals("{\"s1\":\"s11\",\"i1\":1,\"o11\":{\"s1\":\"s21\",\"i1\":2}}", OM3.writeValueAsString(ctxIgnoredFilterPrepare1));
      }
    );
  }

  @Test
  void include() {
    assertAll(
      () -> assertNotNull(new CtxIgnoredFilterPrepare1().include(new BeanPropertyWriterPrepare1()))
    );
  }

  @Accessors(chain = true)
  @CtxIgnored("scenario3")
  @JsonFilter(CtxIgnoredFilter.FILTER_NAME)
  @ToString
  public static class CtxIgnoredFilterPrepare1 extends CtxIgnoredFilter {
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
    private CtxIgnoredFilterPrepare11 o11;

    @Override
    public boolean include(BeanPropertyWriter writer) {
      return super.include(writer);
    }

    @Accessors(chain = true)
    @CtxIgnored("scenario3")
    @JsonFilter(CtxIgnoredFilter.FILTER_NAME)
    @ToString
    public static class CtxIgnoredFilterPrepare11 {
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

  public static class BeanPropertyWriterPrepare1 extends BeanPropertyWriter {
  }
}
