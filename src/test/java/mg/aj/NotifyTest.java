package mg.aj;

import org.junit.Test;

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
