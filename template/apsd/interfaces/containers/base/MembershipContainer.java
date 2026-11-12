package apsd.interfaces.containers.base;

/** Interface: Container con supporto alla verifica di appartenenza. */
public interface MembershipContainer<T extends MembershipContainer<T, Data>, Data> extends Container<T> {
  // Must extend Container.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of MembershipContainer                         */
  /* ************************************************************************ */

  // Exists
  boolean Exists (Data data);
}
