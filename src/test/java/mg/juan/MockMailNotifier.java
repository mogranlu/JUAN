package mg.juan;

import mg.juan.Notifier;

public class MockMailNotifier implements Notifier {

	String recipient = "joe@there.is.no.way.someone.is.receiving.this.mocked.email.foo,bret@there.is.no.way.someone.is.receiving.this.mocked.email.foo";
	String cc = "frank@there.is.no.way.someone.is.receiving.this.mocked.email.foo";
	String subject = "Mocked e-mail!";
	String body = "Hello!"
			+ System.getProperty("line.separator")
			+ "If you can read this message, you have received a mocked e-mail from the Juan framework!";

	@Override
	public void sendNotification() throws CouldNotSendNotificationException {
		System.out.println("[MOCKMAIl] Sending mocked email (of type :"
				+ MockMailNotifier.class.getName()
				+ " with the following detailed info:");
		System.out.println("\tTO:\t" + getRecipient());
		System.out.println("\tCC:\t" + getCc());
		System.out.println("\tSUBJECT:\t" + getSubject());
		System.out.println("\tBODY:\t" + getBody());
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
	public void setCc(String cc) {
		this.cc = cc;
	}
	
	@Override
	public String getCc() {
		return this.cc;
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
