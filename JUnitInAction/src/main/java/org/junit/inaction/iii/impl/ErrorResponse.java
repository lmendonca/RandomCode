/**
 * 
 */
package org.junit.inaction.iii.impl;

import org.junit.inaction.iii.Request;
import org.junit.inaction.iii.Response;

/**
 * @author leonardo
 * 
 */
public class ErrorResponse implements Response {
	
	private static final String NAME = "Erro";
	private Request originalRequest;
	private Exception originalException;

	public ErrorResponse(Request request, Exception exception) {
		this.originalRequest = request;
		this.originalException = exception;
	}

	public Request getOriginalRequest() {
		return this.originalRequest;
	}

	public Exception getOriginalException() {
		return this.originalException;
	}
	
	@Override
	public String getName() {
		return NAME;
	}
}
