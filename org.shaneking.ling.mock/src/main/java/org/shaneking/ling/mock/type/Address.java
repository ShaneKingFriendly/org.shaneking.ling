package org.shaneking.ling.mock.type;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.zero.io.File0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;
import org.shaneking.ling.zero.util.Map0;
import org.shaneking.ling.zero.util.Regex0;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Slf4j
public class Address {
  //字典数据来源 http://www.atatech.org/articles/30028?rnd=254259856
  //https://github.com/nuysoft/Mock/blob/refactoring/src/mock/random/address_dict.js
  public static final List<String> CODE = List0.newArrayList();
  public static final Map<String, String> DICT = Map0.newHashMap();
  //  public static final Map<String, String> TCID = Map0.newHashMap();
  public static final Map<String, Map<String, List<String>>> TREE = Map0.newHashMap();

  public static final String[] REGION = {"东北", "华北", "华东", "华中", "华南", "西南", "西北"};

  static {
    try {
      URL url = Address.class.getResource(String0.SLASH + Address.class.getName().replaceAll(Regex0.DOT, String0.SLASH) + File0.suffix(File0.TYPE_JSON));
      DICT.putAll(OM3.readValue(String.join(String0.EMPTY, Files.readAllLines(Paths.get(url.toURI()))), new TypeReference<Map<String, String>>() {
      }));
    } catch (Exception e) {
      ///ignore exception : most scenario use in test case
      log.error(e.toString());
    }
    CODE.addAll(DICT.keySet());
//    TCID.putAll(CODE.parallelStream().collect(Collectors.toMap(c -> DICT.get(c), c -> c)));//Duplicate key 610482
    Map<String, String> pidMap = Map0.newHashMap();
    for (Map.Entry<String, String> entry : DICT.entrySet()) {
      String id = entry.getKey();
      String pid = "0000".equals(id.substring(2, 6)) ? null : "00".equals(id.substring(4, 6)) ? id.substring(0, 2) + "0000" : id.substring(0, 4) + "00";
      if (!String0.isNullOrEmpty(pid)) {
        pidMap.put(id, pid);
      }
    }
    for (Map.Entry<String, String> entry : DICT.entrySet()) {
      String pid = pidMap.get(entry.getKey());
      if (String0.isNullOrEmpty(pid)) {//current is level 1
        TREE.putIfAbsent(entry.getKey(), Map0.newHashMap());
      } else {
        String ppid = pidMap.get(pid);
        if (String0.isNullOrEmpty(ppid)) {//current is level 2
          TREE.getOrDefault(pid, Map0.newHashMap()).putIfAbsent(entry.getKey(), List0.newArrayList());
        } else {//current is level 3
          TREE.getOrDefault(ppid, Map0.newHashMap()).getOrDefault(pid, List0.newArrayList()).add(entry.getKey());
        }
      }
    }
  }

  // 随机生成一个大区。
  public static String region() {
    return Basic.array(REGION);
  }

  // 随机生成一个（中国）省（或直辖市、自治区、特别行政区）。
  public static String province() {
    return DICT.get(Basic.list(List0.newArrayList(TREE.keySet())));
  }

  public static String city() {
    return city(false);
  }

  // 随机生成一个（中国）市。
  public static String city(Boolean prefix) {
    String province = Basic.list(List0.newArrayList(TREE.keySet()));
    String city = Basic.list(List0.newArrayList(TREE.getOrDefault(province, Map0.newHashMap()).keySet()));
    city = String0.isNullOrEmpty(city) ? String0.MINUS : DICT.get(city);
    return prefix ? String.join(String0.BLANK, DICT.get(province), city) : city;
  }

  public static String county() {
    return county(false);
  }

  // 随机生成一个（中国）县。
  public static String county(Boolean prefix) {
    String province = Basic.list(List0.newArrayList(TREE.keySet()));
    String city = Basic.list(List0.newArrayList(TREE.get(province).keySet()));
    String country = String0.isNullOrEmpty(city) ? null : Basic.list(List0.newArrayList(TREE.getOrDefault(province, Map0.newHashMap()).getOrDefault(city, List0.newArrayList())));
    country = String0.isNullOrEmpty(country) ? String0.MINUS : DICT.get(country);
    city = String0.isNullOrEmpty(city) ? String0.MINUS : DICT.get(city);
    return prefix ? String.join(String0.BLANK, DICT.get(province), city, country) : country;
  }

  // 随机生成一个邮政编码（六位数字）。
  public static String zip() {
    return Basic.string(Basic.NUMBER, 6);
  }

}
