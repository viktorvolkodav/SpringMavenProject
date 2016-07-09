package test.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.User;
import dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:resources/dao-context.xml",
		"classpath:resources/security-context.xml",
		"classpath:resources/service-context.xml",
		"classpath:resources/model-context.xml",
		"classpath:config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersDaoTest {

	private User user, user1;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from users");
		jdbc.execute("delete from authorities");
		user = new User("viktorvv", "victorvv", "v@v.com", true, "ROLE_USER");
		user1 = new User("iraira", "iraira", "iraira@v.com", true, "ROLE_USER");
	}

	@Test
	public void testCreateUser() {

		assertTrue("true", usersDao.createUser(user));

		List<User> users = usersDao.getAllUsers();

		assertEquals("Should 1", 1, users.size());

		assertTrue("Should exist", usersDao.exists(user.getUsername()));

		assertEquals("Should equals", user, users.get(0));
	}

	@Test
	public void testGetUser() {

		assertTrue("true", usersDao.createUser(user));
		assertTrue("true", usersDao.createUser(user1));

		User userFromDB = usersDao.getUser("viktorvv");

		assertEquals("Should equals", user.getUsername(),
				userFromDB.getUsername());
		assertEquals("Should equals", user.getEmail(), userFromDB.getEmail());
		assertEquals("Should equals", user.getAuthority(),
				userFromDB.getAuthority());
		assertEquals("Should equals", user.isEnabled(), userFromDB.isEnabled());

		assertEquals("Should equals", user, userFromDB);
	}

}