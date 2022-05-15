package sktest.ling.zero.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.SnowflakeId;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class SnowflakeIdTest {

  @Test
  void nextId() throws InterruptedException {
    SnowflakeId snowflakeId = new SnowflakeId(0, 0);
    log.info("start  :" + snowflakeId.START);
    log.info("today  :" + Date.UTC(2022-1900, Calendar.MAY, 8, 0, 0, 0));
    log.info("current:" + System.currentTimeMillis());
    log.info("max    :" + Long.MAX_VALUE);
    for (int i = 0; i < 10; i++) {
      long id = snowflakeId.nextId();
      Thread.sleep(1);
      log.info(String.valueOf(id));
      assertTrue(id > snowflakeId.START);
    }
  }
}
