package mg.aj;

import mg.aj.annotations.EmailNotification;

import org.junit.Ignore;
import org.junit.Test;

public class EmailTest {

	/**
	 * Test that the NotificationException is caught by aspect
	 */
	@Test
	@EmailNotification(recipient = "jay.yoo.nit@snailmail.com;e.meihl@foo.com,steve.unemployed@pear.com", subject = "Someone is reusing your framework database!")
	public void testThatTheNotificationExceptionIsCaughtAndHandledByTheAspect() {
		// Test that the notification exception is caught and handled by the 
		throw new NotificationError();
	}
	
	@Test(expected = NotificationError.class)
	public void testThatNotificationExceptionIsNotWrappedAndHandledByAspectWhenNotAnnotated() {
		throw new NotificationError();
	}
	
	@Test(timeout = 15000)
	@Ignore
	public void testSendingAnEmailUsingGMailSTMP() throws CouldNotSendEmailException {
		String subject = "JavaZone - Morten - Juan";
		String recipient = "morten.granlund@gmail.com";
		String body = "This is a test e-mail sent from a framework I'm going to present\n" +
				"at the JavaZone conference in Oslo, Norway in September 2011!";
		Notifier email = new GMailNotifier(subject, recipient, null, body);
		
		email.sendEmail();

	}
	
}
