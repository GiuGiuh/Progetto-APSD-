package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.TraversableContainer;

public interface Set<T extends Set<T, Data>, Data> extends Collection<T, Data> {
  // Must extend Collection.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Set                                         */
  /* ************************************************************************ */

  // IsEqual
  //TODO Chiedere questo discorso che è un po' strano. Se metto i ? non danno problemi.
  default boolean IsEqual(Set<?, Data> set){
    return IsEqual((TraversableContainer<?, Data>) set);
  }



  /* ************************************************************************ */
  /* Override specific member functions from TraversableContainer             */
  /* ************************************************************************ */

  // IsEqual
  @Override
  default boolean IsEqual(TraversableContainer<?, Data> con){
    if (con == null) return false;
    if(!Size().equals(con.Size())) return false;
    return !TraverseForward(x -> !con.Exists(x));
  }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // Intersection
  @Override 
  default void Intersection(Collection<?, Data> col) {
    if(col == null) return;
    Filter(x -> col.Exists(x));
  }

}
