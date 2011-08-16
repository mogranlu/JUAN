package mg.juan.email.aspects;

<<<<<<< HEAD:src/main/java/mg/aj/aspects/EmailNotificationAspect.aj
import mg.aj.CouldNotSendNotificationException;
import mg.aj.GMailNotifier;
import mg.aj.NotificationError;
import mg.aj.Notifier;
import mg.aj.annotations.Notification;

/**
 * This is the aspect containing code that will guide (wrap around) all methods annotated with {@link Notification}. 
 * @author Morten Granlund
 * @since 1.0
 *
=======
import mg.juan.NotificationError;
import mg.juan.Notifier;
import mg.juan.Notify;
import mg.juan.email.CouldNotSendEmailException;
import mg.juan.email.GMailNotifier;
import mg.juan.email.annotations.EmailNotification;

/**
 * This aspect will guide all methods annotated with {@link EmailNotification}
 * so that failing tests using the {@link Notify} methods/tests will cause a
 * Notification to be sent.
 * 
 * @author Morten Granlund
 * @since 1.0
>>>>>>> 9751d5d7fcb6787af0c8317ad4569fb374c31651:src/main/java/mg/juan/email/aspects/EmailNotificationAspect.aj
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
<<<<<<< HEAD:src/main/java/mg/aj/aspects/EmailNotificationAspect.aj
				throw new Error("Something went wrong while attempting to send e-mail notification: "
						+ e.getMessage(), e);
=======
				throw new Error(
						"Something went wrong while attempting to send e-mail notification: "
								+ e.getMessage(), e);
>>>>>>> 9751d5d7fcb6787af0c8317ad4569fb374c31651:src/main/java/mg/juan/email/aspects/EmailNotificationAspect.aj
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
