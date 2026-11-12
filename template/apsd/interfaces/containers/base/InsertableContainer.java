package apsd.interfaces.containers.base;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<T extends InsertableContainer<T, Dat>, Dat> extends Container<T> {
  // Must extend Container.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of InsertableContainer                         */
  /* ************************************************************************ */

  // Insert
  boolean Insert (Dat data);
  // AllInserted
  default boolean AllInserted(TraversableContainer<?,Dat> con) {
    if( con == null || con.isEmpty()) return false; //se il contenuto è vuoto non c'è nulla da inserire, quindi ritorno true
    return con.FoldForward((elem, acc)-> Insert(elem) && acc, true);
  }

  // SomeInserted
  default boolean SomeInserted(TraversableContainer<?,Dat> con) {
    if(con == null || con.isEmpty()) return false; //se il contenuto è vuoto non posso inserire nulla
    return con.FoldForward((elem, acc)-> Insert(elem) || acc, false);
  }

    //TODO: CHIEDERESI SE E' MEGLIO LA FOLD O LA TRAVERSE
}
