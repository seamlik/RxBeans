package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.processors.FlowableProcessor;
import javax.annotation.Nonnull;

public class StandardProperty<T> extends AbstractProperty<T> {

  private final FlowableProcessor<T> stream;

  public StandardProperty(@Nonnull final T defaultValue) {
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