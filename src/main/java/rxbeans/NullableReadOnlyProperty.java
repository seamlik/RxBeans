package rxbeans;

import io.reactivex.Flowable;
import java.util.Optional;
import javax.annotation.Nonnull;

public interface NullableReadOnlyProperty<T> extends ReadOnlyProperty<T> {

  @Override
  @Nonnull
  Flowable<Optional<T>> getStream();
}