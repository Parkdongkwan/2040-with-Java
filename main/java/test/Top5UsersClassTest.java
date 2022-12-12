package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.demo.account.Account;
import com.example.demo.endgame.Top5Users;

class Top5UsersClassTest {
	
	Top5Users obj = new Top5Users();

	@Test
	void sortListTest() {
		Account.accounts.clear();        //Always clear the arrayList in case all test are run together
		Account.makeNewAccount("User7", 100);
		Account.makeNewAccount("User8", 50);
		Account.makeNewAccount("User9", 70);
		
		obj.sortList();
		
		String sortedUserName = "";
		
		for(int i = 0; i < Account.accounts.size(); i++) {
			sortedUserName += Account.accounts.get(i).getUserName()+" ";
		}
		assertEquals("User7 User9 User8 ",sortedUserName);
		
	}

}
