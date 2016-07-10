package test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.powermock.reflect.Whitebox;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.Bank;
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

	@Test
	public void testUpdateDBProblemWithParsing() {

		AdminPageService aps = new AdminPageService();

		String result = "";
		try {
			Object[] arguments = { null, true };
			result = Whitebox.invokeMethod(aps, "updateDB", arguments);

		} catch (Exception e) {
			/* NOP */ }

		assertEquals(
				"Наявні проблеми з отриманням інформації з сайту НБУ. База данних не заповнена.",
				result);
	}
	
	@Test
	public void testUpdateDBProblemWithCleanDB() {

		AdminPageService aps = new AdminPageService();

		String result = "";
		List<Bank> banks = new ArrayList<Bank>();
		banks.add(Bank.createBank());
		try {
			Object[] arguments = { banks, false };
			result = Whitebox.invokeMethod(aps, "updateDB", arguments);

		} catch (Exception e) {
			/* NOP */ }

		assertEquals(
				"База данних не була очищена",
				result);
	}

}
