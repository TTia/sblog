package sblog.repository;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/*
@Documented
@Constraint(validatedBy = PostTitleUniquenessValidator.class)
@Target(value=ElementType.FIELD)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface UniqueTitle {

	String message() default "Il titolo è già presente.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
*/