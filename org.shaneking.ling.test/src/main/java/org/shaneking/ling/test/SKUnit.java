package org.shaneking.ling.test;

import lombok.Getter;
import lombok.Setter;
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
public class SKUnit {
  public static final File MAVEN_TEST_ROOT_FOLDER = new File("src/test/java");

  @Getter
  @Setter
  private TestInfo tstInfo;
  @Getter
  @Setter
  private Stopwatch0 stopwatch;
  @Getter
  @Setter
  private String tstSeq = null;
  @Getter
  @Setter
  private String tstFiles = "tstFiles";
  @Getter
  @Setter
  private String tstI = String0.I.toLowerCase();
  @Getter
  @Setter
  private String tstO = String0.O.toLowerCase();

  //CFG BEGIN:files
  public File tstFolder() {
    return new File(MAVEN_TEST_ROOT_FOLDER, tstInfo.getTestClass().get().getName().replaceAll(tstInfo.getTestClass().get().getSimpleName(), getTstFiles()).replaceAll(Regex0.DOT, String0.SLASH));
  }

  public File tstFiles(String io, String fileType) {
    return new File(tstFolder(), tstInfo.getTestClass().get().getSimpleName() + String0.UNDERLINE + tstInfo.getTestMethod().get().getName() + String0.UNDERLINE + this.getTstSeq() + String0.UNDERLINE + io + String0.DOT + fileType);
  }

  public File tstIFiles() {
    return tstFiles(getTstI(), File0.TYPE_TXT);
  }

  public File tstIFiles(String fileType) {
    return tstFiles(getTstI(), fileType);
  }

  public File tstOFiles() {
    return tstFiles(getTstO(), File0.TYPE_TXT);
  }

  public File tstOFiles(String fileType) {
    return tstFiles(getTstO(), fileType);
  }
  //CFG END:files

  //CFG BEGIN:watch
  @BeforeEach
  public void tstBefore(TestInfo testInfo) {
    setTstInfo(testInfo);
    setStopwatch(Stopwatch0.createStarted());
  }

  @AfterEach
  public void tstAfter() {
    log.info(tstInfo.getTestMethod().get().getName() + String0.EQUAL + this.getStopwatch().stop());
  }
  //CFG END:watch

  public void tstPrint(Object o) {
    log.info(tstInfo.getTestMethod().get().getName() + String0.EQUAL + o);
  }
}
