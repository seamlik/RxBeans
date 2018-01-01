package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.processors.FlowableProcessor;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class ObjectProperty<T> implements MutableProperty<T> {

  private final FlowableProcessor<Optional<T>> stream;
  private final T defaultValue;
  private T value;

  public ObjectProperty() {
    value = null;
    defaultValue = null;

    final FlowableProcessor<Optional<T>> unsafeStream = BehaviorProcessor.createDefault(
        Optional.empty()
    );
    stream = unsafeStream.toSerialized();
  }

  public ObjectProperty(@Nonnull final T value) {
    this.value = value;
    defaultValue = value;

    final FlowableProcessor<Optional<T>> unsafeStream = BehaviorProcessor.createDefault(
        Optional.of(value)
    );
    stream = unsafeStream.toSerialized();
  }

  public ObjectProperty(@Nonnull final ReadOnlyProperty<T> property) {
    value = property.get();
    defaultValue = value;

    final FlowableProcessor<Optional<T>> unsafeStream = BehaviorProcessor.createDefault(
        Optional.of(value)
    );
    stream = unsafeStream.toSerialized();
  }

  @Override
  public synchronized T get() {
    return value;
  }

  @Override
  public T getDefault() {
    return defaultValue;
  }

  @Override
  public synchronized void set(final T value) {
    this.value = value;
    stream.onNext(Optional.ofNullable(value));
  }

  @Override
  @Nonnull
  public Flowable<Optional<T>> getStream() {
    return stream;
  }

  @Override
  public synchronized void getAndDo(@Nonnull final Consumer<T> function) throws Exception {
    function.accept(value);
  }

  @Override
  public synchronized <R> R getAndDo(@Nonnull final Function<T, R> function) throws Exception {
    return function.apply(value);
  }
}