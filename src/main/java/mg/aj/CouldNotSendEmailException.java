package mg.aj;

public class CouldNotSendEmailException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CouldNotSendEmailException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
