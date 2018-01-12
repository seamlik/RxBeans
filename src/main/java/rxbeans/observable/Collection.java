package rxbeans.observable;

import io.reactivex.Flowable;
import java.util.EventObject;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface Collection<E> extends java.util.Collection<E> {

  abstract class ElementsChangedEvent extends EventObject {

    /**
     * Default constructor.
     */
    protected ElementsChangedEvent(@Nonnull final Collection source) {
      super(source);
    }
  }

  class ElementAddedEvent<T> extends ElementsChangedEvent {

    private final T element;

    /**
     * Default constructor.
     * @param element Element being added.
     */
    public ElementAddedEvent(@Nonnull final Collection<T> source,
                             @Nonnull final T element) {
      super(source);
      this.element = element;
    }

    public T getElement() {
      return element;
    }
  }

  class ElementRemovedEvent<T> extends ElementsChangedEvent {

    private final T elements;

    public ElementRemovedEvent(@Nonnull final Collection<T> source,
                               @Nonnull final T element) {
      super(source);
      this.elements = element;
    }

    public T getElement() {
      return elements;
    }
  }

  @Nonnull
  static <T> Collection<T> wrap(@Nonnull final java.util.Collection<T> collection) {
    return new CollectionWrapper<>(collection);
  }

  @Nonnull
  Flowable<ElementsChangedEvent> getEventStream();
}