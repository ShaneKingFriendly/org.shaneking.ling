package org.shaneking.ling.zero.util;

import lombok.NonNull;

import java.util.*;
import java.util.function.Supplier;

public class List0 {

  public static <E> ArrayList<E> concat(@NonNull Iterable<? extends E> iterable, E... elements) {
    ArrayList<E> rtn = List0.newArrayList();
    rtn.addAll(List0.newArrayList(iterable));
    rtn.addAll(List0.newArrayList(elements));
    return rtn;
  }

  //like java.util.Collections.nCopies
  public static <E> ArrayList<E> nCopies(int n, Supplier<E> s) {
    ArrayList<E> rtnList = List0.newArrayList();
    for (int i = 0; i < n; i++) {
      rtnList.add(s.get());
    }
    return rtnList;
  }

  public static <E> ArrayList<E> newArrayList() {
    return new ArrayList<E>();
  }

  public static <E> ArrayList<E> newArrayList(E... elements) {
    return new ArrayList<E>(Arrays.asList(elements));
  }

  public static <E> ArrayList<E> newArrayList(@NonNull Iterable<? extends E> iterable) {
    return iterable instanceof Collection ? new ArrayList<E>((Collection) iterable) : newArrayList(iterable.iterator());
  }

  public static <E> ArrayList<E> newArrayList(@NonNull Iterator<? extends E> iterator) {
    ArrayList<E> rtn = List0.newArrayList();
    while (iterator.hasNext()) {
      rtn.add(iterator.next());
    }
    return rtn;
  }

  public static <E> List<E> reverse(List<E> list) {
    Collections.reverse(list);
    return list;
  }
}
