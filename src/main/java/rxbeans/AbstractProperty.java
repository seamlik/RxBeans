package rxbeans;

import java.util.Objects;

public abstract class AbstractProperty<T> implements ReadOnlyProperty<T>, WriteOnlyProperty<T> {

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
}