package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import java.util.EventObject;
import javax.annotation.concurrent.ThreadSafe;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;

/**
 * Reference implementation of {@link Object}.
 */
@ThreadSafe
public class StandardObject implements Object {

  private final FlowableProcessor<EventObject> eventStream = PublishProcessor
      .<EventObject>create()
      .toSerialized();

  protected final void triggerEvent(
      @UnknownInitialization(StandardObject.class) StandardObject this,
      final EventObject event
  ) {
    eventStream.onNext(event);
  }

  @Override
  public final Flowable<EventObject> getEventStream(
      @UnknownInitialization(StandardObject.class) StandardObject this
  ) {
    return eventStream;
  }
}