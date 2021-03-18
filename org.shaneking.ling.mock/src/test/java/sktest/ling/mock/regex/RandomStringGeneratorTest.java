package sktest.ling.mock.regex;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.mock.regex.RandomStringGenerator;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class RandomStringGeneratorTest {
  @Test
  public void shouldGenerateRandomStringFromPattern() {
    RandomStringGenerator generator = new RandomStringGenerator();
    String randomString = generator.generateFromPattern("cCn!.sb");
    Pattern patternToProve =
      Pattern.compile("^[a-z][A-Z][0-9][~`!@$%^&*()\\-_+={}\\[\\]|\\\\:;\"'.<>?/#,]"
        + "[a-zA-Z0-9~`!@$%^&*()\\-_+={}\\[\\]|\\\\:;\"'.<>?/#,]"
        + "[A-Za-z0-9./].$");
    assertTrue(patternToProve.matcher(randomString).find());
  }

  @Test
  public void shouldOccurExceptionWhenItIsWithInvalidPatternChar() {
    assertThrows(RuntimeException.class, () -> new RandomStringGenerator().generateFromPattern("cCn?.sb"));
  }

  @Test
  public void shouldGenerateRandomStringFromRegex() {
    RandomStringGenerator generator = new RandomStringGenerator();
    {
      String randomString =
        generator.generateByRegex("\\w+\\d*\\W\\D{0,3}a\\{0,3}.\\s\\S[0-9][a-zA-Z]X");
      Pattern patternToProve =
        Pattern.compile("^[a-zA-Z0-9_]+[0-9]*[~`!@$%^&*()\\-+={}\\[\\]|\\\\:;\"'.<>?/#,]"
          + "[a-zA-Z0-9~`!@$%^&*()\\-_+={}\\[\\]|\\\\:;\"'.<>?/#,]{0,3}"
          + "a\\{0,3}.[ \t].[0-9][a-zA-Z]X$");
      assertTrue(patternToProve.matcher(randomString).find());
    }
    {
      // regression test
      String randomString =
        generator.generateByRegex("\\w+\\d*\\W\\D{0,3}a\\{0,3}.\\s\\S[0-9][a-zA-Z]X");
      Pattern patternToProve =
        Pattern.compile("^[a-zA-Z0-9_]+[0-9]*[~`!@$%^&*()\\-+={}\\[\\]|\\\\:;\"'.<>?/#,]"
          + "[a-zA-Z0-9~`!@$%^&*()\\-_+={}\\[\\]|\\\\:;\"'.<>?/#,]{0,3}"
          + "a\\{0,3}.[ \t].[0-9][a-zA-Z]X$");
      assertTrue(patternToProve.matcher(randomString).find());
    }
  }

  @Test
  public void shouldIgnoreInvalidRange() {
    RandomStringGenerator generator = new RandomStringGenerator();
    {
      String randomString = generator.generateByRegex("a{-1,10}b{foo}");
      assertTrue(randomString.equals("a{-1,10}b{foo}"));
    }
    {
      String randomString = generator.generateByRegex("a{");
      assertTrue(randomString.equals("a{"));
    }
    {
      String randomString = generator.generateByRegex("a{,1}");
      assertTrue(randomString.equals("a{,1}"));
    }
  }

  @Test
  public void notSpecifiedDestinationAtBrace() {
    {
      RandomStringGenerator generator = new RandomStringGenerator(10);
      assertEquals("aaaaaaaaaa", generator.generateByRegex("a{10,}"));
    }
    {
      RandomStringGenerator generator = new RandomStringGenerator(10);
      assertTrue(generator.generateByRegex("a{0,}").matches("a{0,10}"));
    }
  }

  @Test
  public void invalidNotSpecifiedDestionationAtBrace() {
    RandomStringGenerator generator = new RandomStringGenerator(10);
    assertThrows(RuntimeException.class, () -> generator.generateByRegex("a{100,}"));
  }

  @Test
  public void shouldOccurExceptionWhenExistsInvalidRange() {
    assertThrows(RuntimeException.class, () -> new RandomStringGenerator().generateByRegex("a{5,0}"));
  }

  @Test
  public void shouldOccurExceptionWhenExistsInvalidEscapeCharacter() {
    assertThrows(RuntimeException.class, () -> new RandomStringGenerator().generateByRegex("foo\\"));
  }

  @Test
  public void shouldOccurExceptionWhenUsingInvalidRangeChar() {
    assertThrows(RuntimeException.class, () -> new RandomStringGenerator().generateByRegex("[$-%]"));
  }

  @Test
  public void shouldOccurExceptionWhenUsingInvalidRangeOrder() {
    assertThrows(RuntimeException.class, () -> new RandomStringGenerator().generateByRegex("[z-a]"));
  }

  @Test
  public void shouldOccurExceptionWhenRangeNotationIsNotClosed() {
    assertThrows(RuntimeException.class, () -> new RandomStringGenerator().generateByRegex("[a-"));
  }

  @Test
  public void shouldSetAndGetNumOfUpperLimitSuccessfully() {
    RandomStringGenerator generator = new RandomStringGenerator();
    generator.setNumOfUpperLimit(0);
    assertEquals(0, generator.getNumOfUpperLimit());
    assertEquals("XX", generator.generateByRegex("X.*X"));
  }

  @Test
  public void shouldSetNumOfUpperLimitSuccessfullyByConstructor() {
    RandomStringGenerator generator = new RandomStringGenerator(0);
    assertEquals(0, generator.getNumOfUpperLimit());
    assertEquals("XX", generator.generateByRegex("X.*X"));
  }

  @Test
  public void forThreadLocalRandom() {
    RandomStringGenerator generator = new RandomStringGenerator(ThreadLocalRandom.current());
    String randomString =
      generator.generateByRegex("\\w+\\d*\\W\\D{0,3}a\\{0,3}.\\s\\S[0-9][a-zA-Z]X");
    Pattern patternToProve =
      Pattern.compile("^[a-zA-Z0-9_]+[0-9]*[~`!@$%^&*()\\-+={}\\[\\]|\\\\:;\"'.<>?/#,]"
        + "[a-zA-Z0-9~`!@$%^&*()\\-_+={}\\[\\]|\\\\:;\"'.<>?/#,]{0,3}"
        + "a\\{0,3}.[ \t].[0-9][a-zA-Z]X$");
    assertTrue(patternToProve.matcher(randomString).find());
  }
}
