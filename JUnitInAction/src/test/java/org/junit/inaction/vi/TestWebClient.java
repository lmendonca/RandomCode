/**
 * 
 */
package org.junit.inaction.vi;

import static org.junit.Assert.assertNull;

import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;

/**
 * @author leonardo
 * 
 */
public class TestWebClient {
	private static WebClient client;

	@BeforeClass
	public static void setUp() throws Exception {
		Server server = new Server(8080);

		Context contentOkContext = new Context(server, "/testGetContentOk");
		contentOkContext.setHandler(new TestGetContentOkHandler());

		Context contentNotFoundContext = new Context(server,
				"/testGetContentNotFound");
		contentNotFoundContext.setHandler(new TestGetContentNotFoundHandler());

		Context contentErrorContext = new Context(server,
				"/testGetContentError");
		contentErrorContext.setHandler(new TestGetContentServerErrorHandler());

		server.setStopAtShutdown(true);
		server.start();

		client = new WebClient();
	}

	@After
	public void tearDown() {
		// Stop Jetty.
	}

	@Test
	public void testGetContentOk() throws Exception {
		String result = client.getContent(new URL(
				"http://localhost:8080/testGetContentOk"));
		Assert.assertEquals("It works", result);
	}

	@Test
	public void testGetContentNotFound() throws Exception {
		String result = client.getContent(new URL(
				"http://localhost:8080/testGetContentNotFound"));
		Assert.assertNull(result);
	}

	@Test
	public void testGetContentError() throws Exception {
		String result = client.getContent(new URL(
				"http://localhost:8080/testGetContentError/"));
		assertNull(result);
	}
}
