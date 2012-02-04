/**
 * 
 */
package org.junit.inaction.vi;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.util.ByteArrayISO8859Writer;

/**
 * @author leonardo
 * 
 */
public class TestGetContentOkHandler extends AbstractHandler {

	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, int dispatch) throws IOException,
			ServletException {
		OutputStream out = response.getOutputStream();
		ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer();
		writer.write("It works");
		writer.flush();
		response.setIntHeader(HttpHeaders.CONTENT_LENGTH, writer.size());
		writer.writeTo(out);
		out.flush();

	}
}
