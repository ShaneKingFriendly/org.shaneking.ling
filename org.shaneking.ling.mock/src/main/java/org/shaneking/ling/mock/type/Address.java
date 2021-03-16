package org.shaneking.ling.mock.type;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.jackson.databind.OM3;
import org.shaneking.ling.zero.io.File0;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.persistence.Tuple;
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
  public static final Map<String, String> DICT = Map0.newHashMap();
  public static final Map<String, Tuple.Triple<String, String, List<String>>> TREE = Map0.newHashMap();

  static {
    try {
      URL url = Address.class.getResource(String0.SLASH + Address.class.getName().replaceAll(Regex0.DOT, String0.SLASH) + File0.suffix(File0.TYPE_JSON));
      DICT.putAll(OM3.readValue(String.join(String0.EMPTY, Files.readAllLines(Paths.get(url.toURI()))), new TypeReference<Map<String, String>>() {
      }));
    } catch (Exception e) {
      log.error(e.toString());
    }

  }


}
