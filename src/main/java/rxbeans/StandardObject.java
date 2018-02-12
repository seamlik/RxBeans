package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import java.util.EventObject;
import javax.annotation.Nonnull;

public class StandardObject implements Object {

  private final FlowableProcessor<EventObject> eventStream;

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