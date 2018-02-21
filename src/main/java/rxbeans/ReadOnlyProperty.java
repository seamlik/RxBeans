package rxbeans;

import io.reactivex.Flowable;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.Nonnull;

public interface ReadOnlyProperty<T> extends Property {

  T get();

  T getDefault();

  @Nonnull
  Flowable getStream();

  default void getAndDo(@Nonnull final Consumer<T> function) throws Exception {
    synchronized (this) {
      function.accept(get());
    }
  }

  default <R> R getAndDo(@Nonnull final Function<T, R> function) throws Exception {
    synchronized (this) {
      return function.apply(get());
    }
  }
}