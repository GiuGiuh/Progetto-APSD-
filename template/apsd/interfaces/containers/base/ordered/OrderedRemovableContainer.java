package apsd.interfaces.containers.base.ordered;

import apsd.interfaces.containers.base.RemovableContainer;

/** Interface: OrderedContainer & RemovableContainer. */
public interface OrderedRemovableContainer<T extends OrderedContainer<T, Data>, Data extends Comparable<Data>> extends OrderedContainer<T, Data>, RemovableContainer<T, Data> {
  // Must extend OrderedContainer and RemovableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Specific member functions of OrderedRemovableContainer                   */
  /* ************************************************************************ */

  // RemoveMin
  default void RemoveMin() {
    Data min = Min();
    Remove(min);
  }
  // MinNRemove
  default Data MinNRemove() {
    Data min = Min();
    Remove(min);
    return min;
  }
  // RemoveMax
  default void RemoveMax() {
    Data max = Max();
    Remove(max);
  }
  // MaxNRemove
  default Data MaxNRemove() {
    Data max = Max();
    Remove(max);
    return max;
  }
  // RemovePredecessor
  default void RemovePredecessor(Data data) {
    Data pred = Predecessor(data);
    Remove(pred);
  }
  // PredecessorNRemove
  default Data PredecessorNRemove(Data data) {
    Data pred = Predecessor(data);
    Remove(pred);
    return pred;
  }
  // RemoveSuccessor
  default void RemoveSuccessor(Data data) {
    Data succ = Successor(data);
    Remove(succ);
  }
  // SuccessorNRemove
  default Data SuccessorNRemove(Data data) {
    Data succ = Successor(data);
    Remove(succ);
    return succ;
  }

}
