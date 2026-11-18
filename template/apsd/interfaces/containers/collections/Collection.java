package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.RemovableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

public interface Collection<T extends Collection<T, Data>, Data> extends ClearableContainer<T>, InsertableContainer<T, Data>, RemovableContainer<T, Data>, IterableContainer<T, Data> {
  // Must extend ClearableContainer, InsertableContainer, RemovableContainer, and IterableContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of Collection                                  */
  /* ************************************************************************ */

  // InsertIfAbsent
  default boolean InsertIfAbsent(Data dat) {
    if(dat == null) return false;
    if(Exists(dat)) return false;
    Insert(dat);
    return true;
  }
  // RemoveOccurrences
  default void RemoveOccurrences(Data dat) {
    if(dat == null) return;
    Filter(x -> !dat.equals(x));
  }
  // Filter
  default boolean Filter(Predicate<Data> fun) {
    if (fun == null) return false;

    boolean removed = false;
    ForwardIterator<Data> it = ForwardIterator();

    while( it.IsValid()) {
      Data dat = it.GetCurrent();
      if(!fun.Apply(dat) && Remove(dat)) {
        removed = true;
        it = ForwardIterator();  // la rimozione può invalidare l'iteratore: si riparte
      } else {
        it.Next();
      }
    }
    return removed;
  }
  // Union
  default void Union(Collection col) {
    if(col == null) return;
    ForwardIterator<Data> it = col.ForwardIterator();
    while(it.IsValid()) {
      Data dat = it.GetCurrent();
      InsertIfAbsent(dat);
      it.Next();
    }    
  }
  // Difference
  default void Difference(Collection col) {
    if(col == null) return;
    ForwardIterator<Data> it = col.ForwardIterator();
    while(it.IsValid()) {
      Data dat = it.GetCurrent();
      RemoveOccurrences(dat);
      it.Next();
    }    
  }
  // Intersection
  default void Intersection(Collection col) {
    if(col == null) return;
    ForwardIterator<Data> it = ForwardIterator();
    while(it.IsValid()) {
      Data dat = it.GetCurrent();
      if(!col.Exists(dat)) {
        RemoveOccurrences(dat);
        it = ForwardIterator();  // la rimozione può invalidare l'iteratore: si riparte
      } else {
        it.Next();
      }
    }    
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // Clear
  @Override
  default void Clear() {
    Filter(x -> false);
  }


  //TODO: Rivedere questa interfaccia, soprattutto il discorso delle rimozione che possono invalidare l'iteratore
}
