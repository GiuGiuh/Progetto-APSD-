package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Queue<T extends Queue<T, Data>, Data> extends ClearableContainer<T>, InsertableContainer<T, Data> {
  // Must extend ClearableContainer and InsertableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Queue                                       */
  /* ************************************************************************ */

  // Head
  Data Head();
  // Dequeue
  void Dequeue();
  // HeadNDequeue
  default Data HeadNDequeue()
  {
    Data data = Head();
    Dequeue();
    return data;
  }
  // Enqueue
  void Enqueue(Data data);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
  @Override 
  default void Clear(){
    while(!isEmpty())
      Dequeue();
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // Insert
  @Override 
  default boolean Insert(Data data){
    if(data == null) return false;
    Enqueue(data);
    return true;
  }

}
