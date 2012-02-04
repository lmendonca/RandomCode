/**
 * 
 */
package org.junit.inaction.vii.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.inaction.vii.ConnectionFactory;

/**
 * @author leonardo
 * 
 */
public class HttpURLConnectionFactory implements ConnectionFactory {
	private URL url;

	public HttpURLConnectionFactory(URL url) {
		this.url = url;
	}

	@Override
	public InputStream getData() throws Exception {
		HttpURLConnection connection =
		         (HttpURLConnection) this.url.openConnection();
		return connection.getInputStream();
	}

}
