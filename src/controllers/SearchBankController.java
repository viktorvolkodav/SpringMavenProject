package controllers;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.Bank;
import service.SearchBankInfoInExcelService;
import service.SearchBankService;

@Controller
public class SearchBankController {

	private SearchBankService searchBankService;
	private SearchBankInfoInExcelService searchBankInfoInExcelService;

	@Autowired
	public void setServiceClass(SearchBankService searchBankService) {
		this.searchBankService = searchBankService;
	}

	@Autowired
	public void setSearchBankInfoInExcelService(SearchBankInfoInExcelService searchBankInfoInExcelService) {
		this.searchBankInfoInExcelService = searchBankInfoInExcelService;
	}

	@RequestMapping("/searchBank")
	public String searchBank(String searchString, Model model) {
		searchString = searchString.trim();

		if (searchString.equals("")) {
			model.addAttribute("badResult", "Ви не заповнили запит.");
			return "index";
		}

		Bank bank = Bank.createBank();

		if (SearchBankService.isDigital(searchString)) {
			if (searchString.length() == 8) {
				bank = searchBankService.searchBankForCode(searchString);

				if (bank == null) {
					model.addAttribute("badResult", "Банк з таким кодом відсутній. Спробуйте ще.");
					return "index";
				} else {
					model.addAttribute("Bank", bank);
					return "SearchResult";
				}

			} else {
				model.addAttribute("badResult", "Невіврний код ЄДРПОУ.");
				return "index";
			}

		} else {
			List<Bank> list = searchBankService.searchBankForName(searchString);
			if (list == null || list.isEmpty() || list.size() == 0) {
				model.addAttribute("badResult", "Банк з такою назвою відсутній. Спробуйте ще.");
				return "index";
			} else {
				model.addAttribute("BankList", list);
				return "SearchResult";
			}

		}

	}

	@RequestMapping("/getAllBanks")
	public String getAllBanks(Model model) {
		List<Bank> list = searchBankService.getAllBanks();
		model.addAttribute("BankList", list);
		return "SearchResult";
	}

	@RequestMapping("/financialInfo/{bancCode}/{bankMfo}")
	public String getFinancialInfo(@PathVariable int bancCode, @PathVariable int bankMfo, Model model) {
		Map<String, Map<String, Double>> map = new LinkedHashMap<String, Map<String, Double>>();
		try {
			map = searchBankInfoInExcelService.searchBankInExcel(bankMfo);
		} catch (Exception e) {
			e.printStackTrace();

		}

		model.addAttribute("bankInfo", map);
		model.addAttribute("Bank", searchBankService.searchBankForCode(bancCode + ""));
		return "SearchResult";
	}

}
