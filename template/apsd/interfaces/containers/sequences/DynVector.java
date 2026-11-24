package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<T extends DynVector<T, Data>, Data> extends ResizableContainer<T>, InsertableAtSequence<T, Data>, RemovableAtSequence<T, Data>, Vector<T, Data> {
  // Must extend ResizableContainer, InsertableAtSequence, RemovableAtSequence, and Vector.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  // InsertAt
  @Override 
  default void InsertAt(Data dat, Natural pos) {
    if(dat == null || pos == null) return;
    if(pos.ToLong() > Size().ToLong()) throw new IndexOutOfBoundsException("Position is out of bounds.");

    Expand(Natural.ONE);
    ShiftRight(pos);
    SetAt(dat, pos);
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // AtNRemove
  @Override 
  default Data AtNRemove(Natural pos) {
    ExcIfOutOfBound(pos);
    Data dat = GetAt(pos);
    ShiftLeft(pos.Increment());
    SetAt(null, Size().Decrement());
    Reduce(Natural.ONE);
    return dat;
  }


  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
  @Override 
  abstract Natural Size();

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // SubSequence
  @Override 
  default T SubSequence(Natural start, Natural end) {
    if (start == null || end == null) return Empty();
    if (!IsInBound(start) || !IsInBound(end)) return Empty();
    if (start.compareTo(end) > 0) return Empty();

    long from = start.ToLong();
    long len = end.ToLong() - from + 1;

    T sub = Empty();

    sub.Expand(Natural.Of(len));
    for (long i = 0; i < len; i++) {
      sub.SetAt(GetAt(Natural.Of(from + i)), Natural.Of(i));
    }
    return sub;
  }
//TODO Controllare poi questa SubSequence, dovrebbe essere un richiamo a Vector ma ha bisogno di controlli.
}
