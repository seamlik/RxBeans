package rxbeans;

import java.util.Objects;

/**
 * {@link Property} with write access.
 */
public interface MutableProperty<T> extends Property<T> {

  /**
   * Sets a new value. Implementations are not required to enforce the
   * immutability of values being set here.
   */
  void set(T value);

  /**
   * Changes to a different value. If the new value equals to the current value, no event will be
   * fired from {@link Property#getStream()}.
   * @return {@code true} if the value has been changed, otherwise {@code false}.
   */
  default boolean change(final T value) {
    synchronized (this) {
      if (Objects.equals(get(), value)) {
        return false;
      } else {
        set(value);
        return true;
      }
    }
  }
}