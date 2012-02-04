/**
 * 
 */
package org.junit.inaction.vii.impl;

import java.io.InputStream;

import org.junit.inaction.vii.ConnectionFactory;

/**
 * @author leonardo
 * 
 */
public class MockConnectionFactory implements ConnectionFactory {

	private InputStream inputStream;

	public void setData(InputStream stream) {
		this.inputStream = stream;
	}

	@Override
	public InputStream getData() throws Exception {
		return this.inputStream;
	}

}
