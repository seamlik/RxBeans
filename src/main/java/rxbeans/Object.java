package rxbeans;

import io.reactivex.Flowable;
import java.util.EventObject;

/**
 * Enhanced {@link java.lang.Object}.
 */
public interface Object {

  /**
   * Gets a stream of events.
   */
  Flowable<EventObject> getEventStream();
}