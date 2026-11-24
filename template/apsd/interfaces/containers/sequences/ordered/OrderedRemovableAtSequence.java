package apsd.interfaces.containers.sequences.ordered;

import apsd.interfaces.containers.base.ordered.OrderedRemovableContainer;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

/** Interface: RemovableAtSequence & OrderedSequence. */
public interface OrderedRemovableAtSequence<T extends OrderedRemovableAtSequence<T, Data>, Data extends Comparable<Data>> extends OrderedSequence<T, Data>, RemovableAtSequence<T, Data>, OrderedRemovableContainer<T, Data> {
  // Must extend OrderedSequence, RemovableAtSequence, and OrderedRemovableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.
}
