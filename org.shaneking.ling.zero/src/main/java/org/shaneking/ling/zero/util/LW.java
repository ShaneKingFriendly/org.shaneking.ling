package org.shaneking.ling.zero.util;

import java.util.List;

public class LW<E> {
  private final List<E> list = List0.newArrayList();

  public LW<E> add(E e) {
    if (e != null) {
      list.add(e);
    }
    return this;
  }

  public LW<E> addAll(List<E> l) {
    if (l != null) {
      list.addAll(l);
    }
    return this;
  }

  public List<E> list() {
    return list;
  }

  public static <E> LW<E> wrap(E e) {
    LW<E> lw = new LW<>();
    if (e != null) {
      lw.list.add(e);
    }
    return lw;
  }

  public static <E> LW<E> wrap(List<E> l) {
    LW<E> lw = new LW<>();
    if (l != null) {
      lw.list.addAll(l);
    }
    return lw;
  }
}
