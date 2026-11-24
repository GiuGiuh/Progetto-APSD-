package apsd.interfaces.containers.base.ordered;

/** Interface: SortedTraversableContainer & OrderedIterableContainer. */
public interface SortedIterableContainer<T extends SortedIterableContainer<T, Data>, Data extends Comparable<Data>> extends SortedTraversableContainer<T, Data>, OrderedIterableContainer<T, Data> {
  // Must extend SortedTraversableContainer and OrderedIterableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.
}
