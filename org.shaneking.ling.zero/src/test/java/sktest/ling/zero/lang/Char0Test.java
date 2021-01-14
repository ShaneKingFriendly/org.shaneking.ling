package sktest.ling.zero.lang;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.Char0;

import static org.junit.jupiter.api.Assertions.*;

class Char0Test {

  @Test
  void isAlphabet() {
    assertAll(
      () -> assertTrue(Char0.isAlphabet('a')),
      () -> assertTrue(Char0.isAlphabet('Q')),
      () -> assertFalse(Char0.isAlphabet('1')),
      () -> assertFalse(Char0.isAlphabet('!'))
    );
  }

  @Test
  void isAlphabetOrDigital() {
    assertAll(
      () -> assertTrue(Char0.isAlphabetOrDigital('a')),
      () -> assertTrue(Char0.isAlphabetOrDigital('Q')),
      () -> assertTrue(Char0.isAlphabetOrDigital('1')),
      () -> assertTrue(Char0.isAlphabetOrDigital('0')),
      () -> assertFalse(Char0.isAlphabetOrDigital('!')),
      () -> assertFalse(Char0.isAlphabetOrDigital('@'))
    );
  }

  @Test
  void isDigital() {
    assertAll(
      () -> assertTrue(Char0.isDigital('1')),
      () -> assertTrue(Char0.isDigital('0')),
      () -> assertFalse(Char0.isDigital('a')),
      () -> assertFalse(Char0.isDigital('!'))
    );
  }
}
