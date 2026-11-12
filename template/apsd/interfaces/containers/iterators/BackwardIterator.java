package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data> {
  // Must extend Iterator.

  /* ************************************************************************ */
  /* Specific member functions of BackwardIterator                            */
  /* ************************************************************************ */

  // Prev
  default void Prev() {
    if(IsValid()) {
      DataNPrev();
    }
  }

  default void Prev(Natural steps) {
    if(steps == null) return;
    for(long i=0; i < steps.ToLong() && IsValid(); i++) {
      DataNPrev();
    }
  }
  // DataNPrev
  Data DataNPrev();

  // ForEachBackward
  default boolean ForEachBackward(Predicate<Data> pred) {
    if (pred == null) return false;

    while(IsValid()){
      if(pred.Apply(DataNPrev())) {
        return true;
      }
    }
    return false;
  }

}
