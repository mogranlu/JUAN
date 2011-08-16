package mg.juan;

<<<<<<< HEAD:src/test/java/mg/aj/EmailTest.java
import mg.aj.annotations.Notification;
=======
import mg.juan.NotificationError;
import mg.juan.email.CouldNotSendEmailException;
import mg.juan.email.annotations.EmailNotification;
>>>>>>> 9751d5d7fcb6787af0c8317ad4569fb374c31651:src/test/java/mg/juan/EmailTest.java

import org.junit.Ignore;
import org.junit.Test;

import static mg.juan.Notify.*;

public class EmailTest {

	/**
	 * Test that the NotificationException is caught by aspect
	 */
	@Test
<<<<<<< HEAD:src/test/java/mg/aj/EmailTest.java
	@Notification(recipient = "spam.granlund@gmail.com,morten.granlund@gmail.com", subject = "Someone is reusing your framework database (JavaZone presentation check)!", notifier = "mg.aj.MockMailNotifier")
=======
	@EmailNotification(recipient = "spam.granlund@gmail.com,morten.granlund@gmail.com", subject = "Someone is reusing your framework database (JavaZone presentation check)!", notifier="mg.juan.MockMailNotifier")
>>>>>>> 9751d5d7fcb6787af0c8317ad4569fb374c31651:src/test/java/mg/juan/EmailTest.java
	public void testThatTheNotificationExceptionIsCaughtAndHandledByTheAspect() {
		/*
		 * Test that the notification exception is caught and handled by aspect,
		 * so that an e-mail is sent instead of the error being sent up the call
		 * stack.
		 */
		throw new NotificationError("");
	}

	@Test(expected = NotificationError.class)
	public void testThatNotificationExceptionIsNotWrappedAndHandledByAspectWhenNotAnnotated() {
		/*
		 * This method is not annotated, so nothing should catch the
		 * NotificationError being thrown (this test expects the error)!
		 */
		throw new NotificationError("");
	}

	@Test
<<<<<<< HEAD:src/test/java/mg/aj/EmailTest.java
	@Notification(recipient = "recipient", subject = "subject", notifier = "mg.aj.MockMailNotifier")
=======
	@EmailNotification(recipient="recipient", subject="subject", notifier="mg.juan.MockMailNotifier")
>>>>>>> 9751d5d7fcb6787af0c8317ad4569fb374c31651:src/test/java/mg/juan/EmailTest.java
	public void testSomething() {
		notifyIfNot("Testing the notifyIfNot method!", false);
	}

	@Test(timeout = 15000)
	@Ignore("Don't use it if you don't mean it! Will actually send e-mails!")
	@Notification(recipient = "morten.granlund@gmail.com,spam.granlund@gmail.com", cc = "morten.granlund@capgemini.com", subject = "JavaZone - Morten - Juan")
	public void testSendingAnEmailUsingGMailSTMP()
			throws CouldNotSendNotificationException {
		notifyIf("This is the custom and expected string message (body)", true);
	}

}
