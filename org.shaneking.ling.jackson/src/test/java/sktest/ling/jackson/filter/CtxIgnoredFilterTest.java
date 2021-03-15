package sktest.ling.jackson.filter;

import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.jackson.filter.CtxIgnoredFilter;
import org.shaneking.ling.test.SKUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CtxIgnoredFilterTest extends SKUnit {

  @Test
  void include() {
    SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
    simpleFilterProvider.addFilter(CtxIgnoredFilter.FILTER_NAME, new CtxIgnoredFilter());
    OM3.om().setFilterProvider(simpleFilterProvider);
    assertAll(
      () -> {
        HelloCtxIgnoredFilter helloCtxIgnoredFilter = new HelloCtxIgnoredFilter().setI1(1).setS1("s11").setS2("s12");
        CtxIgnoredFilter.SCENARIO_CTX.set(null);
        assertEquals("{\"s1\":\"s11\",\"i1\":1,\"s2\":\"s12\",\"o1\":null}", OM3.writeValueAsString(helloCtxIgnoredFilter));
        CtxIgnoredFilter.SCENARIO_CTX.set("scenario2");
        assertEquals("{\"s1\":\"s11\",\"i1\":1,\"o1\":null}", OM3.writeValueAsString(helloCtxIgnoredFilter));
      },
      () -> {
        HelloCtxIgnoredFilter helloCtxIgnoredFilter = new HelloCtxIgnoredFilter().setI1(1).setS1("s11").setS2("s12").setO1(new HelloCtxIgnoredFilter.HelloCtxIgnoredFilter2().setI1(2).setS1("s21").setS2("s22"));
        CtxIgnoredFilter.SCENARIO_CTX.set(null);
        assertEquals("{\"s1\":\"s11\",\"i1\":1,\"s2\":\"s12\",\"o1\":{\"s1\":\"s21\",\"i1\":2,\"s2\":\"s22\"}}", OM3.writeValueAsString(helloCtxIgnoredFilter));
        CtxIgnoredFilter.SCENARIO_CTX.set("scenario2");
        assertEquals("{\"s1\":\"s11\",\"i1\":1,\"o1\":{\"s1\":\"s21\",\"i1\":2}}", OM3.writeValueAsString(helloCtxIgnoredFilter));
      }
    );

  }
}
