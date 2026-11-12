package apsd.interfaces.containers.base;

/** Interface: Container con supporto alla rimozione di un dato. */
public interface RemovableContainer<T extends RemovableContainer<T, Data>, Data> extends Container<T> {
  // Must extend Container.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of RemovableContainer                          */
  /* ************************************************************************ */

  // Remove
  boolean Remove(Data data);
  // AllRemoved
  default boolean AllRemoved(TraversableContainer<?,Data> con) {
    if( con == null || con.isEmpty()) return false; //se il contenuto è vuoto non c'è nulla da rimuovere, quindi ritorno true
    return con.FoldForward((elem, acc)-> Remove(elem) && acc, true);
  }
  // SomeRemoved
  default boolean SomeRemoved(TraversableContainer<?,Data> con) {
    if(con == null || con.isEmpty()) return false; //se il contenuto è vuoto non posso rimuovere nulla
    return con.FoldForward((elem, acc)-> Remove(elem) || acc, false);
  }

  //TODO: CHIEDERESI SE E' MEGLIO LA FOLD O LA TRAVERSE
}
