package mg.aj.annotations;

import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface EmailNotification {
	String recipient();
	String cc() default "";
	String subject();
}
