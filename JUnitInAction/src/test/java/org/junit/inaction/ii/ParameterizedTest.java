/**
 * 
 */
package org.junit.inaction.ii;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.inaction.i.Calculator;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author leonardo
 * 
 */
@RunWith(value=Parameterized.class)
public class ParameterizedTest {
	private double expected;
	private double valueOne;
	private double valueTwo;

	@Parameters
	public static Collection<Integer[]> getTestParameters() {
		return Arrays.asList(new Integer[][] { { 2, 1, 1 }, // expected,
															// valueOne,
															// valueTwo
				{ 3, 2, 1 }, // expected, valueOne, valueTwo
				{ 4, 3, 1 }, // expected, valueOne, valueTwo
		});
	}

	public ParameterizedTest(double expected, double valueOne, double valueTwo) {
		this.expected = expected;
		this.valueOne = valueOne;
		this.valueTwo = valueTwo;
	}
	
	@Test
	public void sum() {
		Calculator calc = new Calculator();
		Assert.assertEquals(expected, calc.add(valueOne, valueTwo), 0);
	}
}
