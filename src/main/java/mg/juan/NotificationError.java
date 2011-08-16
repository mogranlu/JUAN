package mg.juan;

/**
 * The Error being thrown when a Notify method has been called, and the
 * condition fails. This Error will be picked up by an appropriate aspect, e.g.
 * {@link mg.juan.email.aspects.EmailNotificationAspect}, if the method where
 * the static {@link mg.juan.Notify} methods was called from has been annotated
 * with a {@link mg.juan.annotations.Notification} annotation. These will turn a NotificationError
 * to be turned into a notification.
 * 
 * @author Morten Granlund
 * @since 1.0
 * 
 */
public class NotificationError extends Error {

	private static final long serialVersionUID = 1L;
	private Object notification;

	public NotificationError(Object notification) {
		super();
		this.notification = notification;
	}

	public Object getNotification() {
		return notification;
	}
}
