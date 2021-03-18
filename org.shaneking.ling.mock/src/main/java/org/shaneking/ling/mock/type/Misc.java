package org.shaneking.ling.mock.type;

import org.shaneking.ling.zero.util.Date0;

public class Misc {
  public static final int[] ID_RANK = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
  public static final String[] ID_LAST = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

  public static String id() {
    String rtn = Basic.list(Address.CODE) + Date0.on().ymd() + Basic.string(Basic.NUMBER, 3);
    int sum = 0;
    for (int i = 0; i < rtn.length(); i++) {
      sum += rtn.charAt(i) * ID_RANK[i];
    }
    rtn += ID_LAST[sum % 11];
    return rtn;
  }
}
