package model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import dao.Bank;
import dao.BankStatus;

/**
 * Class need to work with site of Nation Bank of Ukraine (NBU) - parse info,
 * and create Banks.
 * 
 * @author Виктор Волкодав
 *
 */

public class ParseInfoFromSite {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.M.yyyy");

	/**
	 * Parse site of Nation Bank of Ukraine (NBU)
	 * 
	 * @return List with all banks from NBU site
	 */
	public static List<Bank> parseSiteNBU() throws Exception{

		Map<String, String> map = new HashMap<String, String>();
		List<String> list = new ArrayList<String>();
		List<Bank> bankList = new ArrayList<Bank>();

		for (int i = 0, j = 1; i < 6; i++, j = j + 20) {

			list = getListOfBanks(
					"http://www.bank.gov.ua/control/bankdict/banks?type=369&sort=name&cPage=" + i + "&startIndx=" + j);

			for (int k = 0; k < list.size(); k++) {

				map = getBankInfoMap("http://www.bank.gov.ua/" + list.get(k));

				bankList.add(createBank(map));
			}
		}
		return bankList;
	}

	/**
	 * Parse info about a bank from NBU site and return it
	 * 
	 * @param URL
	 *            on page with information about one bank
	 * @return Map <String,String> with information about bank
	 */
	public static Map<String, String> getBankInfoMap(String URL) {

		Map<String, String> map = new HashMap<String, String>();
		Document doc;

		try {

			doc = Jsoup.connect(URL).userAgent("Mozilla").timeout(10 * 1000).get();
			List<Comment> comments = findAllComments(doc);
			for (Comment comment : comments) {
				String data = comment.getData();
				comment.after(data);
				comment.remove();
			}

			Elements cells = doc.getElementsByClass("cell");

			for (int i = 0; i < cells.size(); i = i + 2) {

				map.put(cells.get(i).text(), cells.get(i + 1).text());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * Return a List of Strings (part of URLS).
	 * 
	 * @param URL
	 *            where are 20 links to page with information about banks
	 * @return List<String> with 20 links to page with information about banks
	 */
	public static List<String> getListOfBanks(String URL) {
		List<String> list = new ArrayList<String>();

		Document doc;
		try {
			doc = Jsoup.connect(URL).userAgent("Mozilla").timeout(10 * 1000).get();

			Elements links = doc.select("a[href]");

			for (int i = 0; i < links.size(); i++) {

				String res = "";

				if ((res = links.get(i).attr("href")).startsWith("control/uk/bankdict/bank?id=")) {
					list.add(res);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Create Bank and add information about bank
	 * 
	 * @param map
	 *            with information about bank
	 * @return Bank
	 */
	public static Bank createBank(Map<String, String> map) {
		Bank bank = Bank.createBank();

		try {
			bank.setName(map.get("Назва"));
			bank.setShortName(map.get("Коротка назва"));
			bank.setCode(map.get("Код за ЄДРПОУ"));
			bank.setMfo(map.get("Код (МФО)"));
			bank.setDate(new java.sql.Date(DATE_FORMAT.parse(map.get("Дата реєстрації")).getTime()));
			bank.setAdress(map.get("Адреса"));
			bank.setLicense(map.get("Банківська ліцензія (номер)"));
			bank.setLicensedate(new java.sql.Date(DATE_FORMAT.parse(map.get("Банківська ліцензія (дата)")).getTime()));
			bank.setStatus(BankStatus.NORMAL);

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException | NullPointerException e) {
			bank.setLicense("");
			bank.setLicensedate(new java.sql.Date(0));
			bank.setStatus(BankStatus.LIQUIDATION);
			e.printStackTrace();
		}
		return bank;
	}

	/**
	 * Clear page from all comments. Its give access to some closed information
	 * 
	 * @param doc
	 * @return
	 */
	public static List<Comment> findAllComments(Document doc) {
		List<Comment> comments = new ArrayList<>();
		for (Element element : doc.getAllElements()) {
			for (Node n : element.childNodes()) {
				if (n.nodeName().equals("#comment")) {
					comments.add((Comment) n);
				}
			}
		}
		return Collections.unmodifiableList(comments);
	}
}
