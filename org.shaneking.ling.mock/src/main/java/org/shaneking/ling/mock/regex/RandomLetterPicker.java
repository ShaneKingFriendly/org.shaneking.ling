package org.shaneking.ling.mock.regex;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

class RandomLetterPicker {
  private final List<String> letters;
  private final int size;
  private final Random random;

  private RandomLetterPicker(Builder b) {
    letters = Collections.unmodifiableList(b.getLetters());
    if (b.getRandom() == null) {
      random = new Random();
    } else {
      random = b.getRandom();
    }

    size = letters.size();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static RandomLetterPicker constructByCharacterRange(final List<String> bounds) {
    Builder definedPickerBuilder = builder();
    int bufferSize = bounds.size();

    for (int i = 0; i < bufferSize; i += 2) {
      int beginCode = bounds.get(i).charAt(0);
      int endCode = bounds.get(i + 1).charAt(0);

      if (beginCode > endCode) {
        throw new RuntimeException("Detected invalid character range: ["
          + (char) beginCode + "-" + (char) endCode + "]");
      }

      for (int code = beginCode; code <= endCode; code++) {
        definedPickerBuilder.add(String.valueOf((char) code));
      }
    }

    return definedPickerBuilder.build();
  }

  public String pickRandomLetter() {
    return letters.get(random.nextInt(size));
  }

  @Getter
  public static class Builder {
    private List<String> letters;
    private Random random;

    public Builder() {
      letters = new ArrayList<>();
      random = null;
    }

    public <E extends Enum<E> & Letter> Builder addAllByEnum(Class<E> enumClass) {
      letters.addAll(Arrays.stream(enumClass.getEnumConstants())
        .map(e -> e.getLetter())
        .collect(Collectors.toList()));
      return this;
    }

    public Builder addAll(List<String> list) {
      letters.addAll(list);
      return this;
    }

    public Builder add(String letter) {
      letters.add(letter);
      return this;
    }

    public Builder remove(String remove) {
      letters = letters.stream()
        .filter(l -> !l.equals(remove))
        .collect(Collectors.toList());
      return this;
    }

    public Builder setRandom(Random random) {
      this.random = random;
      return this;
    }

    public RandomLetterPicker build() {
      return new RandomLetterPicker(this);
    }
  }
}
