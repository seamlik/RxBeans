package rxbeans;

import io.reactivex.Flowable;
import java.util.EventObject;

public interface Object {

  Flowable<EventObject> getEventStream();
}