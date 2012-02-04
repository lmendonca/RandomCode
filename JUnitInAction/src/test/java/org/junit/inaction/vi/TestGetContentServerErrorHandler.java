/**
 * 
 */
package org.junit.inaction.vi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.handler.AbstractHandler;

/**
 * @author leonardo
 * 
 */
public class TestGetContentServerErrorHandler extends AbstractHandler {

	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, int dispatch) throws IOException,
			ServletException {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
}
