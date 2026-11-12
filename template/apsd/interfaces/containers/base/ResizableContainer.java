package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;
/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer<T extends ResizableContainer<T>> extends ReallocableContainer<T> {
  // T must have a recursive bound.
  // Must extend ReallocableContainer.

  /* ************************************************************************ */
  /* Specific member functions of ResizableContainer                          */
  /* ************************************************************************ */

  double THRESHOLD_FACTOR = 2.0; // Must be strictly greater than 1.

  // Expand
  default void Expand(){
    Expand(Natural.ONE);
  }

  void Expand(Natural newCapacity);

  // Reduce
  default void Reduce(){
    Reduce(Natural.ONE);
  }

  void Reduce(Natural newCapacity);
  

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  abstract Natural Size ();

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // Grow
  @Override
  default void Grow (Natural newCapacity) {
    Expand(newCapacity);
  }
  // Shrink
  @Override
  default void Shrink () {
    Reduce();
  }

//TODO: CAPIRE COME UTILIZZARE TRESHOLD_FACTOR E AGGIUSTARE LE IMPLEMENTAZIONI
}
