package sktest.ling.zero.time;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.time.LocalDate0;
import org.shaneking.ling.zero.util.Date0;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class LocalDate0Test {

  @Test
  void date() {
    assertEquals(Date0.on().date(), LocalDate0.on().date());
  }

  @Test
  void format() {
    assertEquals(Date0.on().format(Date0.Y_M_D), LocalDate0.on().format(Date0.Y_M_D));
  }

  @Test
  void parse() {
    assertAll(
      () -> assertThrows(DateTimeParseException.class, () -> LocalDate0.on().parse(Date0.on().ySmSd())),
      () -> assertDoesNotThrow(() -> LocalDate0.on().parse(Date0.on().date()))
    );
  }

  @Test
  void ymd() {
    assertEquals(Date0.on().ymd(), LocalDate0.on().ymd());
  }

  @Test
  void ySmSd() {
    assertEquals(Date0.on().ySmSd(), LocalDate0.on().ySmSd());
  }
}
