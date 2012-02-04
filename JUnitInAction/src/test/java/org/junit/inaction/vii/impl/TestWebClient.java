/**
 * 
 */
package org.junit.inaction.vii.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author leonardo
 * 
 */
public class TestWebClient {

	@Test
	public void testGetContentOk() throws Exception {
		MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
		MockInputStream mockStream = new MockInputStream();
		mockStream.setBuffer("It works");
		mockConnectionFactory.setData(mockStream);
		WebClient client = new WebClient();
		String result = client.getContent(mockConnectionFactory);
		Assert.assertEquals("It works", result);
		mockStream.verify();
	}
}
