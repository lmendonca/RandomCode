/**
 * 
 */
package org.junit.inaction.iii.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.inaction.iii.Request;
import org.junit.inaction.iii.RequestHandler;
import org.junit.inaction.iii.Response;

/**
 * @author leonardo
 * 
 */
public class TestDefaultController {

	private DefaultController controller;
	private Request request;
	private RequestHandler handler;

	@Before
	public void instantiate() throws Exception {
		controller = new DefaultController();
		request = new SampleRequest();
		handler = new SampleHandler();
		controller.addHandler(request, handler);
	}

	@Test
	public void testAddHandler() {
		RequestHandler handler2 = controller.getHandler(request);
		Assert.assertSame(
				"Handler we set in controller should be the same handler we get",
				handler2, handler);
	}

	@Test
	public void testProcessRequest() {
		Response response = controller.processRequest(request);
		Assert.assertNotNull("Must not return a null response", response);
		Assert.assertEquals("Response should be of type SampleResponse",
				SampleResponse.class, response.getClass());
	}

	@Test
	public void testProcessRequest2() {
		Response response = controller.processRequest(request);
		Assert.assertNotNull("Must not return a null response", response);
		Assert.assertEquals(new SampleResponse(), response);
	}

	@Test
	public void testProcessRequestAnswersErrorResponse() {
		SampleRequest request = new SampleRequest("testError");
		SampleExceptionHandler handler = new SampleExceptionHandler();
		controller.addHandler(request, handler);
		Response response = controller.processRequest(request);
		Assert.assertNotNull("Must not return a null response", response);
		Assert.assertEquals(ErrorResponse.class, response.getClass());
	}

	@Test(expected = RuntimeException.class)
	public void testGetHandlerNotDefined() {
		SampleRequest request = new SampleRequest("testNotDefined");
		// The following line is supposed to throw a RuntimeException
		controller.getHandler(request);
	}

	@Test(expected = RuntimeException.class)
	public void testAddRequestDuplicateName() {
		SampleRequest request = new SampleRequest();
		SampleHandler handler = new SampleHandler();
		// The following line is supposed to throw a RuntimeException
		controller.addHandler(request, handler);
	}

	@Test(timeout = 130)
	@Ignore(value="Ignore for now until we decide a decent time-limit")
	public void testProcessMultipleRequestsTimeout() {
		Request request;
		Response response = new SampleResponse();
		// RequestHandler handler = new SampleHandler();
		for (int i = 0; i < 99999; i++) {
			request = new SampleRequest(String.valueOf(i));
			controller.addHandler(request, handler);
			response = controller.processRequest(request);
			Assert.assertNotNull(response);
			Assert.assertNotSame(ErrorResponse.class, response.getClass());
		}
	}

	private class SampleRequest implements Request {

		private static final String DEFAULT_NAME = "Test";
		private String name;

		public SampleRequest(String name) {
			this.name = name;
		}

		public SampleRequest() {
			this(DEFAULT_NAME);
		}

		@Override
		public String getName() {
			return this.name;
		}
	}

	private class SampleHandler implements RequestHandler {
		@Override
		public Response process(Request request) throws Exception {
			return new SampleResponse();
		}
	}

	private class SampleResponse implements Response {
		private static final String NAME = "Test";

		@Override
		public String getName() {
			return NAME;
		}

		@Override
		public boolean equals(Object object) {
			boolean result = false;
			if (object instanceof SampleResponse) {
				result = ((SampleResponse) object).getName().equals(getName());
			}
			return result;
		}

		@Override
		public int hashCode() {
			return NAME.hashCode();
		}
	}

	private class SampleExceptionHandler implements RequestHandler {
		public Response process(Request request) throws Exception {
			throw new Exception("error processing request");
		}
	}
}