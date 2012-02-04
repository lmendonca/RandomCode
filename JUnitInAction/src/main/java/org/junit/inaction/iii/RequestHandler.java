/**
 * 
 */
package org.junit.inaction.iii;

/**
 * @author leonardo
 * 
 */
public interface RequestHandler {
	Response process(Request request) throws Exception;
}
