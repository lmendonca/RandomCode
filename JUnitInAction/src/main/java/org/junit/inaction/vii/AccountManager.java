/**
 * 
 */
package org.junit.inaction.vii;

import org.junit.inaction.vii.impl.Account;

/**
 * @author leonardo
 * 
 */
public interface AccountManager {
	
	Account findAccountForUser(String userId);

	void updateAccount(Account account);
}
