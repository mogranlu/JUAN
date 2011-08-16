package mg.juan.email.aspects;

import mg.juan.CouldNotSendNotificationException;
import mg.juan.NotificationError;
import mg.juan.Notifier;
import mg.juan.Notify;
import mg.juan.email.GMailNotifier;
import mg.juan.email.annotations.Notification;

/**
 * This aspect will guide all methods annotated with {@link Notification} so
 * that failing tests using the {@link Notify} methods/tests will cause a
 * Notification to be sent.
 * 
 * @author Morten Granlund
 * @since 1.0
 */
public aspect EmailNotificationAspect {
	pointcut sendEmailWhenNotificationErrorIsThrown(Notification notif) :
		  execution(* *(..)) && @annotation(notif);

	Object around(Notification notif) : sendEmailWhenNotificationErrorIsThrown(notif) {
		Object result = null;
		try {
			result = proceed(notif);
		} catch (NotificationError ex) {
			String subject = notif.subject();
			String recipient = notif.recipient();
			String cc = notif.cc();

			String body = ex.getNotification().toString();

			// Default Notifier is GMailNotifier!
			Notifier notifier = new GMailNotifier(subject, recipient, cc, body);

			// Check if another notifier is explicitly set in the notification
			// annotation:
			String notifierImplementationClass = notif.notifier();
			if (notifierImplementationClass != null
					&& notifierImplementationClass.trim().length() > 0) {
				// Don't create the default one with reflection since it has
				// already been implemented!
				if (!GMailNotifier.class.getName().trim()
						.contains(notifierImplementationClass.trim())) {
					try {
						Class<?> c = Class.forName(notifierImplementationClass);
						Object o = c.newInstance(); // InstantiationException
						notifier = (Notifier) o;
					} catch (ClassNotFoundException cnfEx) {
						// Warn and failover to default notifier!
						logWarningOnNotifierFailover(cnfEx);
					} catch (InstantiationException instEx) {
						// Warn and failover to default notifier!
						logWarningOnNotifierFailover(instEx);
					} catch (IllegalAccessException iaEx) {
						// Warn and failover to default notifier!
						logWarningOnNotifierFailover(iaEx);
					}
				}
			}

			try {
				notifier.sendNotification();
			} catch (CouldNotSendNotificationException e) {
				e.printStackTrace();
				throw new Error(
						"Something went wrong while attempting to send e-mail notification: "
								+ e.getMessage(), e);
			}
		}
		return result;

	}

	private void logWarningOnNotifierFailover(Throwable rootCause) {
		System.out
				.println("Switching to default notifier. Could not instantiate explicit notifier due the following :");
		rootCause.printStackTrace();
	}

}
