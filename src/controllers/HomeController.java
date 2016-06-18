package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String mainPage(Model model) {

		return "index";
	}

	@RequestMapping(path = "/aboutsitepage")
	public String aboutPage(Model model) {

		model.addAttribute("result", "На даний момент дана сторінка в розробці");
		return "about";
	}
}
