package test.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.net.BindException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import controllers.LoginController;

public class LoginControllerTest {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController())
				.build();
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
	public void testShowNewAccount() throws Exception {

		this.mockMvc.perform(get("/newaccount")).andExpect((status().isOk()))
				.andExpect(forwardedUrl("log/newaccount"));
	}

	@Test
	public void testDoCreate() {

		BindException ex = new BindException();
		try {
			this.mockMvc.perform(post("/createaccount").flashAttr("result", ex))
					.andExpect((status().isOk()))
					.andExpect(view().name("log/newaccount"))
					.andExpect(forwardedUrl("log/newaccount"))
					.andExpect(model().attribute("result", ex));
		} catch (Exception e) {
			/* NOP */ }
	}

}
