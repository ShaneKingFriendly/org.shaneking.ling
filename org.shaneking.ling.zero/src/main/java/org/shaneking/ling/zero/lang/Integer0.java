package org.shaneking.ling.zero.lang;

public class Integer0 {
  //greater than then default
  public static int gt2d(int v, int gt) {
    return gt2d(v, gt, gt);
  }

  //greater than then default
  public static int gt2d(int v, int gt, int d) {
    return v > gt ? d : v;
  }

  //greater than equals then default
  public static int gte2d(int v, int gte) {
    return gte2d(v, gte, gte);
  }

  //greater than equals then default
  public static int gte2d(int v, int gte, int d) {
    return v >= gte ? d : v;
  }

  //less than then default
  public static int lt2d(int v, int lt) {
    return lt2d(v, lt, lt);
  }

  //less than then default
  public static int lt2d(int v, int lt, int d) {
    return v < lt ? d : v;
  }

  //less than equals then default
  public static int lte2d(int v, int lte) {
    return lte2d(v, lte, lte);
  }

  //less than equals then default
  public static int lte2d(int v, int lte, int d) {
    return v <= lte ? d : v;
  }

  public static int null2Default(Integer i, int d) {
    return i == null ? d : i;
  }

  public static int null2Zero(Integer i) {
    return null2Default(i, 0);
  }
}
