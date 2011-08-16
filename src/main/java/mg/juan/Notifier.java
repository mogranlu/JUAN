package mg.juan;

import mg.juan.email.GMailNotifier;

/**
 * Defines the methods necessary by all Email notifiers being implemented within
 * this notifying framework.
 * 
 * @author Morten Granlund
 * @since 1.0
 * @see {@link GMailNotifier}
 */
public interface Notifier {

	/**
	 * Attempts to send a notification to the recipients
	 * 
	 * @throws CouldNotSendNotificationException
	 */
	public abstract void sendNotification()
			throws CouldNotSendNotificationException;

	/**
	 * Sets the intended recipient (or recipients in some implementations) of
	 * this notification.
	 * 
	 * @param recipient
	 *            this param is (hopefully) the receivers of this notification
	 *            (unless something goes wrong in the attempt). In e-mail
	 *            implementations this is the addresses put in the "TO" field,
	 *            unlike the {@link #setCc(String)} which, as the name implies
	 *            is for the carbon copy recipients.
	 * @see #setCc(String)
	 */
	void setRecipient(String recipient);

	/**
	 * Sets the intended recipient (or recipients in some implementations) of
	 * this notification.
	 * 
	 * @return recipient this param is (hopefully) the receivers of this
	 *         notification (unless something goes wrong in the attempt). In
	 *         e-mail implementations this is the addresses put in the "TO"
	 *         field, unlike the {@link #getCc(String)} which, as the name
	 *         implies is for the carbon copy recipients.
	 * @see #getCc(String)
	 */
	String getRecipient();

	/**
	 * Sets the "carbon copy" recipients of this notification (may be ignored by
	 * non-email implementations). See http://en.wikipedia.org/wiki/Carbon_copy
	 * for details.
	 * 
	 * @param cc
	 *            the carbon copy recipients.
	 */
	void setCc(String cc);

	/**
	 * Gets the carbon copy recipients of this notifications. (may be ignored by
	 * non-email implementations).
	 * 
	 * @return the carbon copy recipients of this notifications.
	 * @see #setCc(String)
	 */
	String getCc();

	/**
	 * Sets the subject of this notification.
	 * @param the subject of this notification.
	 */
	void setSubject(String subject);

	/**
	 * Gets the subject of this notification.
	 * @return subject of this notification.
	 */
	String getSubject();

	/**
	 * Sets the body (main message/text) of this notification.
	 * @param body the body (main message/text) of this notification.
	 */
	void setBody(String body);

	/**
	 * Gets the body (main message/text) of this notification.
	 * @return the body (main message/text) of this notification.
	 */
	String getBody();

}
