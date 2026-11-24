package apsd.interfaces.containers.collections.ordered;

import apsd.interfaces.containers.base.ordered.OrderedIterableContainer;
import apsd.interfaces.containers.base.ordered.OrderedRemovableContainer;
import apsd.interfaces.containers.collections.Collection;

public interface OrderedCollection<T extends OrderedCollection<T, Data>, Data extends Comparable<Data>> extends Collection<T, Data>, OrderedRemovableContainer<T, Data>, OrderedIterableContainer<T, Data> {
  // Must extend Collection, OrderedRemovableContainer, and OrderedIterableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.
}
