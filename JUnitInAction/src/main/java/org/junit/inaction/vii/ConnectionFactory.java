/**
 * 
 */
package org.junit.inaction.vii;

import java.io.InputStream;

/**
 * @author leonardo
 * 
 */
public interface ConnectionFactory {
	InputStream getData() throws Exception;
}
