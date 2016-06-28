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
		List<Bank> banks = new ArrayList<Bank>();
		try {
			banks = parseInfoFromSite.parseSiteNBU();
		} catch (Exception ex) {
			return "Наявні проблеми з отриманням інформації з сайту НБУ. База данних не заповнена.";
		}
		boolean cleanDB = bankDao.cleanDB();
		if (cleanDB == true)
			return "База данних не була очищена";

		try {
			 bankDao.updateDB(banks);
		} catch (Exception ex) {
			return "Проблеми з заповненням бази данних. База даних не була запаовнена.";
		}
		return "База данних заповнена. В ній наявно " + banks.size() + " банків";
	}
}
