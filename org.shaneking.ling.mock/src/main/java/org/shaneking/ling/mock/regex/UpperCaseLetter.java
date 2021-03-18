package org.shaneking.ling.mock.regex;

enum UpperCaseLetter implements Letter {
  A("A"), B("B"), C("C"), D("D"), E("E"), F("F"), G("G"), H("H"),
  I("I"), J("J"), K("K"), L("L"), M("M"), N("N"), O("O"), P("P"),
  Q("Q"), R("R"), S("S"), T("T"), U("U"), V("V"), W("W"), X("X"),
  Y("Y"), Z("Z");

  private final String letter;

  UpperCaseLetter(String letter) {
    this.letter = letter;
  }

  @Override
  public String getLetter() {
    return letter;
  }
}
