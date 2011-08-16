package mg.juan;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

/**
 * Unit tests for the class {@link Notify}.
 * 
 * @author Morten Granlund
 * @since 1.0
 */
public class NotifyTest {

	private final static Locale DEFAULT_LOCALE = Locale.getDefault();
	private final static Locale NORWEGIAN_LOCALE = new Locale("no_NO");
	private final static Locale SWEDISH_LOCALE = new Locale("se_SE");

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

	@Test
	public void testThatMockedResourcesBundleWith_Default_LocaleCanBeLoaded() {
		ResourceBundle defBundle = loadResourcesWithLocale(DEFAULT_LOCALE);
		assertNotNull(defBundle);
		assertThat(defBundle.keySet().size(), is(greaterThan(0)));
	}

	@Test
	public void testThatMockedResourcesBundleWith_Swedish_LocaleCanBeLoaded() {
		ResourceBundle swedishBundle = loadResourcesWithLocale(SWEDISH_LOCALE);
		assertNotNull(swedishBundle);
		assertThat(swedishBundle.keySet().size(), is(greaterThan(0)));
	}

	@Test
	public void testThatMockedResourcesBundleWith_Norwegian_LocaleCanBeLoaded() {
		ResourceBundle norwegianBundle = loadResourcesWithLocale(NORWEGIAN_LOCALE);
		assertNotNull(norwegianBundle);
		assertThat(norwegianBundle.keySet().size(), is(greaterThan(0)));
	}

	@Test(expected = NotificationError.class)
	public void testThatDiffResourceBundleMethodDetectsDifferences() {
		// TODO: ResourceBundle "fail-over"s to default resource with English
		// locale! :-(
		ResourceBundle swedishText = loadResourcesWithLocale(SWEDISH_LOCALE);
		ResourceBundle norwegianText = loadResourcesWithLocale(NORWEGIAN_LOCALE);

		// This should throw an NotificationError (we expect a difference in the
		// default and Swedish resource bundle mocks)!
		Notify.notifyIfDifferencesExistInResourceBundles(norwegianText,
				swedishText);
	}

	/**
	 * Private helper method for fetching mocked resource bundles with a
	 * specified locale.
	 * 
	 * @param locale
	 *            the preferred locale of the mocked resource bundle.
	 * @return the mocked resource bundle based on the preferred locale.
	 */
	private ResourceBundle loadResourcesWithLocale(Locale locale) {
		String plainBundleBaseName = "mg.juan.mockedbundles.ui_textresources";
		ResourceBundle bundle = ResourceBundle.getBundle(plainBundleBaseName,
				locale);
		return bundle;
	}

}
