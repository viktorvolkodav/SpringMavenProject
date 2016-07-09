package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dao.Bank;
import dao.BankStatus;

public class BankTest {

	@Test
	public void testHashCode() {
		List<Bank> banks = creatListOfBanks();

		assertNotEquals("Should not equals", Bank.createBank().hashCode(),
				banks.get(1).hashCode());
		assertNotEquals("Should not equals", banks.get(0).hashCode(),
				banks.get(1).hashCode());
		assertEquals("Should equals", banks.get(0).hashCode(),
				banks.get(0).hashCode());
	}

	@Test
	public void testEquals() {
		List<Bank> banks = creatListOfBanks();

		assertNotEquals("Should not equals", banks.get(0), banks.get(1));
		assertNotEquals("Should not equals", Bank.createBank(), banks.get(1));
		assertEquals("Should equals", banks.get(0), banks.get(0));
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
