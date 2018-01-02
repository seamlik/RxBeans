package rxbeans;

import io.reactivex.Flowable;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import javax.annotation.Nonnull;

public interface ReadOnlyCollectionProperty<T> extends ReadOnlyProperty<Collection<T>> {


  abstract class ElementsChangedEvent extends EventObject {

    /**
     * Default constructor.
     */
    protected ElementsChangedEvent(final ReadOnlyCollectionProperty source) {
      super(source);
    }
  }

  class ElementsAddedEvent<T> extends ElementsChangedEvent {

    private final Collection<T> elements;

    /**
     * Default constructor.
     * @param elements Elements being added.
     */
    public ElementsAddedEvent(final ReadOnlyCollectionProperty<T> source,
                              @Nonnull final Collection<T> elements) {
      super(source);
      this.elements = Collections.unmodifiableCollection(elements);
    }

    public Collection<T> getElement() {
      return elements;
    }
  }

  class ElementsRemovedEvent<T> extends ElementsChangedEvent {

    private final Collection<T> elements;

    public ElementsRemovedEvent(final ReadOnlyCollectionProperty<T> source,
                                @Nonnull final Collection<T> elements) {
      super(source);
      this.elements = Collections.unmodifiableCollection(elements);
    }

    public Collection<T> getElements() {
      return elements;
    }
  }

  @Nonnull
  Flowable<ElementsChangedEvent> getEventStream();
}