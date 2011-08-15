package mg.aj;

/**
 * This is the mock of an e-mail notifier, primarily meant for JUnit tests.
 * 
 * @author Morten Granlund
 * @since 1.0
 */
public class MockMailNotifier implements Notifier {

	protected String recipient = "joe@no-way-someon-has-this-non-existing-mocked-email-address.com,ben@no-way-someon-has-this-non-existing-mocked-email-address.com";
	protected String cc = "frank@no-way-someon-has-this-non-existing-mocked-email-address.com";
	protected String subject = "Mocked E-mail subject";
	protected String body = "Mocked E-mail body";

	@Override
	public void sendNotification() throws CouldNotSendNotificationException {
		System.out.println("Sending mocked email (of type :"
				+ MockMailNotifier.class.getName()
				+ " with the following detailed info:");
		System.out.println("\tTO:\t" + getRecipient());
		System.out.println("\tCC:\t" + getCc());
		System.out.println("\tSUBJECT:\t" + getSubject());
		System.out.println("\tBODY:\t" + getBody());
	}

	@Override
	public void setCc(String cc) {
		this.cc = cc;
	}

	@Override
	public String getCc() {
		return cc;
	}

	@Override
	public void setRecipient(String recipient) {
		this.recipient = recipient;

	}

	@Override
	public String getRecipient() {
		return this.recipient;
	}

	@Override
	public void setSubject(String subject) {
		this.subject = subject;

	}

	@Override
	public String getSubject() {
		return this.subject;
	}

	@Override
	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String getBody() {
		return this.body;
	}

}
