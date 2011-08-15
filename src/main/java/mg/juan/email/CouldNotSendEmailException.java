package mg.juan.email;

/**
 * This Exception is to be thrown when the sending of an e-mail failed. The result should
 * be to break the build to make sure that no notifications are ignored just because the
 * mail sending failed.
 * @author Morten Granlund
 * @since 1.0
 */
public class CouldNotSendEmailException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CouldNotSendEmailException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
