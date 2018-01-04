package rxbeans;

import io.reactivex.Flowable;
import javax.annotation.Nonnull;

public interface NonnullReadOnlyProperty<T> extends ReadOnlyProperty<T> {

  @Override
  @Nonnull
  Flowable<T> getStream();
}