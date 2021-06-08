package sktest.ling.persistence;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.persistence.Keyword;
import org.shaneking.ling.persistence.entity.Identified;
import org.shaneking.ling.test.SKUnit;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.List0;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeywordTest extends SKUnit {

  @Test
  void aaa() {
    assertAll(
      () -> assertNotNull(new Keyword())
    );
  }

  @Test
  void parenthesis() {
    assertAll(
      () -> assertEquals("count(1)", Keyword.parenthesis(Keyword.FN__COUNT, 1)),
      () -> assertEquals("group_concat(id)", Keyword.parenthesis(Keyword.FN__GROUP_CONCAT, Identified.FIELD__ID)),
      () -> assertEquals("group_concat(id,id)", Keyword.parenthesis(Keyword.FN__GROUP_CONCAT, Identified.FIELD__ID, Identified.COLUMN__ID)),
      () -> assertEquals("group_concat(id,id)", Keyword.parenthesis(Keyword.FN__GROUP_CONCAT, List0.newArrayList(Identified.FIELD__ID, Identified.COLUMN__ID)))
    );
    Integer i = null;
    String s = null;
    List<String> l = null;
    String[] sa = null;
    assertAll(
      () -> assertThrows(NullPointerException.class, () -> Keyword.parenthesis(null, 1)),
      () -> assertThrows(NullPointerException.class, () -> Keyword.parenthesis(String0.EMPTY, i)),
      () -> assertThrows(NullPointerException.class, () -> Keyword.parenthesis(null, i)),
      () -> assertThrows(NullPointerException.class, () -> Keyword.parenthesis(null, String0.EMPTY)),
      () -> assertThrows(NullPointerException.class, () -> Keyword.parenthesis(String0.EMPTY, s)),
      () -> assertThrows(NullPointerException.class, () -> Keyword.parenthesis(null, s)),
      () -> assertThrows(NullPointerException.class, () -> Keyword.parenthesis(null, s, s)),
      () -> assertThrows(NullPointerException.class, () -> Keyword.parenthesis(String0.EMPTY, sa)),
      () -> assertThrows(NullPointerException.class, () -> Keyword.parenthesis(null, l)),
      () -> assertThrows(NullPointerException.class, () -> Keyword.parenthesis(String0.EMPTY, l))
    );
  }
}
