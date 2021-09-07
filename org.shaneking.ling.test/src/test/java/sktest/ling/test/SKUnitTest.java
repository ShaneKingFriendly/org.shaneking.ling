package sktest.ling.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@Disabled("explanation")
@Slf4j
public class SKUnitTest extends SKUnit {
  @BeforeEach
  public void beforeEach1() {
    log.info("testBefore1");
  }

  @BeforeEach
  public void beforeEach2() {
    log.info("testBefore2");
  }

  @Test
  void testIContent() {
    assertEquals(tstIContent(), tstIContent());
  }

  @Test
  public void testIFiles() {
    assertEquals(new File("src/test/java/sktest/ling/test/tstFiles/SKUnitTest_testIFiles_null_i.txt"), tstIFiles());
  }

  @Test
  void testOContent() {
    assertEquals(tstOContent(), tstOContent());
  }

  @Test
  public void testOFiles() {
    tstPrint(tstOFiles());
  }

  @Test
  void testToString() {
    assertEquals("testToString", this.setTstFiles(String0.ALPHABET).setTstI(String0.ALPHABET).setTstO(String0.ALPHABET).setTstSeq(String0.ALPHABET).getTstInfo().getTestMethod().get().getName());
  }
}
