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

public interface Notifier {

	public abstract String toString();

	public abstract void sendEmail() throws CouldNotSendEmailException;

}
