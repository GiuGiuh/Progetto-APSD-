package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Interface: IterableContainer con supporto all'iterazione mutabile. */
public interface MutableIterableContainer<T extends MutableIterableContainer<T, Data>, Data> extends IterableContainer<T, Data> {
  // Must extend IterableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Override specific member functions of IterableContainer                  */
  /* ************************************************************************ */

  // FIterator
  MutableForwardIterator<Data> MutableForwardIterator();
  // BIterator
  MutableBackwardIterator<Data> MutableBackwardIterator();

  //SONO OVERRIDE O DEVONO ESSERE DIVERSI?
  

}
