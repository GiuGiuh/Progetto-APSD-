package apsd.interfaces.containers.base.ordered;

/** Interface: OrderedTraversableContainer con struttura dei dati ordinata. */
public interface SortedTraversableContainer<T extends OrderedTraversableContainer<T, Data>, Data extends Comparable<Data>> extends OrderedTraversableContainer<T, Data> {
  // Must extend OrderedTraversableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.
}
