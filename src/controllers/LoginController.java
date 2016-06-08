package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

	@RequestMapping("/userpage")
	public String showUserPage(Model model) {

		String username = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername();

		User user = usersService.getUser(username);

		model.addAttribute("User", user);

		return "userpage";
	}

	@RequestMapping("/login")
	public String showLogin() {

		return "log/login";
	}

	@RequestMapping("/logout")
	public String showlogOut(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {

			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "log/logout";
	}

	@RequestMapping("newaccount")
	public String showNewAccount(Model model) {

		model.addAttribute("user", new User());

		return "log/newaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult result) {

		if (result.hasErrors()) {
			return "log/newaccount";
		}

		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username", "This username already exist");
			return "log/newaccount";
		}

		try {
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username", "This username already exist");
			return "log/newaccount";
		}

		return "log/accountcreated";
	}

}
