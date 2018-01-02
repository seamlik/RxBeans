package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.annotation.Nonnull;

public class CollectionProperty<T> extends ObjectProperty<Collection<T>>
    implements ModifiableCollectionProperty<T> {

  private final FlowableProcessor<ReadOnlyCollectionProperty.ElementsChangedEvent> eventStream;
  private final Collection<T> defaultCollection;
  private Collection<T> collection;

  public CollectionProperty(@Nonnull final Collection<T> collection) {
    super(Collections.unmodifiableCollection(collection));

    this.collection = collection;
    defaultCollection = new ArrayList<>(collection);

    final FlowableProcessor<ReadOnlyCollectionProperty.ElementsChangedEvent> unsafeStream = PublishProcessor.create();
    eventStream = unsafeStream.toSerialized();
  }

  @Override
  public synchronized boolean add(final T element) {
    final boolean successful = collection.add(element);
    if (successful) {
      eventStream.onNext(new ReadOnlyCollectionProperty.ElementsAddedEvent<>(
          this,
          Collections.singleton(element)
      ));
    }
    return successful;
  }

  @Override
  public synchronized void addAll(@Nonnull final Collection<T> elements) {
    throw new UnsupportedOperationException();
  }

  @Override
  public synchronized void clear() {
    final ReadOnlyCollectionProperty.ElementsRemovedEvent<T> event = new ReadOnlyCollectionProperty.ElementsRemovedEvent<>(
        this,
        new ArrayList<>(collection)
    );
    collection.clear();
    eventStream.onNext(event);
  }

  @Override
  public Collection<T> getDefault() {
    return defaultCollection;
  }

  @Override
  @Nonnull
  public Flowable<ReadOnlyCollectionProperty.ElementsChangedEvent> getEventStream() {
    return eventStream;
  }
}