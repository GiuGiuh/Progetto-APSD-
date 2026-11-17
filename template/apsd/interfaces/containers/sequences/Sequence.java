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
  if (index == null || !IsInBound(index)) return null;

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
    return GetAt(Natural.Of(Size()).Decrement());
  }
  // Search
  default Natural Search(Data data) {
    ForwardIterator<Data> iterator = ForwardIterator();
    Natural index = Natural.ZERO;
    while(iterator.IsValid()){
      if(iterator.GetCurrent().equals(data)){
        return index;
      }
      index.Increment();
      iterator.Next();
    }
    return index;
  }
  // IsInBound
  default boolean IsInBound(Natural index) {
    return index != null && index.compareTo(Size()) < 0;
  }
  // ExcIfOutOfBound
  default long ExcIfOutOfBounds(Natural index) throws IndexOutOfBoundsException {
    if(!IsInBound(index)) {
      throw new IndexOutOfBoundsException("Index " + index.ToLong() + " is out of bounds for this sequence of size " + Size() );
    }
      return index.ToLong();
  }
  // IsEqual
  default boolean IsEqual(Sequence<T, Data> Seq){
    if(Seq == null) return false;
    if(Size() != Seq.Size()) return false;

    ForwardIterator<Data> it1 = ForwardIterator();
    ForwardIterator<Data> it2 = Seq.ForwardIterator();

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

  //TODO DOVE USARE BOX IN SEQUENCE? 
  //TODO CHIEDERE SE L'UTILIZZO DI BOX E' IN GETAT, MAGARI A QUALCHE COMPAGNO
}
