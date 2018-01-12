package rxbeans.observable;

import javax.annotation.Nonnull;

public interface List<E> extends java.util.List<E>, Collection<E> {

  class ElementAddedEvent<T> extends Collection.ElementAddedEvent<T> {

    private final int index;

    public ElementAddedEvent(@Nonnull Collection<T> source, @Nonnull T element, int index) {
      super(source, element);
      this.index = index;
    }

    public int getIndex() {
      return index;
    }
  }
}