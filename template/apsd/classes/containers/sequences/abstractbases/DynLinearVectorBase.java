package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<T extends DynLinearVectorBase<T, Data>, Data> extends LinearVectorBase<T, Data> implements DynVector<T, Data> {
  // Must extend LinearVectorBase.
  // Must implement DynVector.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // protected long size = 0L;
  protected long size = 0L;
  // DynLinearVectorBase()
  public DynLinearVectorBase() {
    super();
  }
  // DynLinearVectorBase(Natural inicapacity)
  public DynLinearVectorBase(Natural inicapacity) {
    super(inicapacity);
  }
  // DynLinearVectorBase(TraversableContainer<?, Data> con)
  public DynLinearVectorBase(TraversableContainer<?, Data> con) {
    super(con);
  }

  /* ************************************************************************ */
  /* Specific member functions of DynLinearVectorBase                         */
  /* ************************************************************************ */

  // ArrayAlloc

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
  @Override
  public Natural Size() {
    return new Natural(size);
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
  @Override
  public void Clear() {
    super.Clear();
    size = 0L;
  }

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
