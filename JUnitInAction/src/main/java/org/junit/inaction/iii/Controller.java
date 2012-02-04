/**
 * 
 */
package org.junit.inaction.iii;

/**
 * @author leonardo
 * 
 */
public interface Controller {
	Response processRequest(Request request);

	void addHandler(Request request, RequestHandler requestHandler);
}
