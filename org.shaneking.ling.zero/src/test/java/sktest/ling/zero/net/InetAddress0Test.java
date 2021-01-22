package sktest.ling.zero.net;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
  void putVirtualHost() throws UnknownHostException {
    assertTrue(InetAddress0.putCustomHost("inet.address.test", "44.44.44.44"));
    assertEquals("44.44.44.44", InetAddress0.customAddress("inet.address.test"));
    assertEquals("44.44.44.44", InetAddress0.virtualAddress("inet.address.test"));
    assertNull(InetAddress0.virtualAddress("inet.address.test2"));
    assertEquals("44.44.44.44", InetAddress.getByName("inet.address.test").getHostAddress());
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
