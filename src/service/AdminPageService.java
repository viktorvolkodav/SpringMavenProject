package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Bank;
import dao.BankDao;
import model.ParseInfoFromSite;

@Service("serverPageService")
public class AdminPageService {

	private BankDao bankDao;

	@Autowired
	private ParseInfoFromSite parseInfoFromSite;

	private static Logger logger = LogManager.getLogger();

	@Autowired
	public void setBankingDAO(BankDao bankDao) {
		this.bankDao = bankDao;
	}

	public String updateDB() {
		logger.info("run");

		return updateDB(parseSiteNBU(), cleanDB());
	}

	private String updateDB(List<Bank> banks, boolean isCleanDB) {
		logger.info("run");
		
		if (banks == null)
			return "Наявні проблеми з отриманням інформації з сайту НБУ. База данних не заповнена.";
		if (isCleanDB == false)
			return "База данних не була очищена";
		try {
			bankDao.updateDB(banks);
		} catch (Exception ex) {
			return "Проблеми з заповненням бази данних. База даних не була запаовнена.";
		}
		return "База данних заповнена. В ній наявно " + banks.size()
				+ " банків";
	}

	private List<Bank> parseSiteNBU() {
		logger.info("run");
		
		List<Bank> banks = new ArrayList<Bank>();
		try {
			banks = parseInfoFromSite.parseSiteNBU();
		} catch (Exception ex) {
			return null;
		}
		return banks;
	}

	private boolean cleanDB() {
		logger.info("run");
		
		return !bankDao.cleanDB();
	}

}
