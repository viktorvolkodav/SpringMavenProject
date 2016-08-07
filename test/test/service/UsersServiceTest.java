package test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import service.UsersService;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:resources/dao-context.xml",
		"classpath:resources/security-context.xml",
		"classpath:resources/service-context.xml",
		"classpath:resources/model-context.xml",
		"classpath:config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)

public class UsersServiceTest {
	private User user;

	@Autowired
	private UsersService usersService;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from users");
		user = new User("viktorvv", "victorvv", "v@v.com", true, "ROLE_USER");
	}

	@Test
	public void testService() {
		usersService.createUser(user);
		assertTrue(usersService.userExists("viktorvv"));
		assertEquals(usersService.getUser("viktorvv"), user);
	}
}
