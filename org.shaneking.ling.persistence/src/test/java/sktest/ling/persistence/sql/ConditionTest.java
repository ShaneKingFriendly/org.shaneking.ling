package sktest.ling.persistence.sql;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.sql.Condition;
import org.shaneking.ling.zero.util.List0;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConditionTest {

  @Test
  void appendId() {
    assertEquals("Condition(le=null, op=in, cl=[id2, id3], cs=id1, bw=null, ew=null)", new Condition().setCs("id1").setCl(List0.newArrayList("id2")).appendId("id3").toString());
    assertEquals("Condition(le=null, op=in, cl=[id3], cs=id1, bw=null, ew=null)", new Condition().setCs("id1").appendIds(List0.newArrayList("id3")).toString());
  }

  @Test
  void resetId() {
    assertEquals("Condition(le=null, op==, cl=[id2], cs=id3, bw=null, ew=null)", new Condition().setCs("id1").setCl(List0.newArrayList("id2")).resetId("id3").toString());
  }

  @Test
  void resetIds() {
    assertEquals("Condition(le=null, op=in, cl=[id3], cs=id1, bw=null, ew=null)", new Condition().setCs("id1").setCl(List0.newArrayList("id2")).resetIds(List0.newArrayList("id3")).toString());
  }

  @Test
  void retainIds() {
    assertEquals("Condition(le=null, op=in, cl=[id3], cs=id1, bw=null, ew=null)", new Condition().setCs("id1").setCl(List0.newArrayList("id2", "id3")).retainIds(List0.newArrayList("id3", "id4")).toString());
    assertEquals("Condition(le=null, op=in, cl=[id3], cs=id1, bw=null, ew=null)", new Condition().setCs("id1").retainIds(List0.newArrayList("id3")).toString());
  }

  @Test
  void testToString() {
    assertEquals("Condition(le=LeftExpr, op=Operation, cl=[contentList], cs=contentString, bw=beginWith, ew=endWith)"
      , new Condition().setBw("beginWith").setCl(List0.newArrayList("contentList")).setCs("contentString").setEw("endWith").setLe("LeftExpr").setOp("Operation").toString());
  }
}
