package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.Vector;

/** Object: Abstract vector base implementation. */
abstract public class VectorBase<T extends VectorBase<T, Data>, Data> implements Vector<T, Data> {
  // Must implement Vector.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Fields and constructors                                                  */
  /* ************************************************************************ */

  // protected Data[] arr = null;
  protected Data[] arr = null;
  // VectorBase()
  protected VectorBase() {
    // Default constructor.
  }
  // VectorBase(Natural inisize)
  protected VectorBase(Natural inisize) {
    ArrayAlloc(inisize);
  }
  // VectorBase(TraversableContainer<?, Data> con)
  protected VectorBase(TraversableContainer<?, Data> con) {
    ArrayAlloc(con.Size());
    MutableNatural i = new MutableNatural(0);
    con.TraverseForward(dat -> {
      arr[(int) i.ToNatural().ToLong()] = dat;
      i.Increment();
      return false;
    });
  }

  /* ************************************************************************ */
  /* Specific member functions of VectorBase                                  */
  /* ************************************************************************ */

  // ArrayAlloc
  protected void ArrayAlloc(Natural size) {
    arr = (Data[]) new Object[(int) size.ToLong()];
  }


  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
  @Override
  public void Clear() {
    ArrayAlloc(Natural.ZERO);
  }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // Capacity
  @Override
  public Natural Capacity() {
    return arr == null ? Natural.ZERO : Natural.Of(arr.length);
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // VectorFIterator
  protected class VectorFIterator implements MutableForwardIterator<Data> {
    protected long cur = 0;
    @Override public boolean IsValid() { return cur < Capacity().ToLong(); }
    @Override public void Reset() { cur = 0; }
    @Override public Data GetCurrent() { return GetAt(Natural.Of(cur)); }
    @Override public void SetCurrent(Data dat) { SetAt(dat, Natural.Of(cur)); }
    @Override public void Next() { cur++; }
    @Override public Data DataNNext() { 
      Data dat = GetCurrent();
      Next();
      return dat;
    }
  }
  // FIterator
  @Override 
  public MutableForwardIterator<Data> FIterator() {
    return new VectorFIterator();
  }
  // VectorBIterator
  protected class VectorBIterator implements MutableBackwardIterator<Data> {
    protected long cur = Size().ToLong() - 1;
    @Override public boolean IsValid() { return cur >= 0 && cur < Size().ToLong(); }
    @Override public void Reset() { cur = Size().ToLong() - 1; }
    @Override public Data GetCurrent() { return GetAt(Natural.Of(cur)); }
    @Override public void SetCurrent(Data dat) { SetAt(dat, Natural.Of(cur)); }
    @Override public void Prev() { cur--; }
    @Override public Data DataNPrev() { 
      Data dat = GetCurrent();
      Prev();
      return dat;
    }
  }
  // BIterator
  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return new VectorBIterator();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // GetAt
  @Override
  public abstract Data GetAt(Natural pos);

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // SetAt
  @Override
  public abstract void SetAt(Data dat, Natural pos);

}
