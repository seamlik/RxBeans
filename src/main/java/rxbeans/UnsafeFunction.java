package rxbeans;

@FunctionalInterface
public interface UnsafeFunction<T, R> extends java.util.function.Function<T, R> {

  R applyUnsafe(T o) throws Exception;

  @Override
  default R apply(T value) {
    try {
      return applyUnsafe(value);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}