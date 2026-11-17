package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.InsertableContainer;


/** Interface: Sequence con supporto all'inserimento di un dato tramite posizione. */
public interface InsertableAtSequence<T extends InsertableAtSequence<T, Data>, Data> extends Sequence<T, Data>, InsertableContainer<T, Data> {
  // Must extend Sequence and InsertableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of InsertableAtSequence                        */
  /* ************************************************************************ */

  // InsertAt
  void InsertAt(Natural index, Data data);
  // InsertFirst
  default void InsertFirst(Data data) {
    InsertAt(Natural.ZERO, data);
  }
  // InsertLast
  default void InsertLast(Data data) {
    InsertAt(Natural.Of(Size()), data);
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // Insert
  @Override
  default boolean Insert(Data data) {
    if(data == null) return false;
    InsertLast(data);
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // SubSequence
  @Override
  default T SubSequence(Natural start, Natural end) {
    if(start == null || end == null) return null;
    if(!IsInBound(start) || !IsInBound(end)) return null;
    if(start.compareTo(end) > 0) return null; 

    T subSeq = Empty();
    for(long i = start.ToLong(); i < end.ToLong(); i++){
      subSeq.InsertLast(GetAt(Natural.Of(i)));
    }
  
    return subSeq;

  }
  //TODO Capire come fare SubSequence, teoricamente con un forward iterator ma per qualche motivo nonostante venga ereditato da Sequence non viene visto.
  //Posso definirlo chiamandolo con una variabile qualunque? sembra molto brutto da fare così tbh
  //oppure come ho fatto così
}