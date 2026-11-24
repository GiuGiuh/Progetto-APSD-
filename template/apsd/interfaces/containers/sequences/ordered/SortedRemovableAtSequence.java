package apsd.interfaces.containers.sequences.ordered;

import apsd.classes.utilities.Natural;

/** Interface: RemovableAtSequence & OrderedSequence. */
public interface SortedRemovableAtSequence<T extends SortedRemovableAtSequence<T, Data>, Data extends Comparable<Data>> extends SortedSequence<T, Data>, OrderedRemovableAtSequence<T, Data> {
  // Must extend SortedSequence and OrderedRemovableAtSequence.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Override specific member functions from OrderedRemovableContainer        */
  /* ************************************************************************ */

  // MinNRemove
  @Override
  default Data MinNRemove() {
    if (IsEmpty()) return null;
    return FirstNRemove();
  }
  // MaxNRemove
  @Override
  default Data MaxNRemove() {
    if (IsEmpty()) return null;
    return LastNRemove();
  }
  // PredecessorNRemove
  @Override
  default Data PredecessorNRemove(Data data) {
    if (data == null) return null;
    Natural pos = SearchPredecessor(data);
    if (pos == null) return null;
    else { return AtNRemove(pos); }
  }
  // SuccessorNRemove
  @Override
  default Data SuccessorNRemove(Data data) {
    if (data == null) return null;
    Natural pos = SearchSuccessor(data);
    if (pos == null) return null;
    else { return AtNRemove(pos); }
  }

}
