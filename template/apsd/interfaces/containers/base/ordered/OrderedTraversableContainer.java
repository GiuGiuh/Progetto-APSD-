package apsd.interfaces.containers.base.ordered;

import apsd.interfaces.containers.base.TraversableContainer;

/** Interface: OrderedContainer & TraversableContainer. */
public interface OrderedTraversableContainer<T extends OrderedContainer<T, Data>, Data extends Comparable<Data>> extends OrderedContainer<T, Data>, TraversableContainer<T, Data> {
  // Must extend OrderedContainer and TraversableContainer.
  // T must have a recursive bound.
  // Data must extend Comparable.

  /* ************************************************************************ */
  /* Specific member functions of OrderedTraversableContainer                 */
  /* ************************************************************************ */

  // Min
  @Override 
  default Data Min() {
    return this.FoldForward((data, acc) -> (acc == null || data.compareTo(acc) < 0) ? data : acc, (Data) null);
  } 
  // Max
  @Override
  default Data Max() {
    return this.FoldForward((data, acc) -> (acc == null || data.compareTo(acc) > 0) ? data : acc, (Data) null);
  }
  // Predecessor
  @Override
  default Data Predecessor(Data data) {
    if(data == null) return null;
    return FoldForward((x, acc) ->
      (x.compareTo(data) < 0 && (acc == null || x.compareTo(acc) > 0)) ? x : acc, (Data) null);
  }
  // Successor
  @Override
  default Data Successor(Data data) {
    if(data == null) return null;
    return FoldForward((x, acc) ->
      (x.compareTo(data) > 0 && (acc == null || x.compareTo(acc) < 0)) ? x : acc, (Data) null);
  }

}
