package mg.aj;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GMailNotifier implements Notifier {
	private String subject;
	private String recipient;
	private String cc;
	private String body;
	private static final String NEWLINE = System.getProperty("line.separator");

	/**
	 * 
	 * @param subject
	 * @param recipient the email address(es) of the recipients. If there is more than one, use to 
	 * @param cc
	 * @param body
	 */
	public GMailNotifier(String subject, String recipient, String cc,
			String body) {
		this.setSubject(subject);
		this.setRecipient(recipient);
		this.setCc(cc);
		this.setBody(body);
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("SENDING MAIL TO:");
		str.append(NEWLINE);
		String[] recipientArray = getRecipient().split("[,;]");
		for (String nextRecipient : recipientArray) {
			str.append("\t" + nextRecipient);
			str.append(NEWLINE);
		}
		str.append("CC TO:");
		str.append(NEWLINE);

		String[] nextCcList = getCc().split("[,;]");
		for (String nextCC : nextCcList) {
			str.append("\t" + nextCC);
			str.append(NEWLINE);
		}
		str.append("...WITH SUBJECT = \"" + getSubject() + "\"");
		str.append("...AND BODY :");
		str.append(getBody());

		return str.toString();
	}

	public void sendEmail()  throws CouldNotSendEmailException {
		ResourceBundle emailConfig = ResourceBundle.getBundle("gmail");

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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.getRecipient()));
			message.setSubject(this.getSubject());
			message.setText(this.getBody());

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new CouldNotSendEmailException(
					"Could not send Notification Email due to the following exception: "
							+ e.getMessage(), e);
		}
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
}
