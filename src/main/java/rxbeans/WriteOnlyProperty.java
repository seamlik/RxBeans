package rxbeans;

public interface WriteOnlyProperty<T> extends Property {

  void set(final T value);
}