package sktest.ling.mock.type;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.shaneking.ling.mock.type.Address;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class AddressTest {

  @Test
  void region() {
    assertTrue(List0.newArrayList(Address.REGION).contains(Address.region()));
  }

  @Test
  void province() {
    assertNotNull(Address.province());
    //所有城市含市
    for (Map.Entry<String, Map<String, List<String>>> province : Address.TREE.entrySet()) {
      for (Map.Entry<String, List<String>> city : province.getValue().entrySet()) {
        if (!Address.DICT.get(city.getKey()).endsWith("市")) {
          log.warn(Address.DICT.get(city.getKey()));
          /*
2021-03-18 19:05:25.626 WARN  main sktest.ling.mock.type.AddressTest:30 - 甘南藏族自治州
2021-03-18 19:05:25.734 WARN  main sktest.ling.mock.type.AddressTest:30 - 文山壮族苗族自治州
2021-03-18 19:05:25.735 WARN  main sktest.ling.mock.type.AddressTest:30 - 红河哈尼族彝族自治州
2021-03-18 19:05:25.735 WARN  main sktest.ling.mock.type.AddressTest:30 - 楚雄彝族自治州
2021-03-18 19:05:25.736 WARN  main sktest.ling.mock.type.AddressTest:30 - 伊犁哈萨克自治州
2021-03-18 19:05:25.737 WARN  main sktest.ling.mock.type.AddressTest:30 - 克孜勒苏柯尔克孜自治州
2021-03-18 19:05:25.737 WARN  main sktest.ling.mock.type.AddressTest:30 - 塔城地区
2021-03-18 19:05:25.738 WARN  main sktest.ling.mock.type.AddressTest:30 - 阿勒泰地区
2021-03-18 19:05:25.738 WARN  main sktest.ling.mock.type.AddressTest:30 - 吐鲁番地区
2021-03-18 19:05:25.739 WARN  main sktest.ling.mock.type.AddressTest:30 - 哈密地区
2021-03-18 19:05:25.739 WARN  main sktest.ling.mock.type.AddressTest:30 - 昌吉回族自治州
2021-03-18 19:05:25.739 WARN  main sktest.ling.mock.type.AddressTest:30 - 巴音郭楞蒙古自治州
2021-03-18 19:05:25.740 WARN  main sktest.ling.mock.type.AddressTest:30 - 阿克苏地区
2021-03-18 19:05:25.740 WARN  main sktest.ling.mock.type.AddressTest:30 - 黔西南布依族苗族自治州
2021-03-18 19:05:25.741 WARN  main sktest.ling.mock.type.AddressTest:30 - 黔东南苗族侗族自治州
2021-03-18 19:05:25.741 WARN  main sktest.ling.mock.type.AddressTest:30 - 黔南布依族苗族自治州
2021-03-18 19:05:25.742 WARN  main sktest.ling.mock.type.AddressTest:30 - 离岛
2021-03-18 19:05:25.742 WARN  main sktest.ling.mock.type.AddressTest:30 - 澳门半岛
2021-03-18 19:05:25.743 WARN  main sktest.ling.mock.type.AddressTest:30 - 香港岛
2021-03-18 19:05:25.743 WARN  main sktest.ling.mock.type.AddressTest:30 - 九龙
2021-03-18 19:05:25.746 WARN  main sktest.ling.mock.type.AddressTest:30 - 新界
2021-03-18 19:05:25.747 WARN  main sktest.ling.mock.type.AddressTest:30 - 延边朝鲜族自治州
2021-03-18 19:05:25.747 WARN  main sktest.ling.mock.type.AddressTest:30 - 大兴安岭地区
2021-03-18 19:05:25.748 WARN  main sktest.ling.mock.type.AddressTest:30 - 黄南藏族自治州
2021-03-18 19:05:25.748 WARN  main sktest.ling.mock.type.AddressTest:30 - 果洛藏族自治州
2021-03-18 19:05:25.749 WARN  main sktest.ling.mock.type.AddressTest:30 - 海南藏族自治州
2021-03-18 19:05:25.750 WARN  main sktest.ling.mock.type.AddressTest:30 - 连江县
2021-03-18 19:05:25.752 WARN  main sktest.ling.mock.type.AddressTest:30 - 新竹县
2021-03-18 19:05:25.754 WARN  main sktest.ling.mock.type.AddressTest:30 - 桃园县
2021-03-18 19:05:25.755 WARN  main sktest.ling.mock.type.AddressTest:30 - 花莲县
2021-03-18 19:05:25.758 WARN  main sktest.ling.mock.type.AddressTest:30 - 苗栗县
2021-03-18 19:05:25.759 WARN  main sktest.ling.mock.type.AddressTest:30 - 澎湖县
2021-03-18 19:05:25.761 WARN  main sktest.ling.mock.type.AddressTest:30 - 阿拉善盟
2021-03-18 19:05:25.762 WARN  main sktest.ling.mock.type.AddressTest:30 - 那曲地区
2021-03-18 19:05:25.764 WARN  main sktest.ling.mock.type.AddressTest:30 - 林芝地区
2021-03-18 19:05:25.765 WARN  main sktest.ling.mock.type.AddressTest:30 - 阿里地区
           */
        }
      }
    }
  }

  @RepeatedTest(3)
  void city() {
    assertTrue(Address.city(true).contains(String0.BLANK));
    String city = Address.city();
    assertTrue(city.length() == 1 ? String0.MINUS.equals(city) : city.length() > 1);
  }

  @RepeatedTest(7)
  void county() {
    assertTrue(Address.county(true).contains(String0.BLANK));
    String county = Address.county();
    assertTrue(county.length() == 1 ? String0.MINUS.equals(county) : county.length() > 1);
  }

  @Test
  void zip() {
    assertEquals(6, Address.zip().length());
  }
}
