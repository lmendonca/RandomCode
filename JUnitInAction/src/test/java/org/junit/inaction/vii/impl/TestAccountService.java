/**
 * 
 */
package org.junit.inaction.vii.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author leonardo
 * 
 */
public class TestAccountService {
	
	@Test
	public void testTransferOk() {
		MockAccountManager mockAccountManager = new MockAccountManager();
		Account senderAccount = new Account("1", 200);
		Account beneficiaryAccount = new Account("2", 100);
		mockAccountManager.addAccount("1", senderAccount);
		mockAccountManager.addAccount("2", beneficiaryAccount);
		AccountService accountService = new AccountService();
		accountService.setAccountManager(mockAccountManager);
		accountService.transfer("1", "2", 50);
		Assert.assertEquals(150, senderAccount.getBalance());
		Assert.assertEquals(150, beneficiaryAccount.getBalance());
	}
}
