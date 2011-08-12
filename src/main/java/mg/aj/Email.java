package mg.aj;

import java.io.IOException;

public class Email {
	private String subject;
	private String recipients;
	private String cc;
	private String body;
	private static final String NEWLINE = System.getProperty("line.separator"); 

	public Email(String subject, String recipients, String cc, String body) {
		this.subject = subject;
		this.recipients = recipients;
		this.cc = cc;
		this.body = body;
	}
	
	@Deprecated 
	public Email(String subject) {
		this.subject = subject;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("SENDING MAIL TO:");
		str.append(NEWLINE);
		String[] recipientArray = recipients.split("[,;]");
		for (String nextRecipient : recipientArray ) {
			str.append("\t" + nextRecipient);
			str.append(NEWLINE);
		}
		str.append("CC TO:");
		str.append(NEWLINE);
		
		String[] nextCcList = cc.split("[,;]");
		for (String nextCC : nextCcList) {
			str.append("\t" + nextCC);
			str.append(NEWLINE);
		}
		str.append("...WITH SUBJECT = \"" + subject +  "\"");
		str.append("...AND BODY :");
		str.append(body);
	
		return str.toString();
	}
	
	public void sendEmail() throws IOException {
//		throw new IOException("Could not contact e-mail smart host!");
		System.out.println("SENDING Email...");
		System.out.println(this);
		
	}
	
}
