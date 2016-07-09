package test.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import controllers.AdminPageController;


public class AdminPageControllerTest {

	private MockMvc mockMvc;

	/*@Autowired
	private AdminPageService adminPageService;*/

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(new AdminPageController()).build();
	}

	@Test
	public void testServerPage() {
		try {
			this.mockMvc.perform(get("/adminpage")).andExpect((status().isOk())).andExpect(forwardedUrl("adminpage"));
		} catch (Exception e) {
		/* NOP */ }
	}
}
