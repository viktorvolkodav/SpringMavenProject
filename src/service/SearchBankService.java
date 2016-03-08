package service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Bank;
import dao.BankingDAO;

@Service("searchBankService")
public class SearchBankService {

	private BankingDAO bankingDAO;

	public Bank searchBankForCode(String str) {
		Bank bank = bankingDAO.getBankForCode(str);

		return bank;
	}

	public List<Bank> searchBankForName(String str) {

		return bankingDAO.getBankForName(str);
	}

	@Autowired
	public void setBankingDAO(BankingDAO bankingDAO) {
		this.bankingDAO = bankingDAO;
	}

	public static boolean isDigital(String code) {
		Pattern p = Pattern.compile("^[0-9]+");
		Matcher m = p.matcher(code);
		return m.matches();
	}

	public List<Bank> getAllBanks() {
		
		return bankingDAO.getAllBanks();
	}

}
