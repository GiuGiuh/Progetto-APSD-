package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore in avanti. */
public interface ForwardIterator<Data> extends Iterator<Data> {
  // Must extend Iterator.

  /* ************************************************************************ */
  /* Specific member functions of ForwardIterator                             */
  /* ************************************************************************ */

  // Next
  default void Next() {
    if(IsValid()) {
      DataNNext();
    }
  }

  default void Next(Natural steps) {
    if(steps == null) return;
    for(long i=0; i < steps.ToLong() && IsValid(); i++) {
      DataNNext();
    }
  }

  // DataNNext
  Data DataNNext();

  // ForEachForward
  default boolean ForEachForward(Predicate<Data> pred) {
    if (pred == null) return false;

    while(IsValid()){
      if(pred.Apply(DataNNext())) {
        return true;
      }
    }
    return false;
  }


}
