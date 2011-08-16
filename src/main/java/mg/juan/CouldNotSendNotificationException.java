package mg.juan;

/**
 * This Exception is to be thrown when the sending of a notification fails. The result should
 * be to break the build to make sure that no notifications are ignored just because the
 * notification fails.
 * 
 * @author Morten Granlund
 * @since 1.0
 */
public class CouldNotSendNotificationException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor, creating new instance of this exception. No empty constructor
	 * allowed.
	 * @param message the message og this exception.
	 * @param cause the root cause for this exception.
	 */
	public CouldNotSendNotificationException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
