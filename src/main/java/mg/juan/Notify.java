package mg.juan;

/**
 * 
 * @author Morten
 * @since 1.0
 */
public class Notify {
	
	public static void notifyIf(String message, boolean condition) throws NotificationError {
		if (condition) {
			notify(message);
		}
	}
	
	public static void notifyIfNot(String message, boolean condition) throws NotificationError {
		notifyIf(message, !condition);
	}
	
	public static void notify(String message) throws NotificationError {
		throw new NotificationError(message);
	}
}
