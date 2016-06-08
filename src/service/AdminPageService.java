package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Bank;
import dao.BankDAO;
import model.ParseInfoFromSite;

@Service("serverPageService")
public class AdminPageService {

	private BankDAO bankDAO;

	@Autowired
	public void setBankingDAO(BankDAO bankDAO) {
		this.bankDAO = bankDAO;
	}

	public String updateDB() {
		List<Bank> banks = new ArrayList<Bank>();
		try {
			banks = ParseInfoFromSite.parseSiteNBU();
		} catch (Exception ex) {
			return "����� �������� � ���������� ���������� � ����� ���. ���� ������ �� ���������.";
		}
		boolean cleanDB = bankDAO.cleanDB();
		if (cleanDB == true)
			return "���� ������ �� ���� �������";

		try {
			 bankDAO.updateDB(banks);
		} catch (Exception ex) {
			return "�������� � ����������� ���� ������. ���� ����� �� ���� ����������.";
		}
		return "���� ������ ���������. � �� ������ " + banks.size() + " �����";
	}
}
