package rxbeans.observable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

class CollectionWrapper<E> implements Collection<E> {

  private final java.util.Collection<E> collection;
  private final FlowableProcessor<ElementsChangedEvent> eventStream;

  public CollectionWrapper(@Nonnull final java.util.Collection<E> source) {
    collection = source;
    final FlowableProcessor<ElementsChangedEvent> unsafeStream = PublishProcessor.create();
    eventStream = unsafeStream.toSerialized();
  }

  @Nonnull
  @Override
  public Flowable<ElementsChangedEvent> getEventStream() {
    return eventStream;
  }

  @Override
  public int size() {
    return collection.size();
  }

  @Override
  public boolean isEmpty() {
    return collection.isEmpty();
  }

  @Override
  public boolean contains(final Object o) {
    return collection.contains(o);
  }

  @Override
  public Iterator<E> iterator() {
    return collection.iterator();
  }

  @Override
  public Object[] toArray() {
    return collection.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return collection.toArray(a);
  }

  @Override
  public boolean add(E element) {
    final boolean successful = collection.add(element);
    if (successful) {
      eventStream.onNext(new ElementAddedEvent<>(
          this,
          element
      ));
    }
    return successful;
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsAll(java.util.Collection<?> c) {
    return collection.containsAll(c);
  }

  @Override
  public boolean addAll(java.util.Collection<? extends E> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(java.util.Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(java.util.Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public synchronized void clear() {
    final List<ElementRemovedEvent<E>> events = Observable
        .fromIterable(collection)
        .map(it -> new ElementRemovedEvent<>(this, it))
        .toList()
        .blockingGet();
    collection.clear();
    for (ElementRemovedEvent<E> event : events) {
      eventStream.onNext(event);
    }
  }
}