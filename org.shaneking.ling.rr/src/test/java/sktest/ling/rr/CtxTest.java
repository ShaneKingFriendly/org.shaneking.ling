package sktest.ling.rr;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.rr.Ctx;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CtxTest {

  @Test
  void testToString() {
    assertAll(
      () -> assertEquals("Ctx(auditLog=null, channel=null, jon=null, language=null, tenant=null, user=null, rtuMap={}, trtList=[], tutList=[])", new Ctx().toString()),
      () -> assertEquals("{\"rtuMap\":{},\"trtList\":[],\"tutList\":[]}", OM3.writeValueAsString(new Ctx())),
      () -> assertEquals("{\"jon\":{},\"language\":\"language\",\"rtuMap\":{},\"trtList\":[],\"tutList\":[]}", OM3.writeValueAsString(new Ctx().setJon(OM3.createObjectNode()).setLanguage("language")))
    );
  }
}
