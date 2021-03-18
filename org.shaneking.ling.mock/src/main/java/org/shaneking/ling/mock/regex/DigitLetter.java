package org.shaneking.ling.mock.regex;

enum DigitLetter implements Letter {
  ZERO("0"), ONE("1"), TWO("2"), THREE("3"), FOUR("4"),
  FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9");

  private final String letter;

  DigitLetter(String letter) {
    this.letter = letter;
  }

  @Override
  public String getLetter() {
    return letter;
  }
}
