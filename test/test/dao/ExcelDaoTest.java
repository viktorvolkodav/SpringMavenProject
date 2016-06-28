package test.dao;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.ExcelDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:resources/dao-context.xml",
		"classpath:resources/security-context.xml",
		"classpath:resources/service-context.xml",
		"classpath:resources/model-context.xml",
		"classpath:config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ExcelDaoTest {

	@Autowired
	private ExcelDao excelDao;

	@Test
	public void testSearchBankInfoInExcel() {

		int mfo = 305299;
		Map<String, Map<String, Double>> bankIn = excelDao
				.searchBankInfoInExcel(
						"C://Eclipse/VBankinfo/src/excel/excelresurces/01102015.xls",
						mfo);

		
		
		Map<String, Double> bankIn1 = bankIn.get("Власний капітал банків");
		
		
		assertTrue("true", bankIn1.containsKey("Резерви переоцінки"));
		assertTrue("true", bankIn1.containsKey("Емісійні різниці "));
		assertEquals("Should 2", new Double(1571266.78767), bankIn1.get("Резерви переоцінки"));

	}

}
