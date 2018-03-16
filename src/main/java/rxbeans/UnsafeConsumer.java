package rxbeans;

@FunctionalInterface
public interface UnsafeConsumer<T> extends java.util.function.Consumer<T> {

  void acceptUnsafe(T o) throws Exception;

  @Override
  default void accept(T value) {
    try {
      acceptUnsafe(value);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}