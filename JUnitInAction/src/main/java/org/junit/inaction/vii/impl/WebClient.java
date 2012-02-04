/**
 * 
 */
package org.junit.inaction.vii.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.inaction.vii.ConnectionFactory;

/**
 * @author leonardo
 * 
 */
public class WebClient {

	public String getContent(ConnectionFactory connectionFactory) {

		String result;
		StringBuffer content = new StringBuffer();
		InputStream is = null;
		try {
			is = connectionFactory.getData();
			int count;
			while (-1 != (count = is.read())) {
				content.append(new String(Character.toChars(count)));
			}
			result = content.toString();
		} catch (Exception e) {
			return null;
		}

		// Close the stream if (is != null) {
		try {
			is.close();
		} catch (IOException e) {
			result = null;
		}

		return result;
	}

	protected HttpURLConnection createHttpURLConnection(URL url)
			throws IOException {
		return (HttpURLConnection) url.openConnection();
	}
}
