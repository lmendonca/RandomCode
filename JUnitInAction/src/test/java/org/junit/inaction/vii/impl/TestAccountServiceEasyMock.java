/**
 * 
 */
package org.junit.inaction.vii.impl;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.inaction.vii.AccountManager;

/**
 * @author leonardo
 * 
 */
public class TestAccountServiceEasyMock {

	private AccountManager mockAccountManager;

	@Before
	public void setUp() {
		mockAccountManager = EasyMock.createMock("mockAccountManager",
				AccountManager.class);
	}

	@Test
	public void testTransferOk() {
		Account senderAccount = new Account("1", 200);
		Account beneficiaryAccount = new Account("2", 100);

		// Start defining the expectations
		mockAccountManager.updateAccount(senderAccount);
		mockAccountManager.updateAccount(beneficiaryAccount);

		EasyMock.expect(mockAccountManager.findAccountForUser("1")).andReturn(
				senderAccount);
		EasyMock.expect(mockAccountManager.findAccountForUser("2")).andReturn(
				beneficiaryAccount);

		// we’re done defining the expectations
		EasyMock.replay(mockAccountManager);

		AccountService accountService = new AccountService();
		accountService.setAccountManager(mockAccountManager);
		accountService.transfer("1", "2", 50);

		Assert.assertEquals(150, senderAccount.getBalance());
		Assert.assertEquals(150, beneficiaryAccount.getBalance());
	}

	@After
	public void tearDown() {
		EasyMock.verify(mockAccountManager);
	}
}
