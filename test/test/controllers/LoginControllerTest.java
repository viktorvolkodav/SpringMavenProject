package test.controllers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import controllers.LoginController;
import dao.User;
import dao.UsersDao;
import service.UsersService;

public class LoginControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private UsersService usersService;

	private User user, user1;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController())
				.build();
	}

	@Test
	public void testShowUserPage() {

		try {
			this.mockMvc.perform(get("/userpage")).andExpect((status().isOk()))
					.andExpect(forwardedUrl("userpage"));
		} catch (Exception e) {
			/* NOP */ }
	}

	@Test
	public void testShowLogin() {
		try {
			this.mockMvc.perform(get("/login")).andExpect((status().isOk()))
					.andExpect(forwardedUrl("log/login"));
		} catch (Exception e) {
			/* NOP */ }
	}

	@Test
	public void testShowlogOut() {
		try {
			this.mockMvc.perform(get("/logout")).andExpect((status().isOk()))
					.andExpect(forwardedUrl("log/logout"));
		} catch (Exception e) {
			/* NOP */ }
	}

	@Test
	public void testShowNewAccount() {
		try {
			this.mockMvc.perform(get("/newaccount"))
					.andExpect((status().isOk()))
					.andExpect(forwardedUrl("log/newaccount"));
		} catch (Exception e) {
			/* NOP */ }
	}

	@Test
	public void testDoCreate() {

		try {
			this.mockMvc.perform(post("/createaccount"))
					.andExpect((status().isOk()))
					.andExpect(forwardedUrl("log/newaccount"));
		} catch (Exception e) {
			/* NOP */ }
	}

}
