package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Reallocable;

/** Interface: ClearableContainer che è anche Reallocable. */
public interface ReallocableContainer<T extends ReallocableContainer<T>> extends ClearableContainer<T>, Reallocable {
  // Must extend ClearableContainer and Reallocable.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of ReallocableContainer                        */
  /* ************************************************************************ */

  double GROW_FACTOR = 2.0; // Must be strictly greater than 1.
  double SHRINK_FACTOR = 2.0; // Must be strictly greater than 1.

  // Capacity
  Natural Capacity ();
  // Grow
  default void Grow () { 
    Realloc(Natural.Of( (Capacity().ToLong() + 1L) * (long) GROW_FACTOR ));
  }
  // Grow
  default void Grow (Natural dim) {
    Realloc(Natural.Of((Capacity().ToLong() + dim.ToLong()) * (long) GROW_FACTOR));
  }
  // Shrink
  default void Shrink () {
    Realloc(Natural.Of(  (Capacity().ToLong() / (long) SHRINK_FACTOR)));
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
  @Override
  default Natural Size () {
    return Capacity();
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
  @Override
  default void Clear () { 
    Realloc(Natural.ZERO);
  }
}
