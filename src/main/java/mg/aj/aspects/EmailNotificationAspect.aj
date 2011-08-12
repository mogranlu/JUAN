package mg.aj.aspects;

import static org.junit.Assert.fail;

import java.io.IOException;

import mg.aj.Email;
import mg.aj.NotificationException;
import mg.aj.annotations.EmailNotification;

public aspect EmailNotificationAspect {
	pointcut sendEmailWhenNotificationExceptionIsThrown(EmailNotification notif) :
		  execution(* *(..)) && @annotation(notif);
	
	Object around(EmailNotification notif) : sendEmailWhenNotificationExceptionIsThrown(notif) {
		Object result = null;
		try {
			result = proceed(notif);
		} catch (NotificationException ex) {
			
			Email mail = new Email(notif.subject(), notif.recipient(), notif.cc(), "");
			try {
				mail.sendEmail();
			} catch (IOException e) {
				e.printStackTrace();
				fail("Something went wrong while attempting to send e-mail notification: " + e.getMessage());
			}
		} 
		return result;
		
	}
	
}
