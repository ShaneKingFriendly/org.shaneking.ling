package sktest.ling.zero.time;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.time.LD0;
import org.shaneking.ling.zero.util.Date0;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class LD0Test {

  @Test
  void date() {
    assertEquals(Date0.on().date(), LD0.on().date());
  }

  @Test
  void format() {
    assertEquals(Date0.on().format(Date0.Y_M_D), LD0.on().format(Date0.Y_M_D));
  }

  @Test
  void parse() {
    assertAll(
      () -> assertDoesNotThrow(() -> LD0.on().parse(Date0.on().date())),
      () -> assertThrows(DateTimeParseException.class, () -> LD0.on().parse(LD0.on().ySmSd()))
    );
  }

  @Test
  void ymd() {
    assertEquals(Date0.on().ymd(), LD0.on().ymd());
  }

  @Test
  void ySmSd() {
    assertEquals(Date0.on().ySmSd(), LD0.on().ySmSd());
  }
}
