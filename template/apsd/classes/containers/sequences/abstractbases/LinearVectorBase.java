package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) linear vector base implementation. */
abstract public class LinearVectorBase<T extends LinearVectorBase<T, Data>, Data> extends VectorBase<T, Data> {
  // Must extend VectorBase.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // LinearVectorBase()
  public LinearVectorBase() {
    super();
  }
  // LinearVectorBase(Natural inisize)
  public LinearVectorBase(Natural inisize) {
    super(inisize);
  }
  // LinearVectorBase(TraversableContainer<?, Data> con)
  public LinearVectorBase(TraversableContainer<?, Data> con) {
    super(con);
  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // Realloc
  @Override 
  public void Realloc(Natural newCap){
    Data[] old = arr;
    ArrayAlloc(newCap);
    if (old != null) {
      for (int i = 0; i < old.length && i < arr.length; i++) {
        arr[i] = old[i];
      }
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // GetAt
  @Override
  public Data GetAt(Natural pos) {
    return arr[(int) ExcIfOutOfBound(pos)];
  }
  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // SetAt
  @Override
  public void SetAt(Data dat, Natural pos) {
    arr[(int) ExcIfOutOfBound(pos)] = dat;
  }

}
