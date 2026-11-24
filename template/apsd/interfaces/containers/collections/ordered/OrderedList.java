package apsd.interfaces.containers.collections.ordered;

import apsd.interfaces.containers.collections.List;

public interface OrderedList<T extends OrderedList<T, Data>, Data extends Comparable<Data>> extends List<T, Data>, OrderedChain<T, Data> {
  // Must extend List and OrderedChain.
  // T must have a recursive bound.
  // Data must extend Comparable.
}
