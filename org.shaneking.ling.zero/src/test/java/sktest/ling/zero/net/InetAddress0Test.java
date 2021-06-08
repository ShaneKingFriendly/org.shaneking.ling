package sktest.ling.zero.net;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;
import org.shaneking.ling.zero.net.InetAddress0;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class InetAddress0Test {

  @Test
  void aaa() {
    assertAll(
      () -> assertNotNull(new InetAddress0())
    );
  }

  @Test
  void createCacheEntry() {
    assertAll(
      () -> assertThrows(ZeroException.class, () -> InetAddress0.createCacheEntry(null, null))
    );
  }

  @Test
  void customAddress() {
    assertAll(
      () -> assertNull(InetAddress0.customAddress(null))
    );
  }

  @Test
  void existInLocalHosts() {
    assertAll(
      () -> assertFalse(InetAddress0.existInLocalHosts("shaneking.org")),
      () -> assertFalse(InetAddress0.existInLocalHosts("inet.address.test")),
      () -> assertTrue(InetAddress0.existInLocalHosts("localhost")),
      () -> assertFalse(InetAddress0.existInLocalHosts("127.0.0.1"))
    );
  }

  @Test
  void loadLocalHosts() {
    assertAll(
      () -> assertTrue(InetAddress0.loadLocalHosts()),
      () -> assertFalse(InetAddress0.loadLocalHosts())
    );
  }

  @Test
  void localHostExactAddress() {
    assertAll(
      () -> assertNotNull(InetAddress0.localHostExactAddress()),
      () -> assertNotNull(InetAddress0.localHostExactAddress())
    );
  }

  @Test
  void putCustomHost() throws UnknownHostException {
    assertAll(
      () -> assertTrue(InetAddress0.putCustomHost("inet.address.test", "44.44.44.44")),
      () -> assertEquals("44.44.44.44", InetAddress0.customAddress("inet.address.test")),
      () -> assertEquals("44.44.44.44", InetAddress0.virtualAddress("inet.address.test")),
      () -> assertNull(InetAddress0.virtualAddress("inet.address.test2")),
      () -> assertEquals("44.44.44.44", InetAddress.getByName("inet.address.test").getHostAddress()),
      () -> assertEquals(1, String0.ALPHABET.split(String0.COMMA).length),
      () -> assertEquals(String0.ALPHABET, String.join(String0.COMMA, String0.ALPHABET.split(String0.COMMA))),
      () -> assertEquals(1, String0.EMPTY.split(String0.COMMA).length),
      () -> assertEquals(String0.EMPTY, String.join(String0.COMMA, String0.EMPTY.split(String0.COMMA)))
    );
  }

  @Test
  void reloadLocalHosts() {
    assertTrue(InetAddress0.reloadLocalHosts());
  }

  @Test
  void rmvCustomHost() {
    assertAll(
      () -> assertFalse(InetAddress0.rmvCustomHost("shaneking.org")),
      () -> assertFalse(InetAddress0.rmvCustomHost("localhost")),
      () -> assertFalse(InetAddress0.rmvCustomHost("127.0.0.1"))
    );
  }

  @Test
  void virtualHosts() {
    assertAll(
      () -> assertThrows(ZeroException.class, () -> InetAddress0.virtualHosts(new Object()))
    );
  }

  @Nested
  class CacheEntry0Test {
    @Test
    void test() {
      assertEquals("InetAddress0.CacheEntry0(hostName=hostName, hostAddress=hostAddress, expiration=1)", new InetAddress0.CacheEntry0().setHostName("hostName").setHostAddress("hostAddress").setExpiration(1L).toString());
    }
  }
}
