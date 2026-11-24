package apsd.interfaces.containers.sequences.ordered;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ordered.SortedIterableContainer;

/** Interface: OrderedSequence & SortedIterableContainer. */
public interface SortedSequence<T extends SortedSequence<T, Data>, Data extends Comparable<Data>> extends OrderedSequence<T, Data>, SortedIterableContainer<T, Data> {
  // Must extend OrderedSequence and SortedIterableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  // Exists
  @Override 
  default boolean Exists(Data data){
    return Search(data) != null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedContainer                 */
  /* ************************************************************************ */

  // Min
  @Override
  default Data Min() { return GetFirst(); }
  // Max
  @Override
  default Data Max() { return GetLast(); }
  // Predecessor
  @Override
  default Data Predecessor(Data data) {
    Natural p = SearchPredecessor(data);
    return p == null ? null : GetAt(p);
  }
  // Successor
  @Override
  default Data Successor(Data data) {
    Natural p = SearchSuccessor(data);
    return p == null ? null : GetAt(p);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // Search
  @Override
  default Natural Search(Data dat) {
    if (dat == null) return null;
    var it = FIterator();
    Natural index = Natural.ZERO;
    while(it.IsValid()) {
      int cmp = it.GetCurrent().compareTo(dat);
      if(cmp == 0) return index;
      if(cmp > 0) return null;
      index = index.Increment();
      it.Next();
    }
    return null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSequence                  */
  /* ************************************************************************ */

  // SearchPredecessor
  @Override 
  default Natural SearchPredecessor(Data data) {
    if (data == null) return null;
    var it = FIterator();
    Natural index = Natural.ZERO;
    Natural pos = null;
    while(it.IsValid() && it.GetCurrent().compareTo(data) < 0) {
      pos = index;
      index = index.Increment();
      it.Next();
    }
    return pos;
   
  }
  // SearchSuccessor
  @Override 
  default Natural SearchSuccessor(Data data) {
    if (data == null) return null;
    var it = FIterator();
    Natural index = Natural.ZERO;
    while(it.IsValid()) {
      if(it.GetCurrent().compareTo(data) > 0) return index;
      index = index.Increment();
      it.Next();
    }
    return null;
  }
}
