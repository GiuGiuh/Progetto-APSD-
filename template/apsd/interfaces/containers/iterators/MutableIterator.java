package apsd.interfaces.containers.iterators;

/** Interface: Iteratore mutabile. */
public interface MutableIterator<Data> extends Iterator<Data> {
  // Must extend Iterator.

  /* ************************************************************************ */
  /* Specific member functions of MutableIterator                             */
  /* ************************************************************************ */

  // SetCurrent
  void SetCurrent(Data data);

}
