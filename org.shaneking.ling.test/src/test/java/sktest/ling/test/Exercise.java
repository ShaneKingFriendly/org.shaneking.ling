package sktest.ling.test;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.io.File0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.security.SR0;
import org.shaneking.ling.zero.util.List0;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Exercise extends SKUnit {
  @Test
  void LaLbEqRx() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 10; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 10; j++) {
//        jList.add(MF0.fmt("{0}+{1}=(  )", i, j));
//        jList.add(String.format("%-2d+%-2d=(  )", i, j));
        if (j == 10) {
          jList.add(String.format("%-2d+%-2d=%-2d", i, j, i + j));
        } else {
          jList.add(String.format("%-2d+%d=%-2d", i, j, i + j));
        }
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLbEqRk() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 10; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 10; j++) {
//        jList.add(MF0.fmt("{0}+{1}=(  )", i, j));
//        jList.add(String.format("%-2d+%-2d=(  )", i, j));
        if (j == 10) {
          jList.add(String.format("%-2d+%-2d=(  )", i, j));
        } else {
          jList.add(String.format("%-2d+%d=(  )", i, j));
        }
      }
      iList.add(String.join(String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLbEqRkRx() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 8; j++) {
        int a = SR0.absInt(10);
        a = a == 0 ? SR0.absInt(10) : a;
        int b = SR0.absInt(10);
        b = b == 0 ? SR0.absInt(10) : b;
        jList.add(String.format("%d+%d=(  )+%d", a, b, SR0.absInt(Math.min(10, a + b))));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLbEqRkRm() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 8; j++) {
        int a = SR0.absInt(10);
        a = a == 0 ? SR0.absInt(10) : a;
        int b = SR0.absInt(10);
        b = b == 0 ? SR0.absInt(10) : b;
        jList.add(String.format("%d+%d=(  )-%d", a, b, SR0.absInt(Math.min(10, a + b))));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }


}
