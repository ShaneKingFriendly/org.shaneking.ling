package org.shaneking.ling.mock.type;

import org.shaneking.ling.zero.lang.String0;

import java.util.stream.Stream;

public class Name {
  //http://www.ssa.gov/oact/babynames/limits.html
  public static final String[] MALE_FIRST_NAME = {"James", "John", "Robert", "Michael", "William", "David", "Richard", "Charles", "Joseph", "Thomas"
    , "Christopher", "Daniel", "Paul", "Mark", "Donald", "George", "Kenneth", "Steven", "Edward", "Brian"
    , "Ronald", "Anthony", "Kevin", "Jason", "Matthew", "Gary", "Timothy", "Jose", "Larry", "Jeffrey"
    , "Frank", "Scott", "Eric"};

  public static final String[] FEMALE_FIRST_NAME = {"Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer", "Maria", "Susan", "Margaret", "Dorothy"
    , "Lisa", "Nancy", "Karen", "Betty", "Helen", "Sandra", "Donna", "Carol", "Ruth", "Sharon"
    , "Michelle", "Laura", "Sarah", "Kimberly", "Deborah", "Jessica", "Shirley", "Cynthia", "Angela", "Melissa"
    , "Brenda", "Amy", "Anna"};

  public static final String[] FIRST_NAME = Stream.concat(Stream.of(MALE_FIRST_NAME), Stream.of(FEMALE_FIRST_NAME)).toArray(String[]::new);

  public static final String[] LAST_NAME = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson"
    , "Martinez", "Anderson", "Taylor", "Thomas", "Hernandez", "Moore", "Martin", "Jackson", "Thompson", "White"
    , "Lopez", "Lee", "Gonzalez", "Harris", "Clark", "Lewis", "Robinson", "Walker", "Perez", "Hall"
    , "Young", "Allen"};

  //http://baike.baidu.com/view/1719115.htm
  //http://xuanpai.sinaapp.com/
  public static final String[] C_FIRST_NAME = "王李张刘陈杨赵黄周吴徐孙胡朱高林何郭马罗梁宋郑谢韩唐冯于董萧程曹袁邓许傅沈曾彭吕苏卢蒋蔡贾丁魏薛叶阎余潘杜戴夏锺汪田任姜范方石姚谭廖邹熊金陆郝孔白崔康毛邱秦江史顾侯邵孟龙万段雷钱汤尹黎易常武乔贺赖龚文".split(String0.EMPTY);
  //http://www.name999.net/xingming/xingshi/20131004/48.html
  public static final String[] C_LAST_NAME = "伟 芳 娜 秀英 敏 静 丽 强 磊 军 洋 勇 艳 杰 娟 涛 明 超 秀兰 霞 平 刚 桂英".split(String0.BLANK);

  public static String name() {
    return name(false);
  }

  public static String name(boolean mid) {
    return Basic.array(FIRST_NAME) + String0.BLANK + (mid ? Basic.array(FIRST_NAME) + String0.BLANK : String0.EMPTY) + Basic.array(LAST_NAME);
  }

  public static String cname() {
    return Basic.array(C_FIRST_NAME) + Basic.array(C_LAST_NAME);
  }
}
