/**
 * 
 */
package org.junit.inaction.vi;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author leonardo
 * 
 */
public class TestWebClient1 {
	@BeforeClass
	public static void setUp() {
		TestWebClient1 t = new TestWebClient1();
		URL.setURLStreamHandlerFactory(t.new StubStreamHandlerFactory());
	}

	private class StubStreamHandlerFactory implements URLStreamHandlerFactory {
		public URLStreamHandler createURLStreamHandler(String protocol) {
			return new StubHttpURLStreamHandler();
		}
	}

	private class StubHttpURLStreamHandler extends URLStreamHandler {
		protected URLConnection openConnection(URL url) throws IOException {
			return new StubHttpURLConnection(url);
		}
	}

	@Test
	public void testGetContentOk() throws Exception {
		WebClient client = new WebClient();
		String result = client.getContent(new URL("http://localhost"));
		Assert.assertEquals("It works", result);
	}
}
