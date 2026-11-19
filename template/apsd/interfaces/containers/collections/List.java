package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.sequences.InsertableAtSequence;
import apsd.interfaces.containers.sequences.MutableSequence;

public interface List<T extends List<T, Data>, Data> extends Chain<T, Data>, MutableSequence<T, Data>, InsertableAtSequence<T, Data> {
  // Must extend Chain, MutableSequence, and InsertableAtSequence.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // Insert
  @Override
  default boolean Insert(Data dat) {
    return InsertableAtSequence.super.Insert(dat);
  }

}
