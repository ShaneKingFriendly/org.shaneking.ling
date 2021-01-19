package sktest.ling.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.io.File0;

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
  public void testIFiles() {
    assertEquals(new File("src/test/java/sktest/ling/test/tstFiles/SKUnitTest_testIFiles_null_i.json"), tstIFiles(File0.TYPE_JSON));
  }

  @Test
  public void testOFiles() {
    tstPrint(tstOFiles(File0.TYPE_JSON));
  }
}
