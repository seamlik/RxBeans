package rxbeans;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import java.util.EventObject;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;

/**
 * Reference implementation of {@link Object}. This class is thread-safe.
 */
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