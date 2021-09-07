package sktest.ling.zero.lang;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.io.File0;
import org.shaneking.ling.zero.lang.AC0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class AC0Test {

  @Test
  void close() {
    File thisFile = File0.join(new File(AC0Test.class.getResource(String0.EMPTY).getFile()), AC0Test.class.getSimpleName() + File0.suffix(File0.TYPE_CLASS));
    assertAll(
      () -> assertTrue(AC0.close(null)),
      () -> assertTrue(AC0.close(Files.newBufferedReader(thisFile.toPath()))),
      () -> assertFalse(AC0.close(new AC0Prepare(2))),

      () -> assertTrue(AC0.close(null, false)),
      () -> assertTrue(AC0.close(null, true)),
      () -> assertTrue(AC0.close(Files.newBufferedReader(thisFile.toPath()), false)),
      () -> assertTrue(AC0.close(Files.newBufferedReader(thisFile.toPath()), true)),
      () -> assertThrows(ZeroException.class, () -> AC0.close(new AC0Prepare(2), false)),

      () -> assertTrue(AC0.close(null, true, 3)),
      () -> assertTrue(AC0.close(null, false, 3)),
      () -> assertTrue(AC0.close(Files.newBufferedReader(thisFile.toPath()), false, 3)),
      () -> assertTrue(AC0.close(Files.newBufferedReader(thisFile.toPath()), true, 3)),
      () -> assertThrows(ZeroException.class, () -> AC0.close(new AC0Prepare(5), false, 3)),

      () -> assertTrue(AC0.close(null, 3)),
      () -> assertTrue(AC0.close(Files.newBufferedReader(thisFile.toPath()), 3)),
      () -> assertFalse(AC0.close(new AC0Prepare(5), 3))
    );
  }

  class AC0Prepare implements Closeable {
    private int closeTimesWillSuccessfully = 1;

    public AC0Prepare(int closeTimesWillSuccessfully) {
      this.closeTimesWillSuccessfully = closeTimesWillSuccessfully;
    }

    @Override
    public void close() throws IOException {
      closeTimesWillSuccessfully--;
      if (closeTimesWillSuccessfully != 0) {
        throw new IOException(String.valueOf(closeTimesWillSuccessfully));
      }
    }
  }
}
