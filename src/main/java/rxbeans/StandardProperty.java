package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;
import javax.annotation.Nonnull;

/**
 * Reference implementation of {@link Property}.
 * @param <T>
 */
public class StandardProperty<T> implements MutableProperty<T> {

  private final BehaviorProcessor<T> stream;
  private final T defaultValue;

  /**
   * Default constructor.
   */
  public StandardProperty(@Nonnull final T defaultValue) {
    this.defaultValue = defaultValue;
    stream = BehaviorProcessor.createDefault(defaultValue);
  }

  @Nonnull
  @Override
  public Flowable<T> getStream() {
    return stream;
  }

  @Override
  public synchronized void set(@Nonnull T value) {
    stream.onNext(value);
  }

  @Override
  @Nonnull
  public T get() {
    return stream.getValue();
  }

  @Override
  @Nonnull
  public T getDefault() {
    return defaultValue;
  }
}