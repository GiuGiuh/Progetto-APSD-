package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Accumulator;
import apsd.interfaces.traits.Predicate;

/** Interface: MembershipContainer con supporto all'attraversamento. */
public interface TraversableContainer<T extends TraversableContainer<T, Data>, Data> extends MembershipContainer<T, Data> {
  // Must extend MembershipContainer.
  // T must have a recursive bound.

  /* ************************************************************************ */
  /* Specific member functions of TraversableContainer                        */
  /* ************************************************************************ */

  // TraverseForward
  boolean TraverseForward(Predicate<Data> pred);

  // TraverseBackward
  boolean TraverseBackward(Predicate<Data> pred);

  // FoldForward
  default <Acc> Acc FoldForward(Accumulator<Data, Acc> fun, Acc ini) {
    Box<Acc> accBox = new Box<>(ini);
    TraverseForward(elem -> {
      accBox.Set(fun.Apply(elem, accBox.Get()));
      return false; // Continue traversing
    });
    return accBox.Get();
  }

  // FoldBackward
  default <Acc> Acc FoldBackward(Accumulator<Data, Acc> fun, Acc ini) {
    Box<Acc> accBox = new Box<>(ini);
    TraverseBackward(elem -> {
      accBox.Set(fun.Apply(elem, accBox.Get()));
      return false; // Continue traversing
    });
    return accBox.Get();
  }

  // Multiplicity
  default Natural Multiplicity(Data data) {
    return FoldForward((elem, acc) -> {
      if (elem == null ? data == null : elem.equals(data)) {
        return acc.Increment();
      }  
      return acc; 
    }, Natural.ZERO);
    }
  

  // IsEqual
  default boolean IsEqual(TraversableContainer<?, Data> Con) {
    if (Con == null) { return false; }
    if (!this.Size().equals(Con.Size())) { return false; }
    return !TraverseForward(dat -> !Multiplicity(dat).equals(Con.Multiplicity(dat)));
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
  @Override
  default Natural Size() {
    MutableNatural count = new MutableNatural(0);
    TraverseForward( elem -> {
      count.Increment();
      return false; // Continue traversing
    });
    return count.ToNatural();
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  // Exists
  @Override 
  default boolean Exists(Data data) {
    return TraverseForward(elem -> (elem == null ? data == null : elem.equals(data)));
  }

}
