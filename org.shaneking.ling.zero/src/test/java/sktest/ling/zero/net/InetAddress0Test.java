package sktest.ling.zero.net;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.net.InetAddress0;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class InetAddress0Test {

  @Test
  void existInLocalHosts() {
    assertFalse(InetAddress0.existInLocalHosts("shaneking.org"));
    assertFalse(InetAddress0.existInLocalHosts("inet.address.test"));
  }

  @Test
  void loadLocalHosts() {
    assertTrue(InetAddress0.loadLocalHosts());
  }

  @Test
  void putCustomHost() throws UnknownHostException {
    assertTrue(InetAddress0.putCustomHost("inet.address.test", "44.44.44.44"));
    assertEquals("44.44.44.44", InetAddress0.customAddress("inet.address.test"));
    assertEquals("44.44.44.44", InetAddress0.virtualAddress("inet.address.test"));
    assertNull(InetAddress0.virtualAddress("inet.address.test2"));
    assertEquals("44.44.44.44", InetAddress.getByName("inet.address.test").getHostAddress());

    assertEquals(1, String0.ALPHABET.split(String0.COMMA).length);
    assertEquals(String0.ALPHABET, String.join(String0.COMMA, String0.ALPHABET.split(String0.COMMA)));

    assertEquals(1, String0.EMPTY.split(String0.COMMA).length);
    assertEquals(String0.EMPTY, String.join(String0.COMMA, String0.EMPTY.split(String0.COMMA)));
  }

  @Test
  void reloadLocalHosts() {
    assertTrue(InetAddress0.reloadLocalHosts());
  }

  @Test
  void rmvVirtualHost() {
    assertFalse(InetAddress0.rmvCustomHost("shaneking.org"));
  }

  @Nested
  class CacheEntry0Test {
    @Test
    void test() {
      assertEquals("InetAddress0.CacheEntry0(hostName=hostName, hostAddress=hostAddress, expiration=1)", new InetAddress0.CacheEntry0().setHostName("hostName").setHostAddress("hostAddress").setExpiration(1L).toString());
    }
  }
}
