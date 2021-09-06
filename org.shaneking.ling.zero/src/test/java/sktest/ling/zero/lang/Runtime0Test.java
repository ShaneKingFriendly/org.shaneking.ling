package sktest.ling.zero.lang;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.Runtime0;
import org.shaneking.ling.zero.util.FixedList;
import org.shaneking.ling.zero.util.List0;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class Runtime0Test {

  @Test
  void exec() {
    List<String> pwd1List = List0.newArrayList(
      "===B:errorStream=false,value4pause=false,pauseFlag=null===",
      System.getProperty("user.dir"),
      "===E:errorStream=false,value4pause=false,pauseFlag=null===",
      "===B:errorStream=true,value4pause=false,pauseFlag=null===",
      "===E:errorStream=true,value4pause=false,pauseFlag=null===",
      "process.waitFor()=0"
    );
    List<String> pwd2List = List0.newArrayList(
      "===B:errorStream=false,value4pause=true,pauseFlag=>read -n 1 -p===",
      System.getProperty("user.dir"),
      "===E:errorStream=false,value4pause=true,pauseFlag=>read -n 1 -p===",
      "===B:errorStream=true,value4pause=true,pauseFlag=>read -n 1 -p===",
      "===E:errorStream=true,value4pause=true,pauseFlag=>read -n 1 -p===",
      "process.waitFor()=0"
    );
    assertAll(
      () -> assertLinesMatch(pwd1List, Runtime0.exec("pwd")),

      () -> assertLinesMatch(pwd2List, Runtime0.exec("pwd", true, Runtime0.PAUSE_FLAG_SHELL)),

      () -> assertLinesMatch(pwd2List, Runtime0.exec("pwd", true, Runtime0.PAUSE_FLAG_SHELL, FixedList.DEFAULT_SIZE)),

      () -> assertLinesMatch(List0.newArrayList("java.util.concurrent.TimeoutException"), Runtime0.exec("sleep 5", 3)),

      () -> assertLinesMatch(pwd1List, Runtime0.exec("pwd", Runtime0.DEFAULT_TIMEOUT_SECONDS, FixedList.DEFAULT_SIZE))
    );
  }
}
