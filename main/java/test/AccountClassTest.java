package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.account.Account;

class AccountClassTest {

	@Test
	void testMakeNewAccount() {
		Account.accounts.clear();                       //Always clear the arrayList in case all test are run together
		int previousSize = Account.accounts.size();     //Original size of arrayList
		
		Account.makeNewAccount("User1", 700);           //Add account in arrayList
		
		int sizeAfterAdd = Account.accounts.size();     //Size after addition
		
		assertEquals(previousSize+1, sizeAfterAdd);     //After an addition, size of arrayList should be 1 higher than previous size
		
	}
	
	@Test
	void testAccountHaveBennExist(){
		Account.accounts.clear();                       //Always clear the arrayList in case all test are run together
		Account account1 = Account.makeNewAccount("User2", 200);
		assertEquals(account1, Account.accountHaveBeenExist("User2"));
	}
	
	@Test
	void testDeleteSameUserName() {
		Account.accounts.clear();                       //Always clear the arrayList in case all test are run together
		Account.makeNewAccount("User3", 200);
		int previousSize = Account.accounts.size();
		
		Account.deleteSameUserName("User3");
		
		int sizeAfterDelete = Account.accounts.size();
		
		assertEquals(previousSize-1, sizeAfterDelete);
	}
	
	@Test
	void secondTestDeleteSameUserName() {
		Account.accounts.clear();                       //Always clear the arrayList in case all test are run together
		Account.makeNewAccount("User4", 200);
		assertTrue(Account.deleteSameUserName("User4"));
	}

}
