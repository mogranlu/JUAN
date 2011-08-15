package mg.aj;


/**
 * 
 * @author Morten Granlund
 * @since 1.0
 */
public interface Notifier {
	void sendNotification() throws CouldNotSendNotificationException;
	void setRecipient(String recipient);
	String getRecipient();
	void setCc(String cc);
	String getCc();
	void setSubject(String subject);
	String getSubject();
	void setBody(String body);
	String getBody();
	String toString();
}
