package org.shaneking.ling.zero.util;

import java.util.ArrayList;
import java.util.Collection;

public class FixedList<E> extends ArrayList<E> {
  public static final int DEFAULT_SIZE = 1300;
  private final int fixedSize;

  public FixedList() {
    this(DEFAULT_SIZE);
  }

  public FixedList(int fixedSize) {
    super();
    this.fixedSize = fixedSize;
  }

  public boolean add(E e) {
    if (this.size() > fixedSize) {
      this.subList(0, this.size() - fixedSize).clear();
    } else if (this.size() == fixedSize) {
      this.remove(0);
    }
    return super.add(e);
  }

  public void add(int index, E element) {
    if (this.size() > fixedSize) {
      this.subList(0, this.size() - fixedSize).clear();
    } else if (this.size() == fixedSize) {
      this.remove(0);
    }
    super.add(Math.min(index, this.size()), element);
  }

  public boolean addAll(Collection<? extends E> c) {
    int needRemoveSize = this.size() + c.size() - fixedSize;
    if (needRemoveSize > 0) {
      this.subList(0, Math.min(this.size(), needRemoveSize)).clear();
    }
    return super.addAll(c);
  }

  public boolean addAll(int index, Collection<? extends E> c) {
    int needRemoveSize = this.size() + c.size() - fixedSize;
    if (needRemoveSize > 0) {
      if (c.size() > fixedSize) {
        this.clear();
        return this.addAll(c);
      } else {
        this.subList(0, Math.min(this.size(), needRemoveSize)).clear();
        return super.addAll(Math.min(index, this.size()), c);
      }
    } else {
      return super.addAll(Math.min(index, this.size()), c);
    }
  }
}
