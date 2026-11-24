package apsd.interfaces.containers.sequences.ordered;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ordered.OrderedIterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.sequences.Sequence;

/** Interface: Sequence & OrderedIterableContainer. */
public interface OrderedSequence<T extends OrderedSequence<T, Data>, Data extends Comparable<Data>> extends Sequence<T, Data>, OrderedIterableContainer<T, Data> {
  // Must extend Sequence and OrderedIterableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Specific member functions of OrderedSequence                             */
  /* ************************************************************************ */

  // SearchPredecessor
  default Natural SearchPredecessor(Data data) {
    if (data == null) return null;
    ForwardIterator<Data> it = FIterator();
    Natural index = Natural.ZERO;
    Natural pos = null;
    Data best = null;
    while(it.IsValid()) {
      Data cur = it.GetCurrent();
      if(cur.compareTo(data) < 0 && (best == null || cur.compareTo(best) > 0)) {
        best = cur;
        pos = index;
      }
      index = index.Increment();
      it.Next();
    }
    return pos;
  }
  // SearchSuccessor
  default Natural SearchSuccessor(Data data) {
    if (data == null) return null;
    ForwardIterator<Data> it = FIterator();
    Natural index = Natural.ZERO;
    Natural pos = null;
    Data best = null;
    while(it.IsValid()) {
      Data cur = it.GetCurrent();
      if(cur.compareTo(data) > 0 && (best == null || cur.compareTo(best) < 0)) {
        best = cur;
        pos = index;
      }
      index = index.Increment();
      it.Next();
    }
    return pos;
  }

}
