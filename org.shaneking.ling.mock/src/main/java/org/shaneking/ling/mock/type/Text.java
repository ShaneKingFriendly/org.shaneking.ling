package org.shaneking.ling.mock.type;

import org.shaneking.ling.zero.lang.Integer0;
import org.shaneking.ling.zero.lang.String0;

import java.util.Collections;
import java.util.stream.Collectors;

public class Text {
  //最常用的 500 个汉字 http://baike.baidu.com/view/568436.htm
  //https://github.com/nuysoft/Mock/blob/refactoring/src/mock/random/text.js
  /**
   * String长度有限制吗？是多少？https://www.toutiao.com/i6893014573322863111/
   * <p>
   * 首先字符串的内容是由一个字符数组 char[] 来存储的，由于数组的长度及索引是整数，且String类中返回字符串长度的方法length() 的返回值也是int ，所以通过查看java源码中的类Integer我们可以看到Integer的最大范围是2^31 -1,由于数组是从0开始的，所以数组的最大长度可以使【0~2^31-1】通过计算是大概4GB。
   * 但是通过翻阅java虚拟机手册对class文件格式的定义以及常量池中对String类型的结构体定义我们可以知道对于索引定义了u2，就是无符号占2个字节，2个字节可以表示的最大范围是2^16 -1 = 65535。其实是65535，但是由于JVM需要1个字节表示结束指令，所以这个范围就为65534了。超出这个范围在编译时期是会报错的，但是运行时拼接或者赋值的话范围是在整形的最大范围。
   */
  public static final String DICT500 = "的一是在不了有和人这中大为上个国我以要他时来用们生到作地于出就分对成会可主发年动同工也能下过子说产种面而方后多定行学法所民得经十三之进着等部度家电力里如水化高自二理起小物现实加量都两体制机当使点从业本去把性好应开它合还因由其些然前外天政四日那社义事平形相全表间样与关各重新线内数正心反你明看原又么利比或但质气第向道命此变条只没结解问意建月公无系军很情者最立代想已通并提直题党程展五果料象员革位入常文总次品式活设及管特件长求老头基资边流路级少图山统接知较将组见计别她手角期根论运农指几九区强放决西被干做必战先回则任取据处队南给色光门即保治北造百规热领七海口东导器压志世金增争济阶油思术极交受联什认六共权收证改清己美再采转更单风切打白教速花带安场身车例真务具万每目至达走积示议声报斗完类八离华名确才科张信马节话米整空元况今集温传土许步群广石记需段研界拉林律叫且究观越织装影算低持音众书布复容儿须际商非验连断深难近矿千周委素技备半办青省列习响约支般史感劳便团往酸历市克何除消构府称太准精值号率族维划选标写存候毛亲快效斯院查江型眼王按格养易置派层片始却专状育厂京识适属圆包火住调满县局照参红细引听该铁价严龙飞";

  public static String word() {
    return word(0, 0);
  }

  public static String word(int min, int max) {
    return Basic.string(Basic.LOWER, Integer0.lt2d(min, 3), Integer0.lt2d(max, 10));
  }

  public static String cword() {
    return cword(0, 0);
  }

  public static String cword(int min, int max) {
    return Basic.string(DICT500, Integer0.lt2d(min, 3), Integer0.lt2d(max, 10));
  }

  public static String sentence() {
    return sentence(0, 0);
  }

  public static String sentence(int min, int max) {
    return String0.upperFirst(String.join(String0.BLANK, Collections.nCopies(Basic.natural(Integer0.lt2d(min, 12), Integer0.lt2d(max, 18)), null).stream().map(i -> word()).collect(Collectors.toList()))) + String0.DOT;
  }

  public static String csentence() {
    return csentence(0, 0);
  }

  public static String csentence(int min, int max) {
    return String.join(String0.BLANK, Collections.nCopies(Basic.natural(Integer0.lt2d(min, 12), Integer0.lt2d(max, 18)), null).stream().map(i -> cword()).collect(Collectors.toList())) + String0.C_DOT;
  }

  public static String paragraph() {
    return paragraph(0, 0);
  }

  public static String paragraph(int min, int max) {
    return String.join(String0.BLANK, Collections.nCopies(Basic.natural(Integer0.lt2d(min, 3), Integer0.lt2d(max, 7)), null).stream().map(i -> sentence()).collect(Collectors.toList()));
  }

  public static String cparagraph() {
    return cparagraph(0, 0);
  }

  public static String cparagraph(int min, int max) {
    return String.join(String0.BLANK, Collections.nCopies(Basic.natural(Integer0.lt2d(min, 3), Integer0.lt2d(max, 7)), null).stream().map(i -> csentence()).collect(Collectors.toList()));
  }

  public static String title() {
    return title(0, 0);
  }

  public static String title(int min, int max) {
    return String.join(String0.BLANK, Collections.nCopies(Basic.natural(Integer0.lt2d(min, 3), Integer0.lt2d(max, 7)), null).stream().map(i -> String0.upperFirst(word())).collect(Collectors.toList()));
  }
}
