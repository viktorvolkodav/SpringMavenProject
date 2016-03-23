package service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ExcelDAO;

@Service("searchBankInfoInExcelService")
public class SearchBankInfoInExcelService {
	
	private ExcelDAO excelDAO;

	@Autowired
	public void setExcelDAO(ExcelDAO excelDAO) {
		this.excelDAO = excelDAO;
	}
	
	public Map<String, Map<String, Double>> searchBankInExcel(int mfo) throws IOException{
		
		Map<String, Map<String, Double>> res = new LinkedHashMap<String, Map<String, Double>>();
		
		res = excelDAO.searchBankInfoInExcel("C://Eclipse/VBankinfo/src/excel/excelresurces/01102015.xls", mfo);
		
		return res;
	}

}
