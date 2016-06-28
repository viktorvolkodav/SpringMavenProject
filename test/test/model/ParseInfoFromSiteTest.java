package test.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.Bank;
import model.ParseInfoFromSite;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:resources/dao-context.xml",
		"classpath:resources/security-context.xml",
		"classpath:resources/service-context.xml",
		"classpath:resources/model-context.xml",
		"classpath:config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ParseInfoFromSiteTest {
	
	@Autowired
	private ParseInfoFromSite parseInfoFromSite;

	@Test
	public void testParseSiteNBU() {
		List<Bank> banks = parseInfoFromSite.parseSiteNBU();
		assertNotEquals("not equals", 0, banks.size());
	}
}
