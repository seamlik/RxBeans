package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import java.util.EventObject;
import javax.annotation.Nonnull;

public class Object {

  private final FlowableProcessor<EventObject> eventStream;

  public Object() {
    final FlowableProcessor<EventObject> unsafeStream = PublishProcessor.create();
    eventStream = unsafeStream.toSerialized();
  }

  protected void fireEvent(@Nonnull final EventObject event) {
    eventStream.onNext(event);
  }

  public Flowable<EventObject> getEventStream() {
    return eventStream;
  }
}