package test.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.AdminPageService;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:resources/dao-context.xml",
		"classpath:resources/security-context.xml",
		"classpath:resources/service-context.xml",
		"classpath:resources/model-context.xml",
		"classpath:config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)

public class AdminPageServiceTest {

	@Autowired
	private AdminPageService adminPageService;

	@Test
	public void testUpdateDBNormal() {
		assertNotNull(adminPageService.updateDB());
	}

}
