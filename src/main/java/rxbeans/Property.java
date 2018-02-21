package rxbeans;

import io.reactivex.Flowable;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Property without write access.
 */
@ThreadSafe
public interface Property<T> {

  /**
   * Gets the current value.
   */
  @Nonnull
  T get();

  /**
   * Gets the default value.
   */
  @Nonnull
  T getDefault();

  /**
   * Gets a stream of values emitted by this {@link Property}.
   */
  @Nonnull
  Flowable<T> getStream();

  /**
   * Atomically applies a function on the value of this {@link Property}.
   */
  default void getAndDo(@Nonnull final Consumer<T> function) {
    synchronized (this) {
      function.accept(get());
    }
  }

  /**
   * Atomically applies a function on the value of this {@link Property} and returns its result.
   */
  default <R> R getAndDo(@Nonnull final Function<T, R> function) {
    synchronized (this) {
      return function.apply(get());
    }
  }
}