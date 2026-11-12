package apsd.interfaces.containers.iterators;

/** Interface: Iteratore arbitrario. */
public interface Iterator<Data> {

  /* ************************************************************************ */
  /* Specific member functions of Iterator                                    */
  /* ************************************************************************ */

  // IsValid
  boolean IsValid();
  // Reset
  void Reset();
  // GetCurrent
  Data GetCurrent();

}
