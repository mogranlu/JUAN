package mg.aj;

public class NotificationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Object notification;
	
	public NotificationException() {
		super();
	}
	
	public NotificationException(Object notification) {
		super();
		this.notification = notification;
	}
	
	public Object getNotification() {
		return notification;
	}
}
