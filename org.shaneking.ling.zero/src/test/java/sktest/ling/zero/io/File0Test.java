package sktest.ling.zero.io;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.io.File0;
import org.shaneking.ling.zero.lang.String0;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class File0Test {

  @Test
  void join() {
    ///Users/ShaneKing/me/space/web/com/github/ShaneKingOrg/org.shaneking.ling/org.shaneking.ling.zero
    String userDir = System.getProperty("user.dir");
    assertAll(
      () -> assertEquals(userDir + "/target/test-classes/sktest/ling/zero/io/", File0Test.class.getResource(String0.EMPTY).getFile()),
      () -> assertEquals(userDir + "/target/test-classes/", File0Test.class.getResource(String0.SLASH).getFile()),
      () -> assertEquals(userDir + "/target/test-classes/", File0Test.class.getClassLoader().getResource(String0.EMPTY).getFile()),
      () -> assertNull(File0Test.class.getClassLoader().getResource(String0.SLASH))
    );
    assertAll(
      () -> assertEquals("sktest.ling.zero.io.File0Test", File0Test.class.getCanonicalName()),
      () -> assertEquals("sktest.ling.zero.io.File0Test", File0Test.class.getName()),
      () -> assertEquals("package sktest.ling.zero.io", File0Test.class.getPackage().toString()),
      () -> assertEquals("File0Test", File0Test.class.getSimpleName()),
      () -> assertEquals("sktest.ling.zero.io.File0Test", File0Test.class.getTypeName()),
      () -> assertEquals("class sktest.ling.zero.io.File0Test", File0Test.class.toGenericString()),
      () -> assertEquals("class sktest.ling.zero.io.File0Test", File0Test.class.toString())
    );
    assertAll(
      () -> assertEquals(String.join(File.separator, userDir, "target", "classes", "sktest"), File0.join(new File("target"), "classes", "sktest").getAbsolutePath()),
      () -> assertEquals(String.join(File.separator, userDir, "target", "classes", "sktest"), File0.join(File.separator, "target", "classes", "sktest").getAbsolutePath()),
      () -> assertEquals(String.join(File.separator, userDir, "target", "classes", "sktest"), File0.join(File.separator, new File("target"), "classes", "sktest").getAbsolutePath())
    );
  }

  @Test
  void suffix() {
    assertEquals(String0.DOT + File0.TYPE_CLASS, File0.suffix(File0.TYPE_CLASS));
  }
}
