package controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.Bank;
import dao.BankingDAO;

@Controller
public class SearchBankController {

	@RequestMapping("/")
	public String printResult(Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/resources/beans.xml");
		BankingDAO bankingDAO = (BankingDAO) context.getBean("bankingDAO");
		
		Bank bank = bankingDAO.getBankForCode("21665382");

		
		model.addAttribute("name", bank.getName());

		return "home";
	}

}
