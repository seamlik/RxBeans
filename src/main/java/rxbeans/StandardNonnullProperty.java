package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.processors.FlowableProcessor;
import javax.annotation.Nonnull;

public class StandardNonnullProperty<T>
    extends AbstractProperty<T>
    implements NonnullReadOnlyProperty<T> {

  private final FlowableProcessor<T> stream;

  public StandardNonnullProperty(@Nonnull final T defaultValue) {
    super(defaultValue);
    final FlowableProcessor<T> unsafeStream = BehaviorProcessor.createDefault(
        defaultValue
    );
    stream = unsafeStream.toSerialized();
  }

  @Nonnull
  @Override
  public Flowable<T> getStream() {
    return stream;
  }

  @Override
  public synchronized void set(@Nonnull T value) {
    super.set(value);
    stream.onNext(value);
  }
}