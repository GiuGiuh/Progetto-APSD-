package apsd.interfaces.containers.base.ordered;

import apsd.interfaces.containers.base.RemovableContainer;

/** Interface: OrderedContainer & RemovableContainer. */
public interface OrderedRemovableContainer<T extends OrderedRemovableContainer<T, Data>, Data extends Comparable<Data>> extends OrderedContainer<T, Data>, RemovableContainer<T, Data> {
  // Must extend OrderedContainer and RemovableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Specific member functions of OrderedRemovableContainer                   */
  /* ************************************************************************ */

  // RemoveMin
  default void RemoveMin() {
    MinNRemove();
  }
  // MinNRemove
  default Data MinNRemove() {
    Data min = Min();
    if(min != null) Remove(min);
    return min;
  }
  // RemoveMax
  default void RemoveMax() {
    MaxNRemove();
  }
  // MaxNRemove
  default Data MaxNRemove() {
    Data max = Max();
    if(max != null) Remove(max);
    return max;
  }
  // RemovePredecessor
  default void RemovePredecessor(Data data) {
    PredecessorNRemove(data);
  }
  // PredecessorNRemove
  default Data PredecessorNRemove(Data data) {
    Data pred = Predecessor(data);
    if(pred != null) Remove(pred);
    return pred;
  }
  // RemoveSuccessor
  default void RemoveSuccessor(Data data) {
    SuccessorNRemove(data);
  }
  // SuccessorNRemove
  default Data SuccessorNRemove(Data data) {
    Data succ = Successor(data);
    if(succ != null) Remove(succ);
    return succ;
  }

}
