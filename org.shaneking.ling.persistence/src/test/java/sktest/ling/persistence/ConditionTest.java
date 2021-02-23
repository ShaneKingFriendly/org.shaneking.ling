package sktest.ling.persistence;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.Condition;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.util.List0;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConditionTest extends SKUnit {

  @Test
  void appendVal() {
    assertEquals("Condition(le=null, op=in, cl=[id2, id3], cs=id1, bw=null, ew=null)", new Condition().setCs("id1").setCl(List0.newArrayList("id2")).appendVal("id3").toString());
    assertEquals("Condition(le=null, op=in, cl=[id3], cs=id1, bw=null, ew=null)", new Condition().setCs("id1").appendVal(List0.newArrayList("id3")).toString());
  }

  @Test
  void resetVal() {
    assertEquals("Condition(le=null, op==, cl=[id2], cs=id3, bw=null, ew=null)", new Condition().setCs("id1").setCl(List0.newArrayList("id2")).resetVal("id3").toString());
    assertEquals("Condition(le=null, op=in, cl=[id3], cs=id1, bw=null, ew=null)", new Condition().setCs("id1").setCl(List0.newArrayList("id2")).resetVal(List0.newArrayList("id3")).toString());
  }

  @Test
  void retainVal() {
    assertEquals("Condition(le=null, op=in, cl=[id3], cs=id1, bw=null, ew=null)", new Condition().setCs("id1").setCl(List0.newArrayList("id2", "id3")).retainVal(List0.newArrayList("id3", "id4")).toString());
    assertEquals("Condition(le=null, op=in, cl=[id3], cs=id1, bw=null, ew=null)", new Condition().setCs("id1").retainVal(List0.newArrayList("id3")).toString());
  }

  @Test
  void testToString() {
    assertEquals("Condition(le=LeftExpr, op=Operation, cl=[contentList], cs=contentString, bw=beginWith, ew=endWith)"
      , new Condition().setBw("beginWith").setCl(List0.newArrayList("contentList")).setCs("contentString").setEw("endWith").setLe("LeftExpr").setOp("Operation").toString());
  }
}
