package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.RemovableContainer;

/** Interface: Sequence con supporto alla rimozione di un dato tramite posizione. */
public interface RemovableAtSequence<T extends RemovableAtSequence<T, Data>, Data> extends Sequence<T, Data>, RemovableContainer<T, Data> {
  // Must extend Sequence and RemovableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of RemovableAtSequence                         */
  /* ************************************************************************ */

  // RemoveAt
  default void RemoveAt(Natural pos) {
    AtNRemove(pos);
  }
  // AtNRemove
  Data AtNRemove(Natural pos);
   
  // RemoveFirst
  default void RemoveFirst() {
    RemoveAt(Natural.ZERO);
  }
  // FirstNRemove
  default Data FirstNRemove() {
    return AtNRemove(Natural.ZERO);
  }
  // RemoveLast
  default void RemoveLast() {
    RemoveAt(Natural.Of(Size()).Decrement());
  }
  // LastNRemove
  default Data LastNRemove() {
    return AtNRemove(Natural.Of(Size()).Decrement());
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // Remove
  @Override
  default boolean Remove(Data data) {
    if(data == null) return false;
    Natural pos = Search(data);
    if(!IsInBound(pos)) return false;
    RemoveAt(pos);
    return true;  
  }

  //TODO, Capire se RemoveAt va effettivamente implementato in quel modo o boh di nuovo per l'assenza di forwardIterator, teoricamente dovrebbe essere possibile implementarlo con un forward iterator ma per qualche motivo nonostante venga ereditato da Sequence non viene visto. 
}
