package sktest.ling.zero.persistence;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.persistence.Tuple;

import static org.junit.jupiter.api.Assertions.*;

class TupleTest {

  @Test
  void joinWith() {
    assertAll(
      () -> assertEquals("[127.0.0.1]", Tuple.joinWith(String0.DOT).join(Tuple.of(127, 0, 0, 1))),
      () -> assertEquals("(127,0,0,1)", Tuple.joinWith(String0.OPEN_PARENTHESIS, String0.COMMA, String0.CLOSE_PARENTHESIS).join(Tuple.of(127, 0, 0, 1)))
    );
  }

  @Test
  void of() {
    assertAll(
      () -> assertEquals("[]", Tuple.of().toString()),//0
      () -> assertEquals("[1]", Tuple.of(1).toString()),//1
      () -> assertEquals("[1,1]", Tuple.of(1, 1).toString()),//2
      () -> assertEquals("[1,1,1]", Tuple.of(1, 1, 1).toString()),//3
      () -> assertEquals("[1,1,1,1]", Tuple.of(1, 1, 1, 1).toString()),//4
      () -> assertEquals("[1,1,1,1,1]", Tuple.of(1, 1, 1, 1, 1).toString()),//5
      () -> assertEquals("[1,1,1,1,1,1]", Tuple.of(1, 1, 1, 1, 1, 1).toString()),//6
      () -> assertEquals("[1,1,1,1,1,1,1]", Tuple.of(1, 1, 1, 1, 1, 1, 1).toString()),//7
      () -> assertEquals("[1,1,1,1,1,1,1,1]", Tuple.of(1, 1, 1, 1, 1, 1, 1, 1).toString()),//8
      () -> assertEquals("[1,1,1,1,1,1,1,1,1]", Tuple.of(1, 1, 1, 1, 1, 1, 1, 1, 1).toString()),//9
      () -> assertEquals("[1,1,1,1,1,1,1,1,1,1]", Tuple.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1).toString()),//10
      () -> assertEquals("[1,1,1,1,1,1,1,1,1,1,1]", Tuple.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1).toString()),//11
      () -> assertEquals("[1,1,1,1,1,1,1,1,1,1,1,1]", Tuple.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1).toString()),//12
      () -> assertEquals("[1,1,1,1,1,1,1,1,1,1,1,1,1]", Tuple.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1).toString())//13
    );
  }

  @Test
  void getN() {
    assertAll(
      () -> assertEquals(1, Tuple.getFirst(Tuple.of(1))),
      () -> assertEquals(2, Tuple.getSecond(Tuple.of(1, 2))),
      () -> assertEquals(3, Tuple.getThird(Tuple.of(1, 2, 3))),
      () -> assertEquals(4, Tuple.getFourth(Tuple.of(1, 2, 3, 4))),
      () -> assertEquals(5, Tuple.getFifth(Tuple.of(1, 2, 3, 4, 5))),
      () -> assertEquals(6, Tuple.getSixth(Tuple.of(1, 2, 3, 4, 5, 6))),
      () -> assertEquals(7, Tuple.getSeventh(Tuple.of(1, 2, 3, 4, 5, 6, 7))),
      () -> assertEquals(8, Tuple.getEighth(Tuple.of(1, 2, 3, 4, 5, 6, 7, 8))),
      () -> assertEquals(9, Tuple.getNinth(Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9))),
      () -> assertEquals(10, Tuple.getTenth(Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))),
      () -> assertEquals(11, Tuple.<Integer>getN(Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 10)),
      () -> assertEquals(12, Tuple.<Integer>getN(Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 11)),
      () -> assertEquals(13, Tuple.<Integer>getN(Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13), 12))
    );
  }

  @Test
  void prepend() {
    assertEquals("[1,[2,3]]", Tuple.of(2, 3).prepend(1).toString());
  }

  @Test
  void testEquals() {
    assertEquals(Tuple.of(1, 2), Tuple.of(1, 2));
  }

  @Test
  void testToString() {
    assertAll(
      () -> assertEquals("[127,0,0,1]", Tuple.of(127, 0, 0, 1).toString()),
      () -> assertEquals("[127.0.0.1]", Tuple.of(127, 0, 0, 1).toString(String0.DOT)),
      () -> assertEquals("(127,0,0,1)", Tuple.of(127, 0, 0, 1).toString(String0.OPEN_PARENTHESIS, String0.COMMA, String0.CLOSE_PARENTHESIS))
    );
  }

  @Test
  void testHashCode() {
    assertTrue(Tuple.of(1, 2, 3).hashCode() > 0);
  }
}
