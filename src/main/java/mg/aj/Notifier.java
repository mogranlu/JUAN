package mg.aj;


public interface Notifier {

	public abstract String toString();

	public abstract void sendEmail() throws CouldNotSendEmailException;
	void setRecipient(String recipient);
	String getRecipient();
	void setSubject(String subject);
	String getSubject();
	void setBody(String body);
	String getBody();
}
