package apsd.interfaces.containers.collections.ordered;

import apsd.interfaces.containers.collections.Set;

public interface OrderedSet<T extends OrderedSet<T, Data>, Data extends Comparable<Data>> extends Set<T, Data>, OrderedCollection<T, Data> {
  // Must extend Set and OrderedCollection.
  // T must have a recursive bound.
  // Data must extend Comparable.
}
