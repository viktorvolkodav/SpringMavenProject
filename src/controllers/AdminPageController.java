package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.AdminPageService;


@Controller
public class AdminPageController {
	
	private AdminPageService adminPageService;

	@Autowired
	public void setServiceClass(AdminPageService adminPageService) {
		this.adminPageService = adminPageService;
	}
	@RequestMapping(value="/adminpage", method=RequestMethod.GET)
	public String serverPage(Model model) {
		return "adminpage";
	}	
	
	@RequestMapping(value="/updateDB", method=RequestMethod.GET)
	public String updateDB(Model model){
		
		String res = adminPageService.updateDB();
		model.addAttribute("result", res);
		
		return "adminpage";
	}
}
