package mg.aj;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("SENDING MAIL TO:");
		str.append(NEWLINE);
		String[] recipientArray = recipients.split("[,;]");
		for (String nextRecipient : recipientArray) {
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
		str.append("...WITH SUBJECT = \"" + subject + "\"");
		str.append("...AND BODY :");
		str.append(body);

		return str.toString();
	}

	public void sendEmail() throws IOException {
		// throw new IOException("Could not contact e-mail smart host!");
		System.out.println("SENDING Email...");
		System.out.println(this);

	}

	/**
	 * @throws CouldNotSendEmailException
	 */
	public void sendMailUsingGMailAndSSL() throws CouldNotSendEmailException {
		ResourceBundle emailConfig = ResourceBundle.getBundle("email");

		Properties sendMailProps = new Properties();

		sendMailProps.put("mail.smtp.host",
				emailConfig.getString("mail.smtp.host"));
		sendMailProps.put("mail.smtp.socketFactory.port",
				emailConfig.getString("mail.smtp.socketFactory.port"));
		sendMailProps.put("mail.smtp.socketFactory.class",
				emailConfig.getString("mail.smtp.socketFactory.class"));
		sendMailProps.put("mail.smtp.auth",
				emailConfig.getString("mail.smtp.auth"));
		sendMailProps.put("mail.smtp.port",
				emailConfig.getString("mail.smtp.port"));

		final String username = emailConfig.getString("mail.smtp.user");
		final String password = emailConfig.getString("mail.smtp.password");

		Session session = Session.getDefaultInstance(sendMailProps,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			String from = emailConfig.getString("mail.smtp.sender");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(this.recipients));
			message.setSubject(this.subject);
			message.setText(this.body);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new CouldNotSendEmailException(
					"Could not send Notification Email due to the following exception: "
							+ e.getMessage(), e);
		}
	}

}
