package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Interface: TraversableContainer con supporto all'iterazione. */
public interface IterableContainer<T extends IterableContainer<T, Data>, Data> extends TraversableContainer<T, Data> {
  // Must extend TraversableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of IterableContainer                           */
  /* ************************************************************************ */

  // FIterator
  ForwardIterator<Data> FIterator();
  // BIterator
  BackwardIterator<Data> BIterator();

  /* ************************************************************************ */
  /* Override specific member functions from TraversableContainer             */
  /* ************************************************************************ */

  // TraverseForward
  @Override
  default boolean TraverseForward(Predicate<Data> fun) {
    ForwardIterator<Data> it = FIterator();
    return it.ForEachForward(fun);
  }
  // TraverseBackward
  @Override
  default boolean TraverseBackward(Predicate<Data> fun) {
    BackwardIterator<Data> it = BIterator();
    return it.ForEachBackward(fun);
  }

}
