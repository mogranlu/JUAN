package mg.aj.aspects;

import static org.junit.Assert.fail;

import java.io.IOException;

import mg.aj.CouldNotSendEmailException;
import mg.aj.GMailNotifier;
import mg.aj.Notifier;
import mg.aj.NotificationError;
import mg.aj.annotations.EmailNotification;

public aspect EmailNotificationAspect {
	pointcut sendEmailWhenNotificationExceptionIsThrown(EmailNotification notif) :
		  execution(* *(..)) && @annotation(notif);
	
	Object around(EmailNotification notif) : sendEmailWhenNotificationExceptionIsThrown(notif) {
		Object result = null;
		try {
			result = proceed(notif);
		} catch (NotificationError ex) {
			
			Notifier mail = new GMailNotifier(notif.subject(), notif.recipient(), notif.cc(), "");
			try {
				mail.sendEmail();
			} catch (CouldNotSendEmailException e) {
				e.printStackTrace();
				fail("Something went wrong while attempting to send e-mail notification: " + e.getMessage());
			}
		} 
		return result;
		
	}
	
}
