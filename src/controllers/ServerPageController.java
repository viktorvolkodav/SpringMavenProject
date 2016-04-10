package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.ServerPageService;


@Controller
public class ServerPageController {
	
	private ServerPageService serverPageService;

	@Autowired
	public void setServiceClass(ServerPageService serverPageService) {
		this.serverPageService = serverPageService;
	}
	@RequestMapping(value="/serverpage", method=RequestMethod.GET)
	public String serverPage(Model model) {
		return "serverpage";
	}	
	
	@RequestMapping(value="/updateDB", method=RequestMethod.GET)
	public String updateDB(Model model){
		
		String res = serverPageService.updateDB();
		model.addAttribute("result", res);
		
		return "serverpage";
	}
}
