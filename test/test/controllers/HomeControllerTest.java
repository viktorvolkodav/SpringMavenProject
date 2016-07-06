package test.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import controllers.HomeController;

public class HomeControllerTest {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeController())
				.build();
	}

	@Test
	public void testMainPage() {
		try {
			this.mockMvc.perform(get("/")).andExpect((status().isOk())).andExpect(forwardedUrl("index"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	@Test
	public void testAboutPage() {
		try {
			this.mockMvc.perform(get("/aboutsitepage")).andExpect((status().isOk()))
					.andExpect(forwardedUrl("about"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
}
