package org.shaneking.ling.mock.type;

import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;

public class Web {
  public static final String[] PROTOCOL = "http ftp gopher mailto mid cid news nntp prospero telnet rlogin tn3270 wais".split(String0.BLANK);
  public static final String[] TLD = String.join(String0.BLANK, "com net org edu gov int mil cn"// 域名后缀
    , "com.cn net.cn gov.cn org.cn"// 国内域名
    , "中国 中国互联.公司 中国互联.网络"// 中文国内域名
    , "tel biz cc tv info name hk mobi asia cd travel pro museum coop aero"// 新国际域名// 世界各国域名后缀
    , "ad ae af ag ai al am an ao aq ar as at au aw az ba bb bd be bf bg bh bi bj bm bn bo br bs bt bv bw by bz ca cc cf cg ch ci ck cl cm cn co cq cr cu cv cx cy cz de dj dk dm do dz ec ee eg eh es et ev fi fj fk fm fo fr ga gb gd ge gf gh gi gl gm gn gp gr gt gu gw gy hk hm hn hr ht hu id ie il in io iq ir is it jm jo jp ke kg kh ki km kn kp kr kw ky kz la lb lc li lk lr ls lt lu lv ly ma mc md mg mh ml mm mn mo mp mq mr ms mt mv mw mx my mz na nc ne nf ng ni nl no np nr nt nu nz om qa pa pe pf pg ph pk pl pm pn pr pt pw py re ro ru rw sa sb sc sd se sg sh si sj sk sl sm sn so sr st su sy sz tc td tf tg th tj tk tm tn to tp tr tt tv tw tz ua ug uk us uy va vc ve vg vn vu wf ws ye yu za zm zr zw").split(String0.BLANK);

  // 随机生成一个 URL 协议。
  public static String protocol() {
    return Basic.array(PROTOCOL);
  }

  /*
      随机生成一个顶级域名。
      国际顶级域名 international top-level domain-names, iTLDs
      国家顶级域名 national top-level domainnames, nTLDs
      [域名后缀大全](http://www.163ns.com/zixun/post/4417.html)
  */
  public static String tld() {
    return Basic.array(TLD);
  }

  public static String domain() {
    return domain(null);
  }

  // 随机生成一个域名。
  public static String domain(String tld) {
    return Text.word() + String0.DOT + String0.nullOrEmptyTo(tld, tld());
  }

  public static String url() {
    return url(null, null);
  }

  /*
      随机生成一个 URL。
      [URL 规范](http://www.w3.org/Addressing/URL/url-spec.txt)
          http                    Hypertext Transfer Protocol
          ftp                     File Transfer protocol
          gopher                  The Gopher protocol
          mailto                  Electronic mail address
          mid                     Message identifiers for electronic mail
          cid                     Content identifiers for MIME body part
          news                    Usenet news
          nntp                    Usenet news for local NNTP access only
          prospero                Access using the prospero protocols
          telnet rlogin tn3270    Reference to interactive sessions
          wais                    Wide Area Information Servers
  */
  public static String url(String protocol, String host) {
    return String0.nullOrEmptyTo(protocol, protocol()) + "://" + String0.nullOrEmptyTo(host, domain()) + String0.SLASH + Text.word();
  }

  public static String email() {
    return email(null);
  }

  public static String email(String domain) {
    return Basic.string(Basic.LOWER) + String0.DOT + Text.word() + String0.EMAIL + String0.nullOrEmptyTo(domain, Text.word() + String0.DOT + tld());
  }

  public static String ip() {
    return String.join(String0.DOT, List0.nCopies(4, () -> String.valueOf(Basic.natural(1, 255))));
  }
}
