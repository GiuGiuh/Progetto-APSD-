package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<T extends MutableSequence<T, Data>, Data> extends Sequence<T, Data>, MutableIterableContainer<T, Data> {
  // Must extend Sequence and MutableIterableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of MutableSequence                             */
  /* ************************************************************************ */

  // SetAt
  default void SetAt(Data newdat, Natural pos) {
    if(pos == null || newdat == null) return;
    if(!IsInBound(pos)) return;
    MutableForwardIterator<Data> iterator = FIterator();
    Natural currentpos = Natural.ZERO;
    while(iterator.IsValid() && currentpos.compareTo(pos) < 0){
      currentpos = currentpos.Increment();
      iterator.Next();
    }
    if(iterator.IsValid()){
      iterator.SetCurrent(newdat);
    }
  }
  // GetNSetAt
  default Data GetNSetAt(Data newdat, Natural pos) {
    if(pos == null || newdat == null) return null;
    if(!IsInBound(pos)) return null;
    MutableForwardIterator<Data> iterator = FIterator();
    Natural currentpos = Natural.ZERO;
    while(iterator.IsValid() && currentpos.compareTo(pos) < 0){
      currentpos = currentpos.Increment();
      iterator.Next();
    }
    if(iterator.IsValid()){
      Data oldData = iterator.GetCurrent();
      iterator.SetCurrent(newdat);
      return oldData;
    }
    return null;
   }
  // SetFirst
  default void SetFirst(Data newdat) {
    SetAt(newdat, Natural.ZERO);
  }
  // GetNSetFirst
  default Data GetNSetFirst(Data newdat) {
    return GetNSetAt(newdat, Natural.ZERO);
  }
  // SetLast
  default void SetLast(Data newdat) {
    SetAt(newdat, Natural.Of(Size()).Decrement());
  }
  // GetNSetLast
  default Data GetNSetLast(Data newdat) {
    return GetNSetAt(newdat, Natural.Of(Size()).Decrement());
  }
  // Swap
  default void Swap(Natural fst, Natural snd) {
    Data fstData = GetAt(fst);
    SetAt(GetAt(snd), fst);
    SetAt(fstData, snd);
  }

}
