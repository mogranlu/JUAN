package mg.aj;

import mg.aj.annotations.EmailNotification;

import org.junit.Ignore;
import org.junit.Test;
import static mg.aj.Notify.*;

public class EmailTest {

	/**
	 * Test that the NotificationException is caught by aspect
	 */
	@Test
	@EmailNotification(recipient = "spam.granlund@gmail.com,morten.granlund@gmail.com", subject = "Someone is reusing your framework database (JavaZone presentation check)!", notifier="mg.aj.MockMailNotifier")
	public void testThatTheNotificationExceptionIsCaughtAndHandledByTheAspect() {
		// Test that the notification exception is caught and handled by aspect,
		// so that an e-mail is sent instead of the error being sent up the call
		// stack.
		throw new NotificationError("");
	}

	@Test(expected = NotificationError.class)
	public void testThatNotificationExceptionIsNotWrappedAndHandledByAspectWhenNotAnnotated() {
		// This method is not annotated, so nothing should catch the
		// NotificationError being thrown (this test expects the error)!
		throw new NotificationError("");
	}
	
	@Test
	@EmailNotification(recipient="recipient", subject="subject", notifier="mg.aj.MockMailNotifier")
	public void testSomething() {
		notifyIfNot("Testing the notifyIfNot method!", false);
	}

	@Test (timeout = 15000)
	@Ignore("Don't use it if you don't mean it! Will actually send e-mails!")
	@EmailNotification(recipient="morten.granlund@gmail.com,spam.granlund@gmail.com", subject="JavaZone - Morten - Juan")
	public void testSendingAnEmailUsingGMailSTMP()
			throws CouldNotSendEmailException {
		notifyIf("This is the custom and expected string message (body)", true);
	}

}
