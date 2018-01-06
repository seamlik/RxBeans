package rxbeans;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.Objects;
import javax.annotation.Nonnull;

public abstract class AbstractProperty<T> implements MutableProperty<T> {

  private final T defaultValue;
  private T value;

  protected AbstractProperty(final T defaultValue) {
    this.value = defaultValue;
    this.defaultValue = defaultValue;
  }

  @Override
  public synchronized T get() {
    return value;
  }

  @Override
  public T getDefault() {
    return defaultValue;
  }

  @Override
  public synchronized void set(final T value) {
    this.value = value;
  }

  @Override
  public synchronized boolean change(final T value) {
    if (Objects.equals(value, this.value)) {
      return false;
    } else {
      set(value);
      return true;
    }
  }

  @Override
  public synchronized void getAndDo(@Nonnull final Consumer<T> function) throws Exception {
    function.accept(value);
  }

  @Override
  public synchronized <R> R getAndDo(@Nonnull final Function<T, R> function) throws Exception {
    return function.apply(value);
  }
}