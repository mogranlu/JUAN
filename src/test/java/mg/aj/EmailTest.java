package mg.aj;

import mg.aj.annotations.EmailNotification;

import org.junit.Test;

public class EmailTest {

	/**
	 * Test that the NotificationException is caught by aspect
	 */
	@Test
	@EmailNotification(recipient = "jay.yoo.nit@snailmail.com;e.meihl@foo.com,steve.unemployed@pear.com", subject = "Someone is reusing your framework database!")
	public void testThatTheNotificationExceptionIsCaughtAndHandledByTheAspect() {
		// Test that the notification exception is caught and handled by the 
		throw new NotificationException();
	}
	
	@Test(expected = NotificationException.class)
	public void testThatNotificationExceptionIsNotWrappedAndHandledByAspectWhenNotAnnotated() {
		throw new NotificationException();
	}
	
}
