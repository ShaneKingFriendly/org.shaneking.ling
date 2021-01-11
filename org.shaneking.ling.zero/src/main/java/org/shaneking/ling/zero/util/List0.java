package org.shaneking.ling.zero.util;

import lombok.NonNull;

import java.util.*;

public class List0 {
  public static <E> ArrayList<E> newArrayList() {
    return new ArrayList<E>();
  }

  public static <E> ArrayList<E> newArrayList(@NonNull E... elements) {
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

  public static <E> List<E> reverse(@NonNull List<E> list) {
    Collections.reverse(list);
    return list;
  }
}
