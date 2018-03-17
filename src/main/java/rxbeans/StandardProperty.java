package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Reference implementation of {@link Property}.
 * @param <T>
 */
@ThreadSafe
public class StandardProperty<T> implements MutableProperty<T> {

  private final BehaviorProcessor<T> stream;
  private final T defaultValue;

  /**
   * Default constructor.
   */
  public StandardProperty(final T defaultValue) {
    this.defaultValue = defaultValue;
    stream = BehaviorProcessor.createDefault(defaultValue);
  }

  @Override
  public Flowable<T> getStream() {
    return stream;
  }

  @Override
  public synchronized void set(T value) {
    stream.onNext(value);
  }

  @Override
  public T get() {
    return stream.getValue();
  }

  @Override
  public T getDefault() {
    return defaultValue;
  }
}