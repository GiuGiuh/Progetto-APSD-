package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<T extends Chain<T, Data>, Data> extends Collection<T, Data>, RemovableAtSequence<T, Data> {
  // Must extend Collection and RemovableAtSequence.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // Remove
  @Override
  default boolean Remove(Data dat) {
    if (dat == null) return false;
    Natural pos = Search(dat);
    if (pos == null || !IsInBound(pos)) return false;

    AtNRemove(pos);
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // Search
  @Override
  default Natural Search(Data dat) {
    var it = FIterator();
    Natural index = Natural.ZERO;
    while(it.IsValid()) {
      Data cur = it.GetCurrent();
      if (dat == null ? cur == null : dat.equals(cur)) return index;
      index = index.Increment();
      it.Next();
    }
    return null;
  }
}
