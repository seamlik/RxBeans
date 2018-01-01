package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface ReadOnlyProperty<T> extends Property {

  T get();

  T getDefault();

  @Nonnull
  Flowable<Optional<T>> getStream();

  void getAndDo(@Nonnull Consumer<T> function) throws Exception;

  <R> R getAndDo(@Nonnull Function<T, R> function) throws Exception;
}