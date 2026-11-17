package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.RemovableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

public interface Collection<T extends Collection<T, Data>, Data> extends ClearableContainer<T>, InsertableContainer<T, Data>, RemovableContainer<T, Data>, IterableContainer<T, Data> {
  // Must extend ClearableContainer, InsertableContainer, RemovableContainer, and IterableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Collection                                  */
  /* ************************************************************************ */

  // InsertIfAbsent
  default boolean InsertIfAbsent(Data dat) {
    if(dat == null) return false;
    if(Exists(dat)) return false;
    Insert(dat);
    return true;
  }
  // RemoveOccurrences
  // Filter
  default boolean 
  // Union
  // Difference
  // Intersection

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear

}
