package rxbeans;

import java.lang.Object;
import java.util.Collection;
import javax.annotation.Nonnull;

public interface ModifyOnlyCollectionProperty<T> extends ReadOnlyProperty<Collection<T>> {

  /**
   * @see Collection#add(Object)
   */
  boolean add(T element);

  /**
   * @see Collection#addAll(Collection)
   */
  void addAll(@Nonnull Collection<T> elements);

  /**
   * @see Collection#clear()
   */
  void clear();
}