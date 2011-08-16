package mg.juan.email;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mg.juan.CouldNotSendNotificationException;
import mg.juan.Notifier;

/**
 * This is a relatively simple (and default) implementation of the
 * {@link Notifier} interface, allowing the sending of an e-mail notification
 * through Google's GMail service, using the user account specified in the
 * separate properties file. The implementation is based on SMTP, making this
 * implementation as simple as possible. Details about the username, passord,
 * URL, port number etc. are read from the external properties file defined by
 * the constant {@link #GMAIL_CONFIG_FILE}.
 * 
 * @author Morten Granlund
 * @since 1.0
 */
public class GMailNotifier implements Notifier {

	private String subject;
	private String recipient;
	private String cc;
	private String body;

	/**
	 * Corresponds to "gmail.properties" when being read with/as a
	 * {@link ResourceBundle} with default locale
	 */
	protected static final String GMAIL_CONFIG_FILE = "mg.juan.email.gmail";

	private static final String NEWLINE = System.getProperty("line.separator");

	/**
	 * Creates a new instance of this GMail-based notifier, with a pre-defined
	 * mail subject, mail recipient list, carbon copy (CC), and the body of the
	 * E-mail.
	 * 
	 * @param subject
	 *            the e-mail subject of the e-mail being sent.
	 * @param recipient
	 *            the email address(es) of the recipients. If there is more than
	 *            one recipient, use the comma character ',' to separate them.
	 * @param cc
	 *            The CC field is
	 * @param body
	 */
	public GMailNotifier(String subject, String recipient, String cc,
			String body) {
		this.setSubject(subject);
		this.setRecipient(recipient);
		this.setCc(cc);
		this.setBody(body);
	}

	/*
	 * (non-Javadoc) Useful implementation used for "standard out debugging".
	 * 
	 * @see java.lang.Object#toString()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see mg.juan.Notifier#sendNotification()
	 */
	public void sendNotification() throws CouldNotSendNotificationException {
		ResourceBundle emailConfig = ResourceBundle
				.getBundle(GMAIL_CONFIG_FILE);

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
					InternetAddress.parse(this.getRecipient()));
			message.setRecipients(Message.RecipientType.CC,
					InternetAddress.parse(this.getCc()));
			message.setSubject(this.getSubject());
			message.setText(this.getBody());

			logToConsole(" >>> GMail notification (\"" + message.getSubject()
					+ "\") is armed and ready to be launched!");
			logToConsole(" >>> With the following message body:");
			logToConsole(getBody());

			// Do the actual sending of the e-mail!
			Transport.send(message);

			logToConsole(" >>> GMail notification (\"" + message.getSubject()
					+ "\") sent!");

		} catch (MessagingException e) {
			throw new CouldNotSendNotificationException(
					"Could not send Notification Email due to the following exception: "
							+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mg.juan.Notifier#getSubject()
	 */
	public String getSubject() {
		return subject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mg.juan.Notifier#setSubject(java.lang.String)
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mg.juan.Notifier#getRecipient()
	 */
	public String getRecipient() {
		return recipient;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mg.juan.Notifier#setRecipient(java.lang.String)
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mg.juan.Notifier#getCc()
	 */
	public String getCc() {
		return cc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mg.juan.Notifier#setCc(java.lang.String)
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mg.juan.Notifier#getBody()
	 */
	public String getBody() {
		return body;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mg.juan.Notifier#setBody(java.lang.String)
	 */
	public void setBody(String body) {
		this.body = body;
	}

	private void logToConsole(String logMsg) {
		System.out.println(logMsg);
	}
}
