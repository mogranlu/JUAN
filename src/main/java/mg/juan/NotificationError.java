package mg.juan;

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
