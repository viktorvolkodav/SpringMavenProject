package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.User;
import service.UsersService;

@Controller
public class LoginController {

	private UsersService usersService;

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/log/login")
	public String showLogin() {
		return "log/login";
	}

	@RequestMapping("/log/newaccount")
	public String showNewAccount(Model model) {

		model.addAttribute("user", new User());

		return "log/newaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {

		if (result.hasErrors()) {
			return "log/newaccount";
		}

		user.setAuthority("user");
		user.setEnabled(true);
		usersService.create(user);

		return "log/accountcreated";
	}

}