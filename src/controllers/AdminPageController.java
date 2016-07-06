package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.AdminPageService;

/**
 * Controller responsible for admin`s page.
 * 
 * @author Виктор
 *
 */
@Controller
public class AdminPageController {
	/**
	 * a value of AdminPageService class.
	 */
	private AdminPageService adminPageService;

	/**
	 * Method set value to adminPageService.
	 * 
	 * @param adminPageServ the service value   
	 */
	@Autowired
	public final void setServiceClass(final AdminPageService adminPageServ) {
		this.adminPageService = adminPageServ;
	}

	/**
	 * Method responsible for mapping of admin`s page.
	 * 
	 * @return run adminpage.jsp
	 */
	@RequestMapping("/adminpage")
	public final String serverPage() {
		return "adminpage";
	}

	/**
	 * Method update database.
	 * 
	 * @param model
	 *            the model value
	 * @return run adminpage.jsp
	 */
	@RequestMapping("/updateDB")
	public final String updateDB(final Model model) {

		String res = adminPageService.updateDB();
		model.addAttribute("result", res);

		return "adminpage";
	}
}
