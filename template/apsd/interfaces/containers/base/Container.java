package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;

/** Interface: Container, la base di tutti i contenitori. */
public interface Container<T extends Container<T>> {
  // T must have a recursive bound.


  /* ************************************************************************ */
  /* Specific member functions of Container                                   */
  /* ************************************************************************ */

  // Empty
  T Empty();
  // Size
  Natural Size();
  // IsEmpty
  default boolean isEmpty() { return Size().IsZero(); }

}