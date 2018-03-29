package rxbeans;

import java.util.EventObject;

/**
 * Indicates an exception is thrown in an async realm.
 */
public class ExceptionCaughtEvent extends EventObject {

  private final Exception exception;

  /**
   * Default constructor.
   */
  public ExceptionCaughtEvent(java.lang.Object source, Exception exception) {
    super(source);
    this.exception = exception;
  }

  /**
   * Gets the exception.
   */
  public Exception getException() {
    return exception;
  }
}