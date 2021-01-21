package org.shaneking.ling.zero.net;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;
import org.shaneking.ling.zero.security.SR0;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Regex0;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class InetAddress0 {
  public static final long YEAR = 1000L * 60 * 60 * 24 * 365;
  public static final long EXPIRATION = YEAR * 100;
  public static final List<CacheEntry0> LOCAL_ADDRESS_LIST = List0.newArrayList();

  public static boolean loadLocalHosts() {
    boolean rtn = false;
    if (LOCAL_ADDRESS_LIST.isEmpty()) {
      try {
        Files.lines(new File(System.getProperty("os.name").startsWith("Windows") ? "c:/windows/System32/drivers/etc/hosts" : "/etc/hosts").toPath()).forEachOrdered(line -> {
          if (!String0.isNullOrEmpty(line) && !line.startsWith(String0.POUND)) {
            String[] parts = line.split(Regex0.BLACKS);
            if (parts.length > 1) {
              for (int i = 1; i < parts.length; i++) {
                LOCAL_ADDRESS_LIST.add(new CacheEntry0(parts[i], parts[0]));
              }
            }
          }
        });
        rtn = true;
      } catch (IOException e) {
        log.error(String0.nullOrEmptyTo(e.getMessage(), e.toString()), e);
      }
    }
    return rtn;
  }

  public static boolean reloadLocalHosts() {
    LOCAL_ADDRESS_LIST.clear();
    return loadLocalHosts();
  }

  public static boolean existInLocalHosts(String hostName) {
    return LOCAL_ADDRESS_LIST.parallelStream().filter(cacheEntry0 -> cacheEntry0.hostName.equalsIgnoreCase(hostName)).count() > 0;
  }

  public static boolean putCustomHost(String hostName, String hostAddress) {
    return putCustomHost(hostName, new String[]{hostAddress});
  }

  public static boolean putCustomHost(String hostName, String[] hostAddresses) {
    boolean rtn = false;
    if (!existInLocalHosts(hostName)) {
      getAddressCacheMap().put(hostName, createCacheEntry(hostName, hostAddresses));
      rtn = true;
    }
    return rtn;
  }

  public static boolean rmvCustomHost(String hostName) {
    return getAddressCacheMap().remove(hostName) != null;
  }

  public static boolean isCustomHost(CacheEntry0 cacheEntry) {
    // JVM default expiration is 30s, if grate then one year is custom hosts, because we set 100 years
    return (cacheEntry.expiration - System.currentTimeMillis()) > YEAR;
  }

  public static Object createCacheEntry(String hostName, String[] hostAddresses) {
    try {
      InetAddress[] addresses = new InetAddress[hostAddresses.length];
      for (int i = 0; i < addresses.length; i++) {
        // addresses[i] = InetAddress.getByAddress(hostName, toBytes(hostAddresses[i]));
        addresses[i] = InetAddress.getByAddress(hostName, InetAddress.getByName(hostAddresses[i]).getAddress());
      }
      Class<?> clazz = Class.forName("java.net.InetAddress$CacheEntry");
      Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
      constructor.setAccessible(true);
      return constructor.newInstance(addresses, System.currentTimeMillis() + EXPIRATION);
    } catch (Exception e) {
      throw new ZeroException(e);
    }
  }

  public static Map<String, Object> getAddressCacheMap() {
    try {
      final Field cacheField = InetAddress.class.getDeclaredField("addressCache");
      cacheField.setAccessible(true);
      final Object addressCache = cacheField.get(InetAddress.class);

      Class<?> clazz = addressCache.getClass();
      final Field cacheMapField = clazz.getDeclaredField("cache");
      cacheMapField.setAccessible(true);
      return (Map<String, Object>) cacheMapField.get(addressCache);
    } catch (Exception e) {
      throw new ZeroException(e);
    }
  }

  public static String customAddress(String hostName) {
    List<String> customAddresses = customAddresses(hostName);
    return customAddresses.size() > 0 ? customAddresses.get(SR0.nextInt(customAddresses.size())) : null;
  }

  public static String virtualAddress(String hostName) {
    List<String> virtualAddresses = virtualAddresses(hostName);
    return virtualAddresses.size() > 0 ? virtualAddresses.get(SR0.nextInt(virtualAddresses.size())) : null;
  }

  public static List<String> customAddresses(String hostName) {
    return virtualHosts(getAddressCacheMap().get(hostName)).stream().filter(InetAddress0::isCustomHost).map(CacheEntry0::getHostAddress).collect(Collectors.toList());
  }

  public static List<String> virtualAddresses(String hostName) {
    return virtualHosts(getAddressCacheMap().get(hostName)).stream().map(CacheEntry0::getHostAddress).collect(Collectors.toList());
  }

  public static List<CacheEntry0> virtualHosts(Object entry) {
    if (entry == null) {
      return List0.newArrayList();
    } else {
      try {
        Class<?> clazz = entry.getClass();
        Field expirationField = clazz.getDeclaredField("expiration");
        expirationField.setAccessible(true);
        final long expiration = (Long) expirationField.get(entry);
        Field addressesField = clazz.getDeclaredField("addresses");
        addressesField.setAccessible(true);
        return List0.newArrayList((InetAddress[]) addressesField.get(entry)).stream().map(address -> new CacheEntry0(address.getHostName(), address.getHostAddress(), expiration)).collect(Collectors.toList());
      } catch (Exception e) {
        throw new ZeroException(e);
      }
    }
  }

  @Accessors(chain = true)
  @ToString
  public static class CacheEntry0 {
    @Getter
    @Setter
    String hostName;
    @Getter
    @Setter
    String hostAddress;
    @Getter
    @Setter
    long expiration;

    public CacheEntry0() {
    }

    public CacheEntry0(String hostName, String hostAddress) {
      this.hostName = hostName;
      this.hostAddress = hostAddress;
    }

    public CacheEntry0(String hostName, String hostAddress, long expiration) {
      this.hostName = hostName;
      this.hostAddress = hostAddress;
      this.expiration = expiration;
    }
  }
}
