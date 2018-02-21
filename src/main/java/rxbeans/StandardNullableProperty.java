package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.processors.FlowableProcessor;
import java.util.Optional;
import javax.annotation.Nonnull;

public class StandardNullableProperty<T>
    extends AbstractProperty<T>
    implements NullableReadOnlyProperty<T> {

  private final FlowableProcessor<Optional<T>> stream;

  public StandardNullableProperty(final T defaultValue) {
    super(defaultValue);
    final FlowableProcessor<Optional<T>> unsafeStream = BehaviorProcessor.createDefault(
        Optional.ofNullable(defaultValue)
    );
    stream = unsafeStream.toSerialized();
  }

  @Override
  @Nonnull
  public Flowable<Optional<T>> getStream() {
    return stream;
  }

  @Override
  public synchronized void set(T value) {
    super.set(value);
    stream.onNext(Optional.ofNullable(value));
  }
}