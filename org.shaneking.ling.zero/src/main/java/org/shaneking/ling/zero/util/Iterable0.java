package org.shaneking.ling.zero.util;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.reflect.Array0;

import java.util.Collection;
import java.util.List;

public class Iterable0 {
  public static <T> T[] toArray(Iterable<? extends T> iterable, Class<T> type) {
    return toArray(iterable, Array0.newArray(type, 0));
  }

  static <T> T[] toArray(Iterable<? extends T> iterable, T[] array) {
    return castOrCopyToCollection(iterable).toArray(array);
  }

  private static <E> Collection<E> castOrCopyToCollection(Iterable<E> iterable) {
    return (iterable instanceof Collection) ? (Collection<E>) iterable : List0.newArrayList(iterable.iterator());
  }

  public static <T> T get(@NonNull Iterable<T> iterable, int position) {
    return (iterable instanceof List) ? ((List<T>) iterable).get(position) : Iterator0.get(iterable.iterator(), position);
  }

  public static boolean elementsEqual(Iterable<?> iterable1, Iterable<?> iterable2) {
    if (iterable1 instanceof Collection && iterable2 instanceof Collection) {
      Collection<?> collection1 = (Collection<?>) iterable1;
      Collection<?> collection2 = (Collection<?>) iterable2;
      if (collection1.size() != collection2.size()) {
        return false;
      }
    }
    return Iterator0.elementsEqual(iterable1.iterator(), iterable2.iterator());
  }
}
