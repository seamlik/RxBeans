package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import java.util.EventObject;
import javax.annotation.Nonnull;

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

  protected void fireEvent(@Nonnull final EventObject event) {
    eventStream.onNext(event);
  }

  @Override
  public Flowable<EventObject> getEventStream() {
    return eventStream;
  }
}