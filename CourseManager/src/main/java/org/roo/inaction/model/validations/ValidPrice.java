/**
 * 
 */
package org.roo.inaction.model.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author leonardo
 * 
 */
@NotNull
@DecimalMin("0")
@DecimalMax("10000")
@Documented
@Constraint(validatedBy = ValidPriceValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
		ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPrice {
	String message() default "Price is invalid - must be between 0 and 10,000 with no fractional values.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	String value();
}
