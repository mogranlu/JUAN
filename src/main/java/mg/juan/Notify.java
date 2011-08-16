package mg.juan;

import mg.juan.annotations.Notification;

/**
 * This is the Notifier Addon framework's equivalent of JUnit's
 * {@link org.junit.Assert} class. Use the various notify... methods to get a
 * notification as soon as some customized condition (or set of conditions)
 * changes. Remember to annotate the methods where these methods are used with
 * the {@link Notification} annotation. This will catch the NotificationErrors
 * and trigger the actual sending of the registered notifications.
 * 
 * @author Morten Granlund
 * @since 1.0
 * @see {@link Notification}
 */
public class Notify {

	/**
	 * Performs a check on some condition, and - if the method containing the
	 * call to this method is annotated with {@link Notification} - sends a
	 * notification whenever this test fails. E.g.
	 * <code>notifyIf("Mismatch between English and Norwegian text files", enBundle.size() != noBundle.size());</code>
	 * will send a notification (e.g. an e-mail) as soon as someone forgets to
	 * translate a GUI text resource entry.
	 * 
	 * @param message
	 *            the message to be incorporated in your notification (if the
	 *            condition fails, and a notification is to be sent).
	 * @param condition
	 *            the condition for when a notification should be sent.
	 * @throws NotificationError
	 *             if the condition to resolves to the value <code>true</code>.
	 *             This error will be picked up by the appropriate aspect and
	 *             turned into a notification only if a method in the call stack
	 *             has been annotated with {@link Notification}.
	 */
	public static void notifyIf(String message, boolean condition)
			throws NotificationError {
		if (condition) {
			notify(message);
		}
	}

	/**
	 * An inverse flavor of the {@link #notifyIf()} method.
	 * 
	 * @param message
	 *            the message to be incorporated in your notification (if the
	 *            condition fails, and a notification is to be sent).
	 * @param condition
	 *            if this evaluates to false, a {@link NotificationError} will
	 *            be sent (and will cause a notification to be sent if a method
	 *            in the call stack has been annotated with the
	 *            {@link Notification} annotation.
	 * @throws NotificationError
	 *             if the condition to resolves to the value <code>true</code>.
	 *             This error will be picked up by the appropriate aspect and
	 *             turned into a notification only if a method in the call stack
	 *             has been annotated with {@link Notification}.
	 */
	public static void notifyIfNot(String message, boolean condition)
			throws NotificationError {
		notifyIf(message, !condition);
	}

	/**
	 * 
	 * 
	 * @param message
	 *            the message to be incorporated in your notification.
	 * @throws NotificationError
	 *             This error will be thrown. It will be picked up by the
	 *             appropriate aspect and turned into a notification only if a
	 *             method in the call stack has been annotated with
	 *             {@link Notification}.
	 * 
	 */
	public static void notify(String message) throws NotificationError {
		throw new NotificationError(message);
	}

}
