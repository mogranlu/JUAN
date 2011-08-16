package mg.juan;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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
	 * Sends a condition-less notification with a given message.
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

	/**
	 * 
	 */
	public static void notifyIfDifferencesExistInResourceBundles(
			ResourceBundle resource1, ResourceBundle resource2) {
		// First, traverse keys in bundle 1 and find missing values in bundle 2:
		Enumeration<String> keysInRes1 = resource1.getKeys();
		List<String> keysMissingInBundle2 = new ArrayList<String>();
		while (keysInRes1.hasMoreElements()) {
			String nextKeyInRes1 = null;
			String nextValueIn2 = null;
			try {
				nextKeyInRes1 = keysInRes1.nextElement();
				nextValueIn2 = resource2.getString(nextKeyInRes1);
			} catch (MissingResourceException mrEx) {
			}

			System.out.println(nextValueIn2);
			if (isBlank(nextValueIn2)) {
				keysMissingInBundle2.add(nextKeyInRes1);
			}
		}

		Enumeration<String> keysInRes2 = resource2.getKeys();
		List<String> keysMissingInBundle1 = new ArrayList<String>();
		while (keysInRes2.hasMoreElements()) {
			String nextKeyInRes2 = null;
			String nextValueIn1 = null;
			try {
				nextKeyInRes2 = keysInRes2.nextElement();
				nextValueIn1 = resource1.getString(nextKeyInRes2);
			} catch (MissingResourceException mrEx) {
			}
			System.out.println(nextValueIn1);
			if (isBlank(nextValueIn1)) {
				keysMissingInBundle1.add(nextKeyInRes2);
			}
		}

		// If both lists with missing properties are empty, then that is jolly
		// good!
		if (keysMissingInBundle2.isEmpty() && keysMissingInBundle1.isEmpty()) {
			// Success!
			return;
		} else {
			StringBuilder notifyMessage = new StringBuilder();
			if (keysMissingInBundle1.size() > 0) {
				notifyMessage
						.append("The following properties are missing in bundle #1: {");
				for (String nextMissingIn1 : keysMissingInBundle1) {
					notifyMessage.append(nextMissingIn1).append(", ");
				}
				notifyMessage.append("}.  ");
			}
			if (keysMissingInBundle2.size() > 0) {
				notifyMessage
						.append("The following properties are missing in bundle #1: {");
				for (String nextMissingIn2 : keysMissingInBundle2) {
					notifyMessage.append(nextMissingIn2).append(", ");
				}

				notifyMessage.append("}");
			}
			notify(notifyMessage.toString());
		}

	}

}
