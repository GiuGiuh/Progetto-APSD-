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
    if(dat == null || Exists(dat)) return false;
    return Insert(dat);
  }
  // RemoveOccurrences
  default void RemoveOccurrences(Data dat) {
    if (dat == null) return;
    Natural n = Multiplicity(dat);
    while(n.compareTo(Natural.ZERO) > 0) {
      Remove(dat);
      n = n.Decrement();
    }
  }
  // Filter
  default boolean Filter(Predicate<Data> fun) {
    if (fun == null) return false;

    boolean removed = false;
    ForwardIterator<Data> it = FIterator();

    while( it.IsValid()) {
      Data dat = it.GetCurrent();
      if(!fun.Apply(dat) && Remove(dat)) {
        removed = true;
        it = FIterator();  // la rimozione può invalidare l'iteratore: si riparte
      } else {
        it.Next();
      }
    }
    return removed;
  }
  // Union
  default void Union(Collection<?, Data> col) {
    if(col == null || col == this) return;
    ForwardIterator<Data> it = col.FIterator();
    while(it.IsValid()) {
      InsertIfAbsent(it.GetCurrent());
      it.Next();
    }    
  }
  // Difference
  default void Difference(Collection<?, Data> col) {
    if(col == null) return;
    if(col == this) {
      Clear();
      return;
    }
    Filter(x -> !col.Exists(x));
  }
  // Intersection
  default void Intersection(Collection<?, Data> col) {
    if(col == null || col == this) return;
    ForwardIterator<Data> it = FIterator();
    while(it.IsValid()) {
      Data dat = it.GetCurrent();
      if(Multiplicity(dat).compareTo(col.Multiplicity(dat)) > 0) {
        Remove(dat);
        it = FIterator(); 
      }
      else {
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


  //TODO: Rivedere questa interfaccia, soprattutto il discorso delle rimozione che possono invalidare l'iteratore e dell'intersection in collection
}
