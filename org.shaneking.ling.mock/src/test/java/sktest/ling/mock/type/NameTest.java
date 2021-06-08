package sktest.ling.mock.type;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.mock.type.Name;
import org.shaneking.ling.zero.lang.String0;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
  @Test
  void aaa() {
    assertAll(
      () -> assertNotNull(new Name())
    );
  }

  @Test
  void name() {
    assertAll(
      () -> assertTrue(Name.name().contains(String0.BLANK)),
      () -> assertEquals(3, Name.name(true).split(String0.BLANK).length)
    );
  }

  @Test
  void cname() {
    assertTrue(Name.cname().length() >= 2);
  }
}
