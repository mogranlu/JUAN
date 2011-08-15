package mg.juan;

import mg.juan.Notifier;
import mg.juan.email.CouldNotSendEmailException;

public class MockMailNotifier implements Notifier {

	String recipient = "non-existing-email-recipient";
	String subject = "non-existing-email-subject";
	String body = "non-existing-email-body";

	@Override
	public void sendEmail() throws CouldNotSendEmailException {
		System.out.println("Sending mocked email (of type :"
				+ MockMailNotifier.class.getName()
				+ " with the following detailed info:");
		System.out.println("\tTO:\t" + getRecipient());
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
