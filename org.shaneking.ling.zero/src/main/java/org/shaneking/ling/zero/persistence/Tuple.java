package org.shaneking.ling.zero.persistence;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.util.Iterable0;
import org.shaneking.ling.zero.util.Iterator0;
import org.shaneking.ling.zero.util.List0;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Quadruple&lt;Integer, Integer, Integer, Integer&gt; ip = Tuple.of(127, 0, 0, 1);
 * System.out.println(Tuple.joinWith(".").join(ip));	//127.0.0.1
 */
public class Tuple<First, Rest> implements Iterable<Object> {
  public static final String BEGIN = String0.OPEN_BRACKET;
  public static final String END = String0.CLOSE_BRACKET;
  public static final String SEP = String0.COMMA;

  First first;
  Rest rest;

  private Tuple(First first, Rest rest) {
    this.first = first;
    this.rest = rest;
  }

  public static Null of() {
    return new Null();
  }

  public static TupleJoiner joinWith(String sep) {
    return new TupleJoiner(BEGIN, sep, END);
  }

  public static TupleJoiner joinWith(String begin, String sep, String end) {
    return new TupleJoiner(begin, sep, end);
  }

  public static <T1, Rest> T1 getFirst(Tuple<T1, Rest> tuple) {
    return tuple.first;
  }

  public static <T1, T2, Rest> T2 getSecond(Tuple<T1, Tuple<T2, Rest>> tuple) {
    return tuple.rest.first;
  }

  public static <T1, T2, T3, Rest> T3 getThird(Tuple<T1, Tuple<T2, Tuple<T3, Rest>>> tuple) {
    return tuple.rest.rest.first;
  }

  public static <T1, T2, T3, T4, Rest> T4 getFourth(Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Rest>>>> tuple) {
    return tuple.rest.rest.rest.first;
  }

  public static <T1, T2, T3, T4, T5, Rest> T5 getFifth(Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, Rest>>>>> tuple) {
    return tuple.rest.rest.rest.rest.first;
  }

  public static <T1, T2, T3, T4, T5, T6, Rest> T6 getSixth(Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, Tuple<T6, Rest>>>>>> tuple) {
    return tuple.rest.rest.rest.rest.rest.first;
  }

  public static <T1, T2, T3, T4, T5, T6, T7, Rest> T7 getSeventh(Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, Tuple<T6, Tuple<T7, Rest>>>>>>> tuple) {
    return tuple.rest.rest.rest.rest.rest.rest.first;
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, Rest> T8 getEighth(Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, Tuple<T6, Tuple<T7, Tuple<T8, Rest>>>>>>>> tuple) {
    return tuple.rest.rest.rest.rest.rest.rest.rest.first;
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, Rest> T9 getNinth(Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, Tuple<T6, Tuple<T7, Tuple<T8, Tuple<T9, Rest>>>>>>>>> tuple) {
    return tuple.rest.rest.rest.rest.rest.rest.rest.rest.first;
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Rest> T10 getTenth(Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, Tuple<T6, Tuple<T7, Tuple<T8, Tuple<T9, Tuple<T10, Rest>>>>>>>>>> tuple) {
    return tuple.rest.rest.rest.rest.rest.rest.rest.rest.rest.first;
  }

  @SuppressWarnings("unchecked")
  public static <T> T getN(Tuple<?, ?> tuple, int n) {
    return (T) Iterable0.get(tuple, n);
  }

  public static <T1> Single<T1> of(T1 t1) {
    return new Single<>(t1);
  }

  @NonNull
  @Override
  public Iterator<Object> iterator() {
    return List0.newArrayList(first, rest).iterator();
  }

  public static <T1, T2> Pair<T1, T2> of(T1 t1, T2 t2) {
    return new Pair<>(t1, t2);
  }

  public static <T1, T2, T3> Triple<T1, T2, T3> of(T1 t1, T2 t2, T3 t3) {
    return new Triple<>(t1, t2, t3);
  }

  public static <T1, T2, T3, T4> Quadruple<T1, T2, T3, T4> of(T1 t1, T2 t2, T3 t3, T4 t4) {
    return new Quadruple<>(t1, t2, t3, t4);
  }

  public static <T1, T2, T3, T4, T5> Quintuple<T1, T2, T3, T4, T5> of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
    return new Quintuple<>(t1, t2, t3, t4, t5);
  }

  public static <T1, T2, T3, T4, T5, T6> Sextuple<T1, T2, T3, T4, T5, T6> of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
    return new Sextuple<>(t1, t2, t3, t4, t5, t6);
  }

  public static <T1, T2, T3, T4, T5, T6, T7> Septuple<T1, T2, T3, T4, T5, T6, T7> of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
    return new Septuple<>(t1, t2, t3, t4, t5, t6, t7);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8> Octuple<T1, T2, T3, T4, T5, T6, T7, T8> of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
    return new Octuple<>(t1, t2, t3, t4, t5, t6, t7, t8);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Nonuple<T1, T2, T3, T4, T5, T6, T7, T8, T9> of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
    return new Nonuple<>(t1, t2, t3, t4, t5, t6, t7, t8, t9);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Decuple<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10) {
    return new Decuple<>(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> DecuplePlus<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, Object... rest) {
    return new DecuplePlus<>(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, rest);
  }

  @Override
  public boolean equals(Object obj) {
    return obj != null && (this == obj || ((Tuple.class.isAssignableFrom(obj.getClass())) && Iterable0.elementsEqual(this, (Tuple<?, ?>) obj)));
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(Iterator0.toArray(iterator(), Object.class));
  }

  public <T> Tuple<T, Tuple<First, Rest>> prepend(T t) {
    return new Tuple<>(t, this);
  }

  @Override
  public String toString() {
    return joinWith(BEGIN, SEP, END).join(this);
  }

  public String toString(String sep) {
    return toString(joinWith(sep));
  }

  public String toString(String begin, String sep, String end) {
    return toString(joinWith(begin, sep, end));
  }

  public String toString(TupleJoiner joiner) {
    return joiner.join(this);
  }

  private enum End {
    SINGLETON
  }

  public static class DecuplePlus<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> extends Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, Tuple<T6, Tuple<T7, Tuple<T8, Tuple<T9, Tuple<T10, Tuple<Object[], End>>>>>>>>>>> {
    DecuplePlus(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, Object[] rest) {
      super(t1, of(t2, t3, t4, t5, t6, t7, t8, t9, t10, rest));
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
      return Stream.concat(List0.newArrayList(first
        , rest.first
        , rest.rest.first
        , rest.rest.rest.first
        , rest.rest.rest.rest.first
        , rest.rest.rest.rest.rest.first
        , rest.rest.rest.rest.rest.rest.first
        , rest.rest.rest.rest.rest.rest.rest.first
        , rest.rest.rest.rest.rest.rest.rest.rest.first
        , rest.rest.rest.rest.rest.rest.rest.rest.rest.first).stream()
        , List0.newArrayList(rest.rest.rest.rest.rest.rest.rest.rest.rest.rest.first).stream()).iterator();
    }
  }

  public static class Decuple<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> extends Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, Tuple<T6, Tuple<T7, Tuple<T8, Tuple<T9, Tuple<T10, End>>>>>>>>>> {
    Decuple(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10) {
      super(t1, of(t2, t3, t4, t5, t6, t7, t8, t9, t10));
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
      return List0.newArrayList(first, rest.first, rest.rest.first, rest.rest.rest.first, rest.rest.rest.rest.first, rest.rest.rest.rest.rest.first, rest.rest.rest.rest.rest.rest.first, rest.rest.rest.rest.rest.rest.rest.first, rest.rest.rest.rest.rest.rest.rest.rest.first, rest.rest.rest.rest.rest.rest.rest.rest.rest.first).iterator();
    }
  }

  public static class Nonuple<T1, T2, T3, T4, T5, T6, T7, T8, T9> extends Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, Tuple<T6, Tuple<T7, Tuple<T8, Tuple<T9, End>>>>>>>>> {
    Nonuple(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
      super(t1, of(t2, t3, t4, t5, t6, t7, t8, t9));
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
      return List0.newArrayList(first, rest.first, rest.rest.first, rest.rest.rest.first, rest.rest.rest.rest.first, rest.rest.rest.rest.rest.first, rest.rest.rest.rest.rest.rest.first, rest.rest.rest.rest.rest.rest.rest.first, rest.rest.rest.rest.rest.rest.rest.rest.first).iterator();
    }
  }

  public static class Octuple<T1, T2, T3, T4, T5, T6, T7, T8> extends Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, Tuple<T6, Tuple<T7, Tuple<T8, End>>>>>>>> {
    Octuple(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
      super(t1, of(t2, t3, t4, t5, t6, t7, t8));
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
      return List0.newArrayList(first, rest.first, rest.rest.first, rest.rest.rest.first, rest.rest.rest.rest.first, rest.rest.rest.rest.rest.first, rest.rest.rest.rest.rest.rest.first, rest.rest.rest.rest.rest.rest.rest.first).iterator();
    }
  }

  public static class Septuple<T1, T2, T3, T4, T5, T6, T7> extends Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, Tuple<T6, Tuple<T7, End>>>>>>> {
    Septuple(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
      super(t1, of(t2, t3, t4, t5, t6, t7));
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
      return List0.newArrayList(first, rest.first, rest.rest.first, rest.rest.rest.first, rest.rest.rest.rest.first, rest.rest.rest.rest.rest.first, rest.rest.rest.rest.rest.rest.first).iterator();
    }
  }

  public static class Sextuple<T1, T2, T3, T4, T5, T6> extends Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, Tuple<T6, End>>>>>> {
    Sextuple(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
      super(t1, of(t2, t3, t4, t5, t6));
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
      return List0.newArrayList(first, rest.first, rest.rest.first, rest.rest.rest.first, rest.rest.rest.rest.first, rest.rest.rest.rest.rest.first).iterator();
    }
  }

  public static class Quintuple<T1, T2, T3, T4, T5> extends Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, Tuple<T5, End>>>>> {
    Quintuple(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
      super(t1, of(t2, t3, t4, t5));
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
      return List0.newArrayList(first, rest.first, rest.rest.first, rest.rest.rest.first, rest.rest.rest.rest.first).iterator();
    }
  }

  public static class Quadruple<T1, T2, T3, T4> extends Tuple<T1, Tuple<T2, Tuple<T3, Tuple<T4, End>>>> {
    Quadruple(T1 t1, T2 t2, T3 t3, T4 t4) {
      super(t1, of(t2, t3, t4));
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
      return List0.newArrayList(first, rest.first, rest.rest.first, rest.rest.rest.first).iterator();
    }
  }

  public static class Triple<T1, T2, T3> extends Tuple<T1, Tuple<T2, Tuple<T3, End>>> {
    Triple(T1 t1, T2 t2, T3 t3) {
      super(t1, of(t2, t3));
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
      return List0.newArrayList(first, rest.first, rest.rest.first).iterator();
    }
  }

  public static class Pair<T1, T2> extends Tuple<T1, Tuple<T2, End>> {
    Pair(T1 t1, T2 t2) {
      super(t1, of(t2));
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
      return List0.newArrayList(first, rest.first).iterator();
    }
  }

  public static class Single<T1> extends Tuple<T1, End> {
    Single(T1 t1) {
      super(t1, End.SINGLETON);
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
      return List0.newArrayList((Object) first).iterator();
    }
  }

  public static class Null extends Tuple<End, End> {
    private Null() {
      super(End.SINGLETON, End.SINGLETON);
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
      return Collections.emptyIterator();
    }
  }

  public static class TupleJoiner {
    private final String close;
    private final String open;
    private final String separator;

    public TupleJoiner(String open, String separator, String close) {
      this.open = open;
      this.separator = separator;
      this.close = close;
    }

    public StringBuffer appendTo(Tuple<?, ?> tuple) {
      return appendTo(new StringBuffer(), tuple);
    }

    public StringBuffer appendTo(@NonNull StringBuffer appender, @NonNull Tuple<?, ?> tuple) {
      appender.append(open);
      Iterator<Object> iterTerm = tuple.iterator();
      if (iterTerm.hasNext()) {
        appender.append(toString(iterTerm.next()));
        while (iterTerm.hasNext()) {
          appender.append(separator);
          appender.append(toString(iterTerm.next()));
        }
      }
      appender.append(close);

      return appender;
    }

    public String join(Tuple<?, ?> tuple) {
      return String.valueOf(appendTo(tuple));
    }

    private CharSequence toString(Object term) {
      return (term instanceof CharSequence) ? (CharSequence) term : String.valueOf(term);
    }
  }
}
