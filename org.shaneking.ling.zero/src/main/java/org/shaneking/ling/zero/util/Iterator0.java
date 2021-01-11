package org.shaneking.ling.zero.util;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.Boolean0;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Iterator0 {
  public static <T> T[] toArray(Iterator<? extends T> iterator, Class<T> type) {
    List<T> list = List0.newArrayList(iterator);
    return Iterable0.toArray(list, type);
  }

  public static <T> T get(@NonNull Iterator<T> iterator, int position) {
    Boolean0.checkArgument(position >= 0, "position (" + position + ") must not be negative");
    int skipped = advance(iterator, position);
    if (!iterator.hasNext()) {
      throw new IndexOutOfBoundsException("position (" + position + ") must be less than the number of elements that remained (" + skipped + ")");
    }
    return iterator.next();
  }

  public static int advance(@NonNull Iterator<?> iterator, int numberToAdvance) {
    Boolean0.checkArgument(numberToAdvance >= 0, "numberToAdvance must be nonnegative");

    int i;
    for (i = 0; i < numberToAdvance && iterator.hasNext(); i++) {
      iterator.next();
    }
    return i;
  }

  public static boolean elementsEqual(Iterator<?> iterator1, Iterator<?> iterator2) {
    while (iterator1.hasNext()) {
      if (!iterator2.hasNext()) {
        return false;
      }
      Object o1 = iterator1.next();
      Object o2 = iterator2.next();
      if (!Objects.equals(o1, o2)) {
        return false;
      }
    }
    return !iterator2.hasNext();
  }
}
