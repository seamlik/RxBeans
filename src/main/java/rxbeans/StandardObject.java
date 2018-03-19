package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import java.util.EventObject;

/**
 * Reference implementation of {@link Object}.
 */
public class StandardObject implements Object {

  private final FlowableProcessor<EventObject> eventStream;

  /**
   * Default constructor.
   */
  public StandardObject() {
    final FlowableProcessor<EventObject> unsafeStream = PublishProcessor.create();
    eventStream = unsafeStream.toSerialized();
  }

  protected final void triggerEvent(final EventObject event) {
    eventStream.onNext(event);
  }

  @Override
  public final Flowable<EventObject> getEventStream() {
    return eventStream;
  }
}