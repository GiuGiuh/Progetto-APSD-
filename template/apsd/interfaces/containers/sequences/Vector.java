package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<T extends Vector<T, Data>, Data> extends ReallocableContainer<T>, MutableSequence<T, Data> {
  // Must extend ReallocableContainer and MutableSequence.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  // ShiftLeft
  default void ShiftLeft(Natural start, Natural end, Natural num) {
    if(start == null || end == null || num == null) return ;

    long size = Size().ToLong();
    long s = start.ToLong();
    long e = end.ToLong();
    long n = num.ToLong();

    if(s >= e || n == 0) return;

    for(long i = s; i < e; i++) {
      long dst = i - n;
      if(dst >=0) SetAt(GetAt(Natural.Of(i)), Natural.Of(dst));
    }
    for(long i = Math.max(s, e - n); i < e; i++) {
      SetAt(null, Natural.Of(i));
    }
  }

  default void ShiftLeft(Natural pos, Natural num) {
    if(pos == null) return;
    ShiftLeft(pos, Size(), num);
  }

  default void ShiftLeft(Natural pos) {
    ShiftLeft(pos, Natural.ONE);
  }
  // ShiftFirstLeft
  default void ShiftFirstLeft(Natural num) {
    if(num == null) return;
    ShiftLeft(Natural.ZERO, Size(), num);
  }
  // ShiftLastLeft
  default void ShiftLastLeft(Natural num) {
    if(num == null) return;
    ShiftLeft(Size().Decrement(), Size(), num);
  }
  // ShiftRight
  default void ShiftRight(Natural start, Natural end, Natural num) {
    if (start == null || end == null || num == null) return;
    long size = Size().ToLong();
    long s = start.ToLong();
    long e = Math.min(end.ToLong(), size);
    long n = num.ToLong();
    if (s >= e || n == 0) return;

    for (long i = e - 1; i >= s; i--) {       // decrescente
      long dst = i + n;
      if (dst < size) SetAt(GetAt(Natural.Of(i)), Natural.Of(dst));
    }
    for (long i = s; i < Math.min(s + n, e); i++) {  // slot rimasti liberi
      SetAt(null, Natural.Of(i));
    }
  }

  default void ShiftRight(Natural pos, Natural num) {
    if(pos == null) return;
    ShiftRight(pos, Size(), num);
  }

  default void ShiftRight(Natural pos) {
    ShiftRight(pos, Natural.ONE);
  }
  // ShiftFirstRight
  default void ShiftFirstRight(Natural num) {
    if(num == null) return;
    ShiftRight(Natural.ZERO, Size(), num);
  }
  // ShiftLastRight
  default void ShiftLastRight(Natural num) {
    if(num == null) return;
    ShiftRight(Size().Decrement(), Size(), num);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
  @Override 
  default Natural Size() {
    return Capacity();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // SubSequence
  @Override
  default T SubSequence(Natural start, Natural end) {
    if (start == null || end == null) return null;
    if (!IsInBound(start) || !IsInBound(end)) return null;
    if (start.compareTo(end) > 0) return null;

    long from = start.ToLong();
    long lenght = end.ToLong() - from + 1;

    T sub = Empty();
    sub.Realloc(Natural.Of(lenght));
    for(long i = 0; i < lenght; i++) {
      sub.SetAt(GetAt(Natural.Of(from + i)), Natural.Of(i));
    }
    return sub;
    
  }

  //TODO Ricontrollare questa interfaccia
}
