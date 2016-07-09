package test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.SearchBankInfoInExcelService;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:resources/dao-context.xml",
		"classpath:resources/security-context.xml",
		"classpath:resources/service-context.xml",
		"classpath:resources/model-context.xml",
		"classpath:config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)

public class SearchBankInfoInExcelServiceTest {

	@Autowired
	SearchBankInfoInExcelService searchBankInfoInExcelService;

	@Test
	public void testSearchBankInfoInExcel() {

		int mfo = 305299;
		Map<String, Map<String, Double>> bankIn;
		try {
			bankIn = searchBankInfoInExcelService.searchBank(mfo,
					"C://Eclipse/VBankinfo/test/resources/01102015.xls");

			Map<String, Double> bankIn1 = bankIn.get("Власний капітал банків");

			assertTrue("true", bankIn1.containsKey("Резерви переоцінки"));
			assertTrue("true", bankIn1.containsKey("Емісійні різниці "));
			assertEquals("Should 2", new Double(1571266.78767),
					bankIn1.get("Резерви переоцінки"));
		} catch (IOException e) {
		}
	}
}
