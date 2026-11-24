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
  void InsertAt(Data Dat, Natural pos);
  // InsertFirst
  default void InsertFirst(Data data) {
    InsertAt(data, Natural.ZERO);
  }
  // InsertLast
  default void InsertLast(Data data) {
    InsertAt(data, Natural.Of(Size()));
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
    if(start == null || end == null) return Empty();
    if(!IsInBound(start) || !IsInBound(end)) return Empty();
    if(start.compareTo(end) > 0) return Empty(); 

    T subSeq = Empty();
    
    var iterator = FIterator();
    long from = start.ToLong();
    long to = end.ToLong();

    iterator.Next(start);
    for(long i = from; i < to; i++) {
      subSeq.InsertLast(iterator.DataNNext());
      iterator.Next();
    }
  
    return subSeq;

  }
  //TODO Non sono pienamente convinto di questa implementazione.
}