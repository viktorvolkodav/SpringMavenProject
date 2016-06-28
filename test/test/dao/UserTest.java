package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import dao.User;

public class UserTest {

	@Test
	public void testHashCode() {
		User user1 = new User("viktorvv", "victorvv", "v@v.com", true,
				"ROLE_USER");
		User user2 = new User("ira", "ira", "ira@v.com", true, "ROLE_USER");

		assertNotEquals("Should not equals", new User().hashCode(),
				user2.hashCode());
		assertNotEquals("Should not equals", user1.hashCode(),
				user2.hashCode());
		assertEquals("Should equals", user1.hashCode(),
				user1.hashCode());
	}
	
	@Test
	public void testEquals(){
		User user1 = new User("viktorvv", "victorvv", "v@v.com", true, "ROLE_USER");
		User user2 = new User("ira", "ira", "ira@v.com", true, "ROLE_USER");
		
		assertNotEquals("Should not equals", new User(),
				user2);
		assertNotEquals("Should not equals", user1,
				user2);
		assertEquals("Should equals", user1,
				user1);
		
	}
	
}
