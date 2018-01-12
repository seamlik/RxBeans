package rxbeans;

import io.reactivex.Flowable;
import javax.annotation.Nonnull;
import rxbeans.observable.Collection;

public interface ReadOnlyCollectionProperty<T> extends ReadOnlyProperty<java.util.Collection> {


  @Nonnull
  Flowable<Collection.ElementsChangedEvent> getEventStream();
}