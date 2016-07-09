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
	private String FILE_NAME = "C://Eclipse/VBankinfo/src/excel/excelresurces/01102015.xls";

	@Autowired
	public void setExcelDAO(ExcelDao excelDao) {
		this.excelDao = excelDao;
	}
	
	public Map<String, Map<String, Double>> searchBankInExcel(int mfo) throws IOException{
		return searchBank(mfo, FILE_NAME);
	}
	
	public Map<String, Map<String, Double>> searchBank(int mfo, String fileName) throws IOException{
		logger.info("run");
		
		Map<String, Map<String, Double>> res = new LinkedHashMap<String, Map<String, Double>>();
		
		res = excelDao.searchBankInfoInExcel(fileName, mfo);
		
		return res;
	}

}
