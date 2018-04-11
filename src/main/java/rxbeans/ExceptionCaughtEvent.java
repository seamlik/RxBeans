package rxbeans;

import java.util.EventObject;

/**
 * Indicates a {@link Throwable} is thrown in the async realm.
 */
public class ExceptionCaughtEvent extends EventObject {

  private final Throwable cause;

  /**
   * Default constructor.
   */
  public ExceptionCaughtEvent(final java.lang.Object source, final Throwable cause) {
    super(source);
    this.cause = cause;
  }

  /**
   * Gets the cause.
   */
  public Throwable getCause() {
    return cause;
  }
}