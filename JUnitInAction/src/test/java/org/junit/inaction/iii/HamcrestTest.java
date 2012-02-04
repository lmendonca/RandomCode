/**
 * 
 */
package org.junit.inaction.iii;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

/**
 * @author leonardo
 * 
 */
public class HamcrestTest {
	private List<String> values;

	@Before
	public void setUpList() {
		values = new ArrayList<String>();
		values.add("x");
		values.add("y");
		values.add("z");
	}

	@Test
	public void testWithoutHamcrest() {
		Assert.assertTrue(values.contains("one") || values.contains("two")
				|| values.contains("three"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testWithHamcrest() {
		Assert.assertThat(
				values,
				JUnitMatchers.hasItem(CoreMatchers.anyOf(
						CoreMatchers.equalTo("one"),
						CoreMatchers.equalTo("two"),
						CoreMatchers.equalTo("three"))));
	}
}
