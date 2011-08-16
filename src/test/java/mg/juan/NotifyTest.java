package mg.juan;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.junit.Test;

/**
 * Unit tests for the class {@link Notify}.
 * 
 * @author Morten Granlund
 * @since 1.0
 */
public class NotifyTest {

	private final static String DEFAULT_LOCALE = "";
	private final static String NORWEGIAN_LOCALE = "_no_NO";
	private final static String SWEDISH_LOCALE = "_se_SE";
	private final static String BASE_PATH_TO_MOCKED_RESOURCE_BUNDLES = "src/test/resources/mg/juan/mockedbundles/ui_textresources";

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
	public void testThatMockedResourcesBundleWith_Default_LocaleCanBeLoaded()
			throws IOException {
		ResourceBundle defBundle = loadResourcesWithLocale(DEFAULT_LOCALE);
		assertNotNull(defBundle);
		assertThat(defBundle.keySet().size(), is(greaterThan(0)));
	}

	@Test
	public void testThatMockedResourcesBundleWith_Swedish_LocaleCanBeLoaded()
			throws IOException {
		ResourceBundle swedishBundle = loadResourcesWithLocale(SWEDISH_LOCALE);
		assertNotNull(swedishBundle);
		assertThat(swedishBundle.keySet().size(), is(greaterThan(0)));
	}

	@Test
	public void testThatMockedResourcesBundleWith_Norwegian_LocaleCanBeLoaded()
			throws IOException {
		ResourceBundle norwegianBundle = loadResourcesWithLocale(NORWEGIAN_LOCALE);
		assertNotNull(norwegianBundle);
		assertThat(norwegianBundle.keySet().size(), is(greaterThan(0)));
	}

	@Test(expected = NotificationError.class)
	public void testThatDiffResourceBundleMethodDetectsDifferences()
			throws IOException {
		PropertyResourceBundle swedishText = loadResourcesWithLocale(SWEDISH_LOCALE);
		PropertyResourceBundle norwegianText = loadResourcesWithLocale(NORWEGIAN_LOCALE);

		// This should throw an NotificationError (we expect a difference in the
		// default and Swedish resource bundle mocks)!
		Notify.notifyIfDifferencesExistInResourceBundlesWithDifferentLocales(
				norwegianText, swedishText);
	}

	@Test
	public void testThatDiffResourceBundleMethodDoesNotFailOnEqualBundles()
			throws IOException {
		PropertyResourceBundle englishText = loadResourcesWithLocale(DEFAULT_LOCALE);
		PropertyResourceBundle norwegianText = loadResourcesWithLocale(NORWEGIAN_LOCALE);

		// Should not throw error or exception (expects default and Norwegian
		// mock bundles to contain same properties):
		Notify.notifyIfDifferencesExistInResourceBundlesWithDifferentLocales(
				englishText, norwegianText);
	}

	/**
	 * Private helper method for fetching mocked resource bundles with a
	 * specified locale.
	 * 
	 * @param locale
	 *            the preferred locale of the mocked resource bundle.
	 * @return the mocked resource bundle based on the preferred locale.
	 */
	private PropertyResourceBundle loadResourcesWithLocale(String locale)
			throws IOException {
		File file = null;
		FileReader fileReader = null;
		try {
			file = new File(BASE_PATH_TO_MOCKED_RESOURCE_BUNDLES + locale
					+ ".properties");
			fileReader = new FileReader(file);
			PropertyResourceBundle bundle = new PropertyResourceBundle(
					fileReader);

			return bundle;
		} finally {
			if (fileReader != null) {
				fileReader.close();
			}
		}

	}

}
