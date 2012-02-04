/**
 * 
 */
package org.junit.inaction.viii;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author leonardo
 * 
 */
public class SampleServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6295946491499171352L;

	public boolean isAuthenticated(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
		String authenticationAttribute = (String) session
				.getAttribute("authenticated");
		return Boolean.valueOf(authenticationAttribute).booleanValue();
	}
}
