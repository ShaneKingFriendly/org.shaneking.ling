package sktest.ling.rr;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.rr.Req;
import org.shaneking.ling.rr.ReqMsg;
import org.shaneking.ling.rr.ReqMsgBdy;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.util.UUID0;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReqTest extends SKUnit {

  @Test
  void build() {
    String cul33 = UUID0.cUl33();
    assertAll(
      () -> assertEquals("Req(cno=null, tkn=null, mvc=null, enc=null, msg=null, ctx=null)", Req.build().toString()),

      () -> assertEquals("{}", OM3.writeValueAsString(Req.build())),
      () -> assertEquals("{\"msg\":{\"rno\":\"" + cul33 + "\",\"tno\":\"" + cul33 + "\"}}", OM3.writeValueAsString(Req.build().setMsg(ReqMsg.build().setRno(cul33).setTno(cul33)))),
      () -> assertEquals("{\"msg\":{\"rno\":\"" + cul33 + "\",\"tno\":\"" + cul33 + "\",\"bdy\":{}}}", OM3.writeValueAsString(Req.build().setMsg(ReqMsg.build().setRno(cul33).setTno(cul33)).srtMsgBdy(ReqMsgBdy.build()))),
      () -> assertEquals("{\"enc\":\"enc\"}", OM3.writeValueAsString(Req.build().setEnc("enc"))),

      () -> assertEquals("{\"ctx\":{\"language\":\"zh_CN\",\"rtuMap\":{},\"trtList\":[],\"tutList\":[]}}", OM3.writeValueAsString(Req.build().setCtx(new Req().gnnCtx().setLanguage("zh_CN"))))
    );
  }
}
