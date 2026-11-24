package apsd.interfaces.containers.collections.ordered;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.ordered.SortedRemovableAtSequence;

public interface SortedChain<T extends SortedChain<T, Data>, Data extends Comparable<Data>> extends OrderedChain<T, Data>, SortedRemovableAtSequence<T, Data> {
  // Must extend OrderedChain and SortedRemovableAtSequence.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Specific member functions of SortedChain                                 */
  /* ************************************************************************ */

  // Intersection
  default void Intersection(SortedChain<?, Data> chn) {
    if (chn == null || chn == this) return;

    var Iterator1 = FIterator();
    var Iterator2 = chn.FIterator();
    long pos = 0;

    while(Iterator1.IsValid()) {
      Data cur = Iterator1.GetCurrent();
      
      while(Iterator2.IsValid() && Iterator2.GetCurrent().compareTo(cur) < 0) {
        Iterator2.Next();
      }

      if(Iterator2.IsValid() && Iterator2.GetCurrent().compareTo(cur) == 0) {
        Iterator1.Next();
        Iterator2.Next();
        pos++;
      } else if (!Iterator2.IsValid()){
        while(Size().ToLong() > pos) {
          RemoveLast();
        }
      } else {
        AtNRemove(new Natural(pos));
        Iterator1 = FIterator();
        Iterator1.Next(Natural.Of(pos));
      }
    }
  }


  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // Search
  @Override
  default Natural Search(Data dat) {
    return SortedRemovableAtSequence.super.Search(dat);
  }

}
