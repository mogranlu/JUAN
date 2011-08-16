package mg.juan;

import mg.juan.email.GMailNotifier;

/**
 * Defines the methods necessary by all Email notifiers being implemented within this notifying framework.
 * 
 * @author Morten Granlund
 * @since 1.0
 * @see {@link GMailNotifier}
 */
public interface Notifier {

	public abstract String toString();

	public abstract void sendNotification() throws CouldNotSendNotificationException;

	void setRecipient(String recipient);

	String getRecipient();

	void setCc(String cc);

	String getCc();

	void setSubject(String subject);

	String getSubject();

	void setBody(String body);

	String getBody();

}
