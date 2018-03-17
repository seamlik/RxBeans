package rxbeans;

import java.util.function.Function;

@FunctionalInterface
public interface UnsafeFunction<T, R> extends Function<T, R> {

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