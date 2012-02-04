/**
 * 
 */
package org.roo.inaction.model.validations;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author leonardo
 *
 */
public class ValidPriceValidator implements ConstraintValidator<ValidPrice, BigDecimal> {

	private BigDecimal increment = new BigDecimal("0.0");
	
	@Override
	public void initialize(ValidPrice constraintAnnotation) {
		this.increment = new BigDecimal(constraintAnnotation.value());		
	}

	@Override
	public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
		if (value == null){
			return true;
		}
		
		if (increment.doubleValue() == 0){
			return true;
		}
		
		return value.remainder(increment).compareTo(BigDecimal.ZERO) == 0;
	}

}
