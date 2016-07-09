package test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.Bank;
import dao.BankDao;
import dao.BankStatus;
import service.SearchBankService;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:resources/dao-context.xml",
		"classpath:resources/security-context.xml",
		"classpath:resources/service-context.xml",
		"classpath:resources/model-context.xml",
		"classpath:config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)

public class SearchBankServiceTest {

	@Autowired
	private BankDao bankDao;

	@Autowired
	private SearchBankService searchBankService;

	@Autowired
	private DataSource dataSource;

	private List<Bank> banks;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from banktable");
		banks = creatListOfBanks();
		bankDao.updateDB(banks);
	}

	@Test
	public void testSearchBankForCode() {
		Bank bank = searchBankService.searchBankForCode("5555555");
		assertEquals("Should equals", banks.get(1), bank);
	}

	@Test
	public void testSearchBankForName() {
		List<Bank> banksForName = searchBankService.searchBankForName("Oschad");
		assertEquals(banks.get(1), banksForName.get(0));
	}

	@Test
	public void testIsDigital() {
		assertTrue(SearchBankService.isDigital("55"));
		assertFalse(SearchBankService.isDigital("s"));
	}
	
	@Test
	public void testGetAllBanks() {
		assertEquals(2, searchBankService.getAllBanks().size());
	}

	public final List<Bank> creatListOfBanks() {
		List<Bank> banks = new ArrayList<>();

		Bank bank1 = Bank.createBank();
		bank1.setName("Privat".toUpperCase());
		bank1.setShortName("priv".toUpperCase());
		bank1.setCode("44444444");
		bank1.setMfo("4");
		bank1.setDate(new java.sql.Date(100 * 60 * 60 * 60 * 1000));
		bank1.setAdress("Kiev");
		bank1.setLicense("111");
		bank1.setLicensedate(new java.sql.Date(10000));
		bank1.setStatus(BankStatus.NORMAL);

		Bank bank2 = Bank.createBank();
		bank2.setName("Oschad".toUpperCase());
		bank2.setShortName("os".toUpperCase());
		bank2.setCode("5555555");
		bank2.setMfo("5");
		bank2.setDate(new java.sql.Date(1));
		bank2.setAdress("Kiev");
		bank2.setLicense("111");
		bank2.setLicensedate(new java.sql.Date(1));
		bank2.setStatus(BankStatus.NORMAL);

		banks.add(bank1);
		banks.add(bank2);
		return banks;
	}
}
