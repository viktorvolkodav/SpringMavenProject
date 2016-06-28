package service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ExcelDao;

@Service("searchBankInfoInExcelService")
public class SearchBankInfoInExcelService {
	
	private ExcelDao excelDao;
	private static Logger logger = LogManager.getLogger();

	@Autowired
	public void setExcelDAO(ExcelDao excelDao) {
		this.excelDao = excelDao;
	}
	
	public Map<String, Map<String, Double>> searchBankInExcel(int mfo) throws IOException{
		logger.info("run");
		
		Map<String, Map<String, Double>> res = new LinkedHashMap<String, Map<String, Double>>();
		
		res = excelDao.searchBankInfoInExcel("C://Eclipse/VBankinfo/src/excel/excelresurces/01102015.xls", mfo);
		
		return res;
	}

}
