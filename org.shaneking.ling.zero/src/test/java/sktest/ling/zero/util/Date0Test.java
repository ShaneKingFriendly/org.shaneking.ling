package sktest.ling.zero.util;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.Date0;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class Date0Test {

  @Test
  void parse() {
    assertAll(
      () -> assertThrows(ParseException.class, () -> Date0.on().parse(Date0.on().ySmSd())),
      () -> assertDoesNotThrow(() -> Date0.on().parse(Date0.on().date()))
    );
  }
}
