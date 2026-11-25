package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<T extends CircularVectorBase<T, Data>, Data> extends VectorBase<T, Data> {
  // Must extend VectorBase.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // protected long start = 0L;
  protected long start = 0L;
  // CircularVectorBase()
  public CircularVectorBase() {
    super();
  }
  // CircularVectorBase(Natural inisize)
  public CircularVectorBase(Natural inisize) {
    super(inisize);
  }
  // CircularVectorBase(TraversableContainer<?, Data> con)
  public CircularVectorBase(TraversableContainer<?, Data> con) {
    super(con);
  }

  /* ************************************************************************ */
  /* Specific member functions of CircularVectorBase                          */
  /* ************************************************************************ */

  // ArrayAlloc
  @Override 
  protected void ArrayAlloc(Natural newCap) {
    super.ArrayAlloc(newCap);
    start = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // Realloc
  @Override
  public void Realloc(Natural newcap) {
    Data[] old = arr;
    long oldstart = start;
    ArrayAlloc(newcap);
    if(old != null && old.length > 0) {
      long n = Math.min(old.length, arr.length);
      for (long i = 0; i < n; i++) {
        arr[(int) i] = old[(int) ((oldstart + i) % old.length)];
      }
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // GetAt
  @Override
  public Data GetAt(Natural pos) {
    long p = ExcIfOutOfBound(pos);
    return arr[(int) ((start + p) % arr.length)];
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // SetAt
  @Override
  public void SetAt(Data dat, Natural pos) {
    long p = ExcIfOutOfBound(pos);
    arr[(int) ((start + p) % arr.length)] = dat;
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  // ShiftLeft
  @Override
  public void ShiftLeft(Natural n) {
  // ShiftRight

}
