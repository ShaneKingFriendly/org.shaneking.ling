package sktest.ling.mock.type;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.mock.type.Web;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Regex0;

import static org.junit.jupiter.api.Assertions.*;

class WebTest {

  @Test
  void protocol() {
    assertAll(
      () -> assertTrue(List0.newArrayList(Web.PROTOCOL).contains(Web.protocol()))
    );
  }

  @Test
  void tld() {
    assertAll(
      () -> assertTrue(List0.newArrayList(Web.TLD).contains(Web.tld()))
    );
  }

  @Test
  void domain() {
    assertAll(
      () -> assertTrue(Web.domain().contains(String0.DOT)),
      () -> assertTrue(Web.domain("shaneking.org").contains("shaneking.org"))
    );
  }

  @Test
  void url() {
    assertAll(
      () -> assertEquals(4, Web.url().split(String0.SLASH).length),
      () -> assertEquals(4, Web.url("http", "localhost").split(String0.SLASH).length)
    );
  }

  @Test
  void email() {
    assertAll(
      () -> assertTrue(Web.email().split(Regex0.DOT).length > 2),
      () -> assertTrue(Web.email("shaneking.org").split(Regex0.DOT).length > 2)
    );
  }

  @Test
  void ip() {
    assertAll(
      () -> assertEquals(4, Web.ip().split(Regex0.DOT).length)
    );
  }
}
