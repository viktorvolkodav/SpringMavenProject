package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Bank;
import dao.BankingDAO;
import model.ParseInfoFromSite;

@Service("serverPageService")
public class ServerPageService {

	private BankingDAO bankingDAO;

	@Autowired
	public void setBankingDAO(BankingDAO bankingDAO) {
		this.bankingDAO = bankingDAO;
	}

	public String updateDB() {
		List<Bank> banks = new ArrayList<Bank>();
		try {
			banks = ParseInfoFromSite.parseSiteNBU();
		} catch (Exception ex) {
			return "����� �������� � ���������� ���������� � ����� ���. ���� ������ �� ���������.";
		}
		boolean cleanDB = bankingDAO.cleanDB();
		if (cleanDB == true)
			return "���� ������ �� ���� �������";

		try {
			 bankingDAO.updateDB(banks);
		} catch (Exception ex) {
			return "�������� � ����������� ���� ������. ���� ����� �� ���� ����������.";
		}
		return "���� ������ ���������. � �� ������ " + banks.size() + " �����";
	}
}
