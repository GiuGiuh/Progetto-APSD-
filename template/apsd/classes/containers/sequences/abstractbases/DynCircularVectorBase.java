package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic circular vector base implementation. */
abstract public class DynCircularVectorBase<T extends DynCircularVectorBase<T, Data>, Data> extends CircularVectorBase<T, Data> implements DynVector<T, Data> {
  // Must extend CircularVectorBase.
  // Must implement DynVector.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // protected long size = 0L;
  protected long size = 0L;
  // DynCircularVectorBase()
  DynCircularVectorBase() {
    super();
  }
  // DynCircularVectorBase(Natural inicapacity)
  DynCircularVectorBase(Natural inicapacity) {
    super(inicapacity);
  }
  // DynCircularVectorBase(TraversableContainer<?, Data> con)
  DynCircularVectorBase(TraversableContainer<?, Data> con) {
    super(con);
  }

  /* ************************************************************************ */
  /* Specific member functions of DynCircularVectorBase                       */
  /* ************************************************************************ */

  // ArrayAlloc

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // Realloc

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // Expand
  // Reduce

}
