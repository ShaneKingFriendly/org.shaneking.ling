package sktest.ling.mock.type;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.mock.type.Text;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Regex0;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TextTest {
  @Test
  void aaa() {
    assertAll(
      () -> assertNotNull(new Text())
    );
  }

  @Test
  void word() {
    assertAll(
      () -> assertTrue(Text.word().length() >= 3),
      () -> assertTrue(Text.word(7, 13).length() >= 7)
    );
  }

  @Test
  void cword() {
    assertAll(
      () -> assertTrue(Text.cword().length() >= 3),
      () -> assertTrue(Text.cword(7, 13).length() >= 7)
    );
  }

  @Test
  void sentence() {
    assertAll(
      () -> assertTrue(Text.sentence().split(String0.BLANK).length >= 12),
      () -> assertTrue(Text.sentence(13, 21).split(String0.BLANK).length >= 13)
    );
  }

  @Test
  void csentence() {
    assertAll(
      () -> assertTrue(Text.csentence().split(String0.BLANK).length >= 12),
      () -> assertTrue(Text.csentence(13, 21).split(String0.BLANK).length >= 13)
    );
  }

  @Test
  void paragraph() {
    assertAll(
      () -> assertTrue(Text.paragraph().split(Regex0.DOT).length >= 3),
      () -> assertTrue(Text.paragraph(5, 8).split(Regex0.DOT).length >= 5)
    );
  }

  @Test
  void cparagraph() {
    assertAll(
      () -> assertTrue(Text.cparagraph().split(String0.C_DOT).length >= 3),
      () -> assertTrue(Text.cparagraph(5, 8).split(String0.C_DOT).length >= 5)
    );
  }

  @Test
  void title() {
    List<String> title = Arrays.stream(Text.title(5, 8).split(String0.BLANK)).map(s -> String.valueOf(s.charAt(0))).collect(Collectors.toList());
    assertAll(
      () -> assertTrue(Text.title().split(String0.BLANK).length >= 3),
      () -> assertLinesMatch(title, title.stream().map(String::toUpperCase).collect(Collectors.toList()))
    );
  }
}
