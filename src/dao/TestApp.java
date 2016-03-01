package dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import model.ParseInfoFromSite;

public class TestApp {
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/resources/beans.xml");
		BankingDAO bankingDAO = (BankingDAO) context.getBean("bankingDAO");
		
		try{
		/*Bank bank = bankingDAO.getBankForCode("21665382");
		System.out.println(bank.toString());
		List<Bank> banks = bankingDAO.getBankForName("·‡Ìiii");
		System.out.println(banks.size());
		for (Bank bank2 : banks) {
			System.out.println(bank2.toString());
		}
		
		List<Bank> banks2 = bankingDAO.getAllBanks();
		
		int n=1;
		for (Bank bank3 : banks2) {
			System.out.println(n+"  "+bank3.getShortName());
			n++;
		}*/
			
			
			List<Bank> banks = ParseInfoFromSite.parseSiteNBU();
			
			int n=1;
			for (Bank bank3 : banks) {
				System.out.println(n+"  "+bank3.getShortName());
				n++;
			}
			
			boolean res = bankingDAO.cleanDB();
			if (res==false) System.out.println("DB cleaned");
			else System.out.println("DB didnt cleane");
			
			bankingDAO.updateDB(banks);
			
			int n2=1;
			for (Bank bank3 : bankingDAO.getAllBanks()) {
				System.out.println(n2+"  "+bank3.getShortName());
				n2++;
			}
			
			
		}catch (DataAccessException e){
			System.out.println(e.getMessage());	
			System.out.println(e.getClass());	
		}
		
		
		
		
		((ClassPathXmlApplicationContext)context).close();
	
	}
}
