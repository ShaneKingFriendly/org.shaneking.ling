package org.shaneking.ling.zero.util.concurrent;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.zero.lang.AC0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.FixedList;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

@Slf4j
public class Runtime0Callable implements Callable<List<String>> {
  @Getter
  private final boolean errorStream;
  @Getter
  private final InputStream inputStream;
  private final int maxRecordSize;
  @Getter
  private final String pauseFlag;
  @Getter
  private final boolean value4pause;///if meet pause, return true or false?

  public Runtime0Callable(InputStream inputStream, boolean errorStream, boolean value4pause, String pauseFlag, int maxRecordSize) {
    super();
    this.inputStream = inputStream;
    this.errorStream = errorStream;
    this.value4pause = value4pause;
    this.pauseFlag = pauseFlag;
    this.maxRecordSize = maxRecordSize;
  }

  @Override
  public List<String> call() throws Exception {
    List<String> rtnList = new FixedList<String>(maxRecordSize);
    rtnList.add(MessageFormat.format("===B:errorStream={0},value4pause={1},pauseFlag={2}===", this.isErrorStream(), this.isValue4pause(), this.getPauseFlag()));

    LineNumberReader lineNumberReader = null;
    InputStreamReader inputStreamReader = null;
    String line = null;

    try {
      inputStreamReader = new InputStreamReader(this.getInputStream());
      lineNumberReader = new LineNumberReader(inputStreamReader);

      if (String0.isNull2Empty(this.getPauseFlag())) {
        while ((line = lineNumberReader.readLine()) != null) {
          rtnList.add(line);
          if (this.isErrorStream()) {
            log.warn(line);
          } else {
            log.info(line);
          }
        }
      } else {
        while ((line = lineNumberReader.readLine()) != null) {
          rtnList.add(line);
          if (this.isErrorStream()) {
            log.warn(line);
          } else {
            log.info(line);
          }
          if (line.toLowerCase(Locale.ENGLISH).contains(this.getPauseFlag())) {
            rtnList.add("===P:line contains pauseFlag===");
            break;
          }
        }
      }
    } catch (Exception e) {
      ///ignore exception : most exec is output
      log.error(e.getMessage(), e);
      rtnList.add(e.toString());
    } finally {
      AC0.close(lineNumberReader);
      AC0.close(inputStreamReader);
    }
    rtnList.add(MessageFormat.format("===E:errorStream={0},value4pause={1},pauseFlag={2}===", this.isErrorStream(), this.isValue4pause(), this.getPauseFlag()));
    return rtnList;
  }
}
