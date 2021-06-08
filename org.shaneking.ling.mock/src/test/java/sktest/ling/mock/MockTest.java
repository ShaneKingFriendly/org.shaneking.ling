package sktest.ling.mock;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.mock.Mock;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class MockTest {
  @Test
  void aaa() {
    assertAll(
      () -> assertNotNull(new Mock())
    );
  }

  @Test
  void patStr() {
    assertTrue(Pattern.compile("^[a-z][A-Z][0-9][~`!@$%^&*()\\-_+={}\\[\\]|\\\\:;\"'.<>?/#,][a-zA-Z0-9~`!@$%^&*()\\-_+={}\\[\\]|\\\\:;\"'.<>?/#,][A-Za-z0-9./].$").matcher(Mock.patStr("cCn!.sb")).find());
  }

  @Test
  void regStr() {
    assertTrue(Pattern.compile("^[a-zA-Z0-9_]+[0-9]*[~`!@$%^&*()\\-+={}\\[\\]|\\\\:;\"'.<>?/#,][a-zA-Z0-9~`!@$%^&*()\\-_+={}\\[\\]|\\\\:;\"'.<>?/#,]{0,3}a\\{0,3}.[ \t].[0-9][a-zA-Z]X$").matcher(Mock.regStr("\\w+\\d*\\W\\D{0,3}a\\{0,3}.\\s\\S[0-9][a-zA-Z]X")).find());
  }
}
