package service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Bank;
import dao.BankDao;

@Service("searchBankService")
public class SearchBankService {

	private BankDao bankDao;
	private static Logger logger = LogManager.getLogger();

	@Autowired
	public void setBankingDAO(BankDao bankDao) {
		this.bankDao = bankDao;
	}

	public Bank searchBankForCode(String str) {
		
		logger.info("run");
		return bankDao.getBankForCode(str);
	}

	public List<Bank> searchBankForName(String str) {
		logger.info("run");
		return bankDao.getBankForName(str);
	}

	public static boolean isDigital(String code) {
		logger.info("run");
		Pattern p = Pattern.compile("^[0-9]+");
		Matcher m = p.matcher(code);
		return m.matches();
	}

	public List<Bank> getAllBanks() {
		logger.info("run");
		return bankDao.getAllBanks();
	}

}
