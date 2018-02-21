package rxbeans;

import io.reactivex.Flowable;
import java.util.EventObject;
import javax.annotation.Nonnull;

/**
 * Enhanced {@link java.lang.Object}.
 */
public interface Object {

  /**
   * Gets a stream of events.
   */
  @Nonnull
  Flowable<EventObject> getEventStream();
}