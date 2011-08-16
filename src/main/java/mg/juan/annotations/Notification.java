package mg.juan.annotations;

import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import org.junit.Test;

import mg.juan.Notifier;

/**
 * This is the annotation to be set around methods where a notification should be sent
 * if one of {@link Notifier}'s tests fails. This mechanism is intentionally imitating the way that JUnit's {@link Test}
 * annotation works, but instead of "breaking the build", failing conditions/tests will cause a notification
 * to be sent, while the build may continue.
 * 
 * @author Morten Granlund
 * @since 1.0
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Notification {
	String recipient();
	String cc() default "";
	String subject();
	String body() default "";
	String notifier() default "mg.juan.email.GMailNotifier";
}
