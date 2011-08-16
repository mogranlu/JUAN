package mg.juan;

import mg.juan.NotificationError;
import mg.juan.Notify;

import org.junit.Test;

/**
 * Unit tests for the class {@link Notify}. 
 * @author Morten Granlund
 * @since 1.0
 */
public class NotifyTest {

	@Test(expected = NotificationError.class)
	public void testThatThe_NotifyMethod_ThrowsNotificationError() {
		Notify.notify("This should cause the test to throw a NotificationError.");
	}

	@Test(expected = NotificationError.class)
	public void testThat_NotifyIf_MethodThrowsNotificationError() {
		Notify.notifyIf("This should cause a notification!", true);
	}

	@Test
	public void testThatNotifyIfMethodDoesNotNotifyOnFalseValue() {
		Notify.notifyIf("This should not cause a notification!", false);
	}

	@Test(expected = NotificationError.class)
	public void testThatMethod_NotifyIfNot_DoesNotifyOnFalseValue() {
		Notify.notifyIfNot("This method should cause a notification!", false);
	}

	@Test
	public void testThat_NotifyIfNot_DoesNotNotifyOnTrueValue() {
		Notify.notifyIfNot("This method should not cause a notification!", true);
	}

}
