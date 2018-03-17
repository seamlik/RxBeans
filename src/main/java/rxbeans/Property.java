package rxbeans;

import io.reactivex.Flowable;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Property without write access.
 */
@ThreadSafe
public interface Property<T> {

  /**
   * Gets the current value.
   */
  T get();

  /**
   * Gets the default value.
   */
  T getDefault();

  /**
   * Gets a stream of values emitted by this {@link Property}.
   */
  Flowable<T> getStream();

  /**
   * Atomically applies a function on the value of this {@link Property}.
   */
  default void getAndDo(final Consumer<T> function) {
    synchronized (this) {
      function.accept(get());
    }
  }

  /**
   * Atomically applies a function on the value of this {@link Property}.
   */
  default <X extends Exception> void
  getAndDoUnsafe(final Class<X> exType, final UnsafeConsumer<T> function) throws X {
    synchronized (this) {
      try {
        function.acceptUnsafe(get());
      } catch (RuntimeException ex) {
        throw ex;
      } catch (Exception ex) {
        if (exType.isInstance(ex)) {
          throw exType.cast(ex);
        } else {
          throw new RuntimeException(ex);
        }
      }
    }
  }

  /**
   * Atomically applies a function on the value of this {@link Property} and returns its result.
   */
  default <R> R getAndDo(final Function<T, R> function) {
    synchronized (this) {
      return function.apply(get());
    }
  }

  /**
   * Atomically applies a function on the value of this {@link Property}.
   */
  default <R, X extends Exception> R
  getAndDoUnsafe(final Class<X> exType, final UnsafeFunction<T, R> function) throws X {
    synchronized (this) {
      try {
        return function.applyUnsafe(get());
      } catch (RuntimeException ex) {
        throw ex;
      } catch (Exception ex) {
        if (exType.isInstance(ex)) {
          throw exType.cast(ex);
        } else {
          throw new RuntimeException(ex);
        }
      }
    }
  }
}