/**
 * 
 */
package org.junit.inaction.iii.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.inaction.iii.Controller;
import org.junit.inaction.iii.Request;
import org.junit.inaction.iii.RequestHandler;
import org.junit.inaction.iii.Response;

/**
 * @author leonardo
 * 
 */
public class DefaultController implements Controller {

	private Map<String,RequestHandler> requestHandlers = new HashMap<String,RequestHandler>();

	protected RequestHandler getHandler(Request request) {
		if (!this.requestHandlers.containsKey(request.getName())) {
			String message = "Cannot find handler for request name " + "["
					+ request.getName() + "]";
			throw new RuntimeException(message);
		}
		return this.requestHandlers.get(request.getName());
	}

	@Override
	public Response processRequest(Request request) {
		Response response;
		try {
			response = getHandler(request).process(request);
		} catch (Exception exception) {
			response = new ErrorResponse(request, exception);
		}
		return response;
	}

	@Override
	public void addHandler(Request request, RequestHandler requestHandler) {
		if (this.requestHandlers.containsKey(request.getName())) {
			throw new RuntimeException("A request handler has "
					+ "already been registered for request name " + "["
					+ request.getName() + "]");
		} else {
			this.requestHandlers.put(request.getName(), requestHandler);
		}

	}

}
