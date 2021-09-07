package org.shaneking.ling.test;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.shaneking.ling.zero.io.File0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.time.Stopwatch0;
import org.shaneking.ling.zero.util.Regex0;

import java.io.File;

@Slf4j
@Accessors(chain = true)
public class SKUnit {
  public static final File MAVEN_TEST_ROOT_FOLDER = new File("src/test/java");
  @Getter
  @Setter
  private String tstFiles = "tstFiles";
  @Getter
  @Setter
  private String tstI = String0.I.toLowerCase();
  @Getter
  @Setter
  private String tstO = String0.O.toLowerCase();
  @Getter
  @Setter
  private String tstSeq = null;

  @Getter
  @Setter
  private Stopwatch0 stopwatch;
  @Getter
  @Setter
  private TestInfo tstInfo;

  @AfterEach
  public void tstAfter() {
    log.info(getTstInfo().getTestMethod().get().getName() + String0.EQUAL + this.getStopwatch().stop());
  }

  @BeforeEach
  public void tstBefore(TestInfo testInfo) {
    setTstInfo(testInfo);
    setStopwatch(Stopwatch0.createStarted());
  }

  public File tstFiles(String io, String fileType) {
    File rtn = new File(tstFolder(), getTstInfo().getTestClass().get().getSimpleName() + String0.UNDERLINE + getTstInfo().getTestMethod().get().getName() + String0.UNDERLINE + this.getTstSeq() + String0.UNDERLINE + io + String0.DOT + fileType);
    rtn.getParentFile().mkdirs();
    return rtn;
  }

  public File tstFolder() {
    return new File(MAVEN_TEST_ROOT_FOLDER, getTstInfo().getTestClass().get().getName().replaceAll(getTstInfo().getTestClass().get().getSimpleName(), getTstFiles()).replaceAll(Regex0.DOT, String0.SLASH));
  }

  public String tstIContent() {
    return tstIContent(File0.TYPE_TXT);
  }

  public String tstIContent(String fileType) {
    return File0.content(tstIFiles(fileType).toPath());
  }

  public File tstIFiles() {
    return tstIFiles(File0.TYPE_TXT);
  }

  public File tstIFiles(String fileType) {
    return tstFiles(getTstI(), fileType);
  }

  public String tstOContent() {
    return tstOContent(File0.TYPE_TXT);
  }

  public String tstOContent(String fileType) {
    return File0.content(tstOFiles(fileType).toPath());
  }

  public File tstOFiles() {
    return tstOFiles(File0.TYPE_TXT);
  }

  public File tstOFiles(String fileType) {
    return tstFiles(getTstO(), fileType);
  }

  public void tstPrint(Object o) {
    log.info(getTstInfo().getTestMethod().get().getName() + String0.EQUAL + o);
  }
}
