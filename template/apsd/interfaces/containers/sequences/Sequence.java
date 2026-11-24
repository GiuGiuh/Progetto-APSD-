package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<T extends Sequence<T, Data>, Data> extends IterableContainer<T, Data> {
  // Must extend IterableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Sequence                                    */
  /* ************************************************************************ */

  // GetAt
  default Data GetAt(Natural index) {
  ExcIfOutOfBound(index);
  Box<Data> result = new Box<>();
  Box<Natural> current = new Box<>(Natural.ZERO);
  TraverseForward(data -> {
    if (current.Get().equals(index)) {
      result.Set(data);
      return true;
    }
    current.Set(current.Get().Increment());
    return false;
  });
  return result.Get();
}
  // GetFirst
  // Magari Fare GetCurrent con ForwardIterator
  default Data GetFirst() {
    return GetAt(Natural.ZERO);
  }
  // GetLast
  // Magari fare col BackwardIterator
  default Data GetLast() {
    if (IsEmpty()) throw new IndexOutOfBoundsException("Cannot get last element of an empty sequence.");
    return GetAt(Natural.Of(Size()).Decrement());
  }
  // Search
  default Natural Search(Data data) {
    Box<Natural> index = new Box<>(Natural.ZERO);
    TraverseForward(elem -> {
      if (elem == null ? data == null : elem.equals(data)) {
        return true;
      }
      index.Set(index.Get().Increment());
      return false;
    });
    return IsInBound(index.Get()) ? index.Get() : null;
  }
  // IsInBound
  default boolean IsInBound(Natural index) {
    return index != null && index.compareTo(Size()) < 0;
  }
  // ExcIfOutOfBound
  default long ExcIfOutOfBound(Natural index) throws IndexOutOfBoundsException {
    if(!IsInBound(index)) {
      throw new IndexOutOfBoundsException("Index " + index.ToLong() + " is out of bounds for this sequence of size " + Size() );
    }
      return index.ToLong();
  }
  // IsEqual
  default boolean IsEqual(Sequence<?, Data> seq){
    if(seq == null) return false;
    if(Size().equals(seq.Size())) return false;

    ForwardIterator<Data> it1 = FIterator();
    ForwardIterator<Data> it2 = seq.FIterator();

    while(it1.IsValid() && it2.IsValid()){
      if(!it1.GetCurrent().equals(it2.GetCurrent())){
        return false;
      }
      it1.Next();
      it2.Next();
    }
    return true;
  }
  // SubSequence
  T SubSequence(Natural start, Natural end);

}
