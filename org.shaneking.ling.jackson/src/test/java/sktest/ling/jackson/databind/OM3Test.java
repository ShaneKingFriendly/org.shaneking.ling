package sktest.ling.jackson.databind;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.jackson.databind.OM3;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OM3Test {

  @Test
  void writeValueAsString() {
    LinkedBlockingQueue<Integer> arrayBlockingQueue = new LinkedBlockingQueue<Integer>(3);
    for (int i = 0; i < 8; i++) {
      arrayBlockingQueue.add(i);
    }
//    assertEquals("", arrayBlockingQueue.toString());
    assertEquals("", OM3.writeValueAsString(arrayBlockingQueue));
  }
}
