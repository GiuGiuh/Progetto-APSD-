package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<T extends Stack<T, Data>, Data> extends ClearableContainer<T>, InsertableContainer<T, Data> {
  // Must extend ClearableContainer and InsertableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Stack                                       */
  /* ************************************************************************ */

  // Top
  Data Top();
  // Pop
  void Pop();
  // TopNPop
  default Data TopNPop(){
    Data data = Top();
    Pop();
    return data;
  }
  // SwapTop
  default void SwapTop(Data data)
  {
    Pop();
    Push(data);
  }
  // TopNSwap
  default Data TopNSwap(Data data)
  {
    Data top = Top();
    SwapTop(data);
    return top;
  }
  // Push
  void Push(Data data);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
  @Override 
  default void Clear(){
    while(!isEmpty())
      Pop();
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // Insert
  @Override
  default boolean Insert(Data data){
    if(data == null) return false;
    Push(data);
    return true;
  }

}
