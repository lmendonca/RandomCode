/**
 * 
 */
package org.junit.inaction.viii;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author leonardo
 * 
 */
public class EasyMockSampleServletTest {
	private SampleServlet servlet;
	private HttpServletRequest mockHttpServletRequest;
	private HttpSession mockHttpSession;

	@Before
	public void setUp() {
		servlet = new SampleServlet();
		mockHttpServletRequest = EasyMock
				.createStrictMock(HttpServletRequest.class);
		mockHttpSession = EasyMock.createStrictMock(HttpSession.class);
	}

	@Test
	public void testIsAuthenticatedAuthenticated() {
		EasyMock.expect(mockHttpServletRequest.getSession(EasyMock.eq(false)))
				.andReturn(mockHttpSession);
		EasyMock.expect(
				mockHttpSession.getAttribute(EasyMock.eq("authenticated")))
				.andReturn("true");
		EasyMock.replay(mockHttpServletRequest);
		EasyMock.replay(mockHttpSession);
		Assert.assertTrue(servlet.isAuthenticated(mockHttpServletRequest));
	}

	@Test
	public void testIsAuthenticatedNotAuthenticated() {
		EasyMock.expect(
				mockHttpSession.getAttribute(EasyMock.eq("authenticated")))
				.andReturn("false");
		EasyMock.replay(mockHttpSession);
		EasyMock.expect(mockHttpServletRequest.getSession(EasyMock.eq(false)))
				.andReturn(mockHttpSession);
		EasyMock.replay(mockHttpServletRequest);
		Assert.assertFalse(servlet.isAuthenticated(mockHttpServletRequest));
	}

	@Test
	public void testIsAuthenticatedNoSession() {
		EasyMock.expect(mockHttpServletRequest.getSession(EasyMock.eq(false)))
				.andReturn(null);
		EasyMock.replay(mockHttpServletRequest);
		EasyMock.replay(mockHttpSession);
		Assert.assertFalse(servlet.isAuthenticated(mockHttpServletRequest));
	}

	@After
	public void tearDown() {
		EasyMock.verify(mockHttpServletRequest);
		EasyMock.verify(mockHttpSession);
	}
}
