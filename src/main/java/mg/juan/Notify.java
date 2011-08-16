package mg.juan;

<<<<<<< HEAD:src/main/java/mg/aj/Notify.java

/**
 * This is the Notifier Addon framework's equivalent of JUnit's {@link org.junit.Assert} class.  Use the notify... methods to
 * get a notification as soon as some customized condition (or set of conditions) changes.
 * 
 * @author Morten Granlund
=======
/**
 * 
 * @author Morten
>>>>>>> 9751d5d7fcb6787af0c8317ad4569fb374c31651:src/main/java/mg/juan/Notify.java
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
