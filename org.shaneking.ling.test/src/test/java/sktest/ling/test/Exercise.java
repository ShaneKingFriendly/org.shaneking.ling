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
  private int abs(int max) {
    return abs(max, 0);
  }

  private int abs(int max, int min) {
    max = Math.min(max, 10);
    min = Math.min(min, 6);
    min = Math.max(min, 0);
    int m = max - min;
    if (m <= 1)
      return min;
    return min + SR0.absInt(m);
  }

  @Test
  void LaLbEqRaFixed() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 9; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 9; j++) {
        jList.add(String.format("%d+%d=%-2d", i, j, i + j));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLbEqRpFixed() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 9; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 9; j++) {
//        jList.add(MF0.fmt("{0}+{1}=(  )", i, j));
        jList.add(String.format("%d+%d=(  )", i, j));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }


  @Test
  void LaLbEqRp() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 8; j++) {
        int la = abs(10);
        int lb = abs(10);
        jList.add(String.format("%d+%d=(  )", la, lb));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLyEqRp() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 8; j++) {
        int la = abs(10, 2);
        int ly = abs(la);
        jList.add(String.format("%d-%d=(  )", la, ly));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }


  @Test
  void LaLbEqRpRb() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 8; j++) {
        int la = abs(10);
        int lb = abs(10);
        jList.add(String.format("%d+%d=(  )+%d", la, lb, abs(la + lb)));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLbEqRpRy() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 8; j++) {
        int la = abs(10);
        int lb = abs(10);
        jList.add(String.format("%d+%d=(  )-%d", la, lb, abs(la + lb)));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLyEqRpRb() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 8; j++) {
        int la = abs(10, 2);
        int ly = abs(la);
        jList.add(String.format("%d-%d=(  )+%d", la, ly, abs(la - ly)));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLyEqRpRy() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 8; j++) {
        int la = abs(10, 2);
        int ly = abs(la, 0);
        jList.add(String.format("%d-%d=(  )-%d", la, ly, abs(10)));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }


  @Test
  void LaLbLcEqRpRb() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 7; j++) {
        int la = abs(10);
        int lb = abs(10);
        int lc = abs(10);
        jList.add(String.format("%d+%d+%d=(  )+%d", la, lb, lc, abs(la + lb + lc)));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLbLcEqRpRy() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 7; j++) {
        int la = abs(10);
        int lb = abs(10);
        int lc = abs(10);
        jList.add(String.format("%d+%d+%d=(  )-%d", la, lb, lc, abs(la + lb + lc)));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLyLcEqRpRb() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 7; j++) {
        int la = abs(10);
        int lc = abs(10);
        int ly = abs(la + lc);
        jList.add(String.format("%d-%d+%d=(  )+%d", la, ly, lc, abs(la - ly + lc)));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLyLcEqRpRy() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 7; j++) {
        int la = abs(10);
        int lc = abs(10);
        int ly = abs(la + lc);
        jList.add(String.format("%d-%d+%d=(  )-%d", la, ly, lc, abs(10)));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }


  @Test
  void LaLbLcEqRaRpRc() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 6; j++) {
        int la = abs(10);
        int lb = abs(10);
        int lc = abs(10);
        int rc = abs(la + lb + lc);
        jList.add(String.format("%d+%d+%d=%d+(  )+%d", la, lb, lc, abs(la + lb + lc - rc), rc));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLyLcEqRaRpRc() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 6; j++) {
        int la = abs(10);
        int ly = abs(10);
        int lc = abs(10);
        int rc = abs(la - ly + lc);
        jList.add(String.format("%d-%d+%d=%d+(  )+%d", la, ly, lc, abs(la - ly + lc - rc), rc));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLbLcEqRaRmRc() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 6; j++) {
        int la = abs(10);
        int lb = abs(10);
        int lc = abs(10);
        int ra = abs(la + lb + lc, lc);
        jList.add(String.format("%d+%d+%d=%d-(  )+%d", la, lb, lc, ra, abs(la + lb + lc - ra, la)));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLyLcEqRaRmRc() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 6; j++) {
        int la = abs(10);
        int ly = abs(10);
        int lc = abs(10);
        int ra = abs(la + ly + lc, lc);
        jList.add(String.format("%d-%d+%d=%d-(  )+%d", la, ly, lc, ra, abs(la - ly + lc - ra, la)));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }


  @Test
  void LaLbLcEqRaRpRz() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 6; j++) {
        int la = abs(10);
        int lb = abs(10);
        int lc = abs(10);
        int rz = abs(la + lb + lc);
        jList.add(String.format("%d+%d+%d=%d+(  )-%d", la, lb, lc, abs(la + lb + lc - rz), rz));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLyLcEqRaRpRz() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 6; j++) {
        int la = abs(10);
        int lc = abs(10);
//        int ly = abs(la + lc);// allow -
        int ly = abs(10);
        int rz = abs(la - ly + lc);
        jList.add(String.format("%d-%d+%d=%d+(  )-%d", la, ly, lc, abs(la - ly + lc - rz), rz));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLbLcEqRaRmRz() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 6; j++) {
        int la = abs(abs(10));
        int lb = abs(abs(10));
        int lc = abs(abs(10));
        int ra = abs(10, la + lb + lc);
        jList.add(String.format("%d+%d+%d=%d-(  )-%d", la, lb, lc, ra, abs(ra - (la + lb + lc))));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }

  @Test
  void LaLyLcEqRaRmRz() throws IOException {
    List<String> iList = List0.newArrayList();
    for (int i = 0; i <= 25; i++) {
      List<String> jList = List0.newArrayList();
      for (int j = 0; j <= 6; j++) {
        int la = abs(abs(10));
        int lc = abs(abs(10));
        int ly = abs(la + lc);
        int ra = abs(10, la + ly + lc);
        jList.add(String.format("%d-%d+%d=%d-(  )-%d", la, ly, lc, ra, abs(ra - (la - ly + lc))));
      }
      iList.add(String.join(String0.BLANK + String0.BLANK + String0.BLANK, jList));
    }
    Files.write(tstOFiles(File0.TYPE_LOG).toPath(), String.join(String0.BR_LINUX, iList).getBytes());
    assertNotNull(String0.EMPTY);
  }
}
