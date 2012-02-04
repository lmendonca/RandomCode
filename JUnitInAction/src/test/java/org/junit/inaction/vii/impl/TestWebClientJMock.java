/**
 * 
 */
package org.junit.inaction.vii.impl;

import java.io.IOException;
import java.io.InputStream;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.inaction.vii.ConnectionFactory;
import org.junit.runner.RunWith;

/**
 * @author leonardo
 * 
 */
@RunWith(JMock.class)
public class TestWebClientJMock {
	private Mockery context = new JUnit4Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	@Test
	public void testGetContentOk() throws Exception {
		final ConnectionFactory factory = context.mock(ConnectionFactory.class);
		final InputStream mockStream = context.mock(InputStream.class);
		context.checking(new Expectations() {
			{
				oneOf(factory).getData();
				will(returnValue(mockStream));
				atLeast(1).of(mockStream).read();
				will(onConsecutiveCalls(returnValue(new Integer((byte) 'W')),
						returnValue(new Integer((byte) 'o')),
						returnValue(new Integer((byte) 'r')),
						returnValue(new Integer((byte) 'k')),
						returnValue(new Integer((byte) 's')),
						returnValue(new Integer((byte) '!')), returnValue(-1)));
				oneOf(mockStream).close();
			}
		});
		WebClient client = new WebClient();
		String result = client.getContent(factory);
		Assert.assertEquals("Works!", result);
	}

	@Test
	public void testGetContentCannotCloseInputStream() throws Exception {
		final ConnectionFactory factory = context.mock(ConnectionFactory.class);
		final InputStream mockStream = context.mock(InputStream.class);
		context.checking(new Expectations() {
			{
				oneOf(factory).getData();
				will(returnValue(mockStream));
				oneOf(mockStream).read();
				will(returnValue(-1));
				oneOf(mockStream).close();
				will(throwException(new IOException("cannot close")));
			}
		});
		WebClient client = new WebClient();
		String result = client.getContent(factory);
		Assert.assertNull(result);
	}
}
