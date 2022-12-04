package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.account.Account;
import com.example.account.RecordHighestScore;


class RecordHighestScoreClassTest {
	
	RecordHighestScore obj = new RecordHighestScore();

	@Test
	void testFindHighestScore() {
		Account.accounts.clear();                  //Always clear the arrayList in case all test are run together
		Account.makeNewAccount("User5", 100);
		Account.makeNewAccount("User6", 50);
		Account.makeNewAccount("User7", 500);       //Add 3 accounts 
		
		obj.findHighestScore();                       //Find the highestScore and store it into field
		assertEquals(500,obj.getHighestScore());
	}

}
