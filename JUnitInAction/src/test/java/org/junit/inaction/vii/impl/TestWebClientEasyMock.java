/**
 * 
 */
package org.junit.inaction.vii.impl;

import java.io.IOException;
import java.io.InputStream;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.inaction.vii.ConnectionFactory;

/**
 * @author leonardo
 * 
 */
public class TestWebClientEasyMock {
	private ConnectionFactory factory;
	private InputStream stream;

	@Before
	public void setUp() {
		factory = EasyMock.createMock("factory", ConnectionFactory.class);
		stream = EasyMock.createMock("stream", InputStream.class);
	}

	@Test
	public void testGetContentOk() throws Exception {
		EasyMock.expect(factory.getData()).andReturn(stream);
		EasyMock.expect(stream.read()).andReturn(new Integer((byte) 'W'));
		EasyMock.expect(stream.read()).andReturn(new Integer((byte) 'o'));
		EasyMock.expect(stream.read()).andReturn(new Integer((byte) 'r'));
		EasyMock.expect(stream.read()).andReturn(new Integer((byte) 'k'));
		EasyMock.expect(stream.read()).andReturn(new Integer((byte) 's'));
		EasyMock.expect(stream.read()).andReturn(new Integer((byte) '!'));
		EasyMock.expect(stream.read()).andReturn(-1);
		stream.close();

		EasyMock.replay(factory);
		EasyMock.replay(stream);

		WebClient client = new WebClient();
		String result = client.getContent(factory);

		Assert.assertEquals("Works!", result);
	}

	@Test
	public void testGetContentCannotCloseInputStream() throws Exception {
		EasyMock.expect(factory.getData()).andReturn(stream);
		EasyMock.expect(stream.read()).andReturn(-1);
		stream.close();

		EasyMock.expectLastCall().andThrow(new IOException("cannot close"));

		EasyMock.replay(factory);
		EasyMock.replay(stream);
		WebClient client = new WebClient();
		String result = client.getContent(factory);
		Assert.assertNull(result);
	}

	@After
	public void tearDown() {
		EasyMock.verify(factory);
		EasyMock.verify(stream);
	}
}
