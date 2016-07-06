package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Class responsible for mapping of main page and page with info about site.
 * @author Виктор
 *
 */
@Controller
public class HomeController {

	
	/**
	 * Method responsible for mapping of main page.
	 * @return index.jsp
	 */
	@RequestMapping("/")
	public final String mainPage() {
		return "index";
	}

	
	/**
	 * Method responsible for mapping of page with info about site.
	 * @param  model the model value
	 * @return about.jsp
	 */
	@RequestMapping("/aboutsitepage")
	public final String aboutPage(final Model model) {

		model.addAttribute("result", "На даний момент дана сторінка в розробці");
		return "about";
	}
}
