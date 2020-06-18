package com.example.demo.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.AccountDao;

import edu.stayConnected.FBO.Account;
import edu.stayConnected.FBO.RegistrationForm;

@Controller
public class AccountController {

	@Autowired
	private AccountDao accountDao;

	@GetMapping(value = "/")
	public String postHomeFromRoot() {
		return "home";
	}

	@GetMapping(value = "/home")
	public String postHome() {
		return "home";
	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public String showCreateAccount(Model model) {
		model.addAttribute("accountForm", new Account());
		return "createAccount";
	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public String createAccount(@Valid Account account, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "createAccount";
		} else {
			model.addAttribute(account);
			accountDao.createAccount(account);
			return "home";
		}
	}

	@RequestMapping(value = "/registerAccount", method = RequestMethod.POST)
	public String reegisterAccount(@Valid RegistrationForm regiForm, BindingResult result, Model model) {
		if (accountDao.validateAccount(regiForm)) {
			
			//they have to set password first, then edit account
			return "editAccount";
			// TODO auto login
		} else {
			return "registerAccount";
		}

	}

	@RequestMapping(value = "/registerAccount", method = RequestMethod.GET)
	public String registerAccount(Model model) {
		model.addAttribute("registrationForm", new RegistrationForm());
		return "registerAccount";
	}

	private String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	@RequestMapping(value = "/logoutForm", method = RequestMethod.GET)
	public String showLogoutForm() {
		return "logout";
	}
}
